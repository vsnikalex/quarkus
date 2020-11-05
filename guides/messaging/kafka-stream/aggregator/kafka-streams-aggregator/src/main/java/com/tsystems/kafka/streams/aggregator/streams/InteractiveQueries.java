package com.tsystems.kafka.streams.aggregator.streams;

import com.tsystems.kafka.streams.aggregator.model.Aggregation;
import com.tsystems.kafka.streams.aggregator.model.WeatherStationData;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.kafka.streams.state.StreamsMetadata;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InteractiveQueries {

    private static final Logger LOG = Logger.getLogger(InteractiveQueries.class);

    @ConfigProperty(name = "hostname")
    String host;

    @Inject
    KafkaStreams streams;

    public GetWeatherStationDataResult getWeatherStationData(int id) {
        StreamsMetadata metadata = streams.metadataForKey(
                TopologyProducer.WEATHER_STATIONS_STORE,
                id,
                Serdes.Integer().serializer()
        );

        if (metadata == null || metadata == StreamsMetadata.NOT_AVAILABLE) {
            LOG.warn(String.format("Found no metadata for key %d", id));
            return GetWeatherStationDataResult.notFound();
        }
        else if (metadata.host().equals(host)) {
            LOG.info(String.format("Found data for key %d locally", id));
            Aggregation result = getWeatherStationStore().get(id);

            if (result != null) {
                return GetWeatherStationDataResult.found(WeatherStationData.from(result));
            }
            else {
                return GetWeatherStationDataResult.notFound();
            }
        }
        else {
            LOG.info(String.format(
                    "Found data for key %d on remote host %s:%s",
                    id,
                    metadata.host(),
                    metadata.port()
            ));
            return GetWeatherStationDataResult.foundRemotely(metadata.host(), metadata.port());
        }
    }

    public List<PipelineMetadata> getMetaData() {
        return streams.allMetadataForStore(TopologyProducer.WEATHER_STATIONS_STORE)
                .stream()
                .map(m -> new PipelineMetadata(
                        m.hostInfo().host() + ":" + m.hostInfo().port(),
                        m.topicPartitions()
                                .stream()
                                .map(TopicPartition::toString)
                                .collect(Collectors.toSet()))
                )
                .collect(Collectors.toList());
    }

    private ReadOnlyKeyValueStore<Integer, Aggregation> getWeatherStationStore() {
        while (true) {
            try {
                return streams.store(TopologyProducer.WEATHER_STATIONS_STORE, QueryableStoreTypes.keyValueStore());
            } catch (InvalidStateStoreException e) {
                // ignore, store not ready yet
            }
        }
    }
}

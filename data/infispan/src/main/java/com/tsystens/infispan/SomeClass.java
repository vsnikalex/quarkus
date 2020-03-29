package com.tsystens.infispan;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import javax.inject.Inject;

public class SomeClass {
    @Inject
    SomeClass(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    @Inject
    @Remote("myCache")
    RemoteCache<String, Book> cache;

    RemoteCacheManager remoteCacheManager;
}

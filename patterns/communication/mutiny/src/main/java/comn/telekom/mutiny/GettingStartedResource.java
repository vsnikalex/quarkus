package comn.telekom.mutiny;

import io.smallrye.mutiny.Multi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GettingStartedResource {

    @GET
    @Path("/world")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        Multi.createFrom().items("hello", "world")
                .onItem().transform(s -> s.toUpperCase() + " ")
                .onCompletion().continueWith("!")
                .subscribe().with(System.out::print);

        return "helloWold()";
    }

    @GET
    @Path("/concept")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloConcept() {
        Multi<String> source = Multi.createFrom().items("a", "b", "c");

        source
                .onItem().invoke(item -> System.out.println("Received item " + item))
                .onFailure().invoke(failure -> System.out.println("Failed with " + failure.getMessage()))
                .onCompletion().invoke(() -> System.out.println("Completed"))
                .onSubscribe().invoke(subscription -> System.out.println("We are subscribed!"))
                .onCancellation().invoke(() -> System.out.println("Downstream has cancelled the interaction"))
                .onRequest().invoke(n -> System.out.println("Downstream requested " + n + " items"))
                .subscribe().with(item -> System.out.println("Subscriber received " + item));

        return "helloConcept()";
    }
}
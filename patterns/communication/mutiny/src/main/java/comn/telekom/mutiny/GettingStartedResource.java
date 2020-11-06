package comn.telekom.mutiny;

import io.smallrye.mutiny.Multi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GettingStartedResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Multi.createFrom().items("hello", "world")
                .onItem().transform(s -> s.toUpperCase() + " ")
                .onCompletion().continueWith("!")
                .subscribe().with(System.out::print);

        return "hello";
    }
}
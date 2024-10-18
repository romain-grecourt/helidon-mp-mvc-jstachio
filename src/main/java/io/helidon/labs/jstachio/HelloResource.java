package io.helidon.labs.jstachio;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public HelloModel hello() {
        return new HelloModel("Joe");
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    public HelloModel helloName(@PathParam(value = "name") String name) {
        return new HelloModel(name);
    }
}

package io.helidon.labs.jstachio;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable hello() {
        return new JStachioView(new HelloModel("Joe"));
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable helloName(@PathParam(value = "name") String name) {
        return new JStachioView(new HelloModel(name));
    }
}

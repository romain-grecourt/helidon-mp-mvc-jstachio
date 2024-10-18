package io.helidon.labs.jstachio;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.mvc.MvcFeature;

@Provider
@ConstrainedTo(RuntimeType.SERVER)
public class JStachioMvcFeature implements Feature {

    @Override
    public boolean configure(final FeatureContext context) {
        Configuration config = context.getConfiguration();
        if (!config.isRegistered(MvcFeature.class)) {
            context.register(MvcFeature.class);
        }
        return true;
    }
}

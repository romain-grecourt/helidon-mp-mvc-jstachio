package io.helidon.labs.jstachio;

import io.jstach.jstache.JStache;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JStachioResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        Object entity = responseContext.getEntity();
        if (entity != null && entity.getClass().getAnnotation(JStache.class) != null) {
            responseContext.setEntity(new JStachioView(entity));
        }
    }
}

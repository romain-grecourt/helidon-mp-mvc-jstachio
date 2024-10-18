package io.helidon.labs.jstachio;

import io.jstach.jstachio.JStachio;
import io.jstach.jstachio.Template;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.ResolvedViewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;
import org.glassfish.jersey.server.mvc.spi.ViewableContext;

@Provider
@SuppressWarnings({"rawtypes", "unchecked"})
public class JStachioViewableContext implements ViewableContext {

    @Override
    public ResolvedViewable resolveViewable(Viewable viewable,
                                            MediaType mediaType,
                                            Class<?> resourceClass,
                                            TemplateProcessor processor) {
        return switch (viewable) {
            case ResolvedViewable rv -> rv;
            case JStachioView jv -> doResolveViewable(jv, mediaType, resourceClass, processor);
            default -> doResolveViewable(viewable, mediaType, resourceClass, processor);
        };
    }

    private ResolvedViewable doResolveViewable(JStachioView viewable, MediaType mediaType,
                                             Class<?> resourceClass,
                                             TemplateProcessor processor) {
        try {
            Template<?> template = JStachio.of().findTemplate(viewable.getModel());
            return new ResolvedViewable(processor, template, viewable, resourceClass, mediaType);
        } catch (Exception ignored) {
            return null;
        }
    }

    private ResolvedViewable doResolveViewable(Viewable viewable, MediaType mediaType,
                                               Class<?> resourceClass,
                                               TemplateProcessor processor) {
        Object template = processor.resolve(viewable.getTemplateName(), mediaType);
        return new ResolvedViewable(processor, template, viewable, resourceClass, mediaType);
    }
}

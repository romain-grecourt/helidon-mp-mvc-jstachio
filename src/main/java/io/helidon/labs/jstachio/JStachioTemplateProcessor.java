package io.helidon.labs.jstachio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import io.helidon.common.LazyValue;

import io.jstach.jstachio.TemplateInfo;
import io.jstach.jstachio.spi.TemplateProvider;
import io.jstach.jstachio.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;

@Provider
public class JStachioTemplateProcessor implements TemplateProcessor<Template<?>> {

    private final LazyValue<Map<String, Template<?>>> templates = LazyValue.create(this::templates);

    @Override
    public Template<?> resolve(String templateName, MediaType mediaType) {
        return templates.get().get(templateName);
    }

    @Override
    public void writeTo(Template<?> template,
                        Viewable viewable,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> headers,
                        OutputStream out) throws IOException {

        String charset = mediaType.getParameters().getOrDefault(MediaType.CHARSET_PARAMETER, "UTF-8");
        headers.put(HttpHeaders.CONTENT_TYPE, List.of(mediaType.withCharset(charset).toString()));
        render(template, viewable.getModel(), new OutputStreamWriter(out, Charset.forName(charset)));
    }

    @SuppressWarnings("unchecked")
    private <T> void render(Template<T> template, Object model, OutputStreamWriter writer) throws IOException {
        template.execute((T) model, writer);
        writer.flush();
    }

    private Map<String, Template<?>> templates() {
        return ServiceLoader.load(TemplateProvider.class).stream()
                .flatMap(p -> p.get().provideTemplates().stream())
                .collect(Collectors.toMap(TemplateInfo::templatePath, Function.identity()));
    }
}

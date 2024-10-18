package io.helidon.labs.jstachio;

import org.glassfish.jersey.server.mvc.Viewable;

public class JStachioView extends Viewable {

    public JStachioView(Object model) {
        super("", model);
    }
}

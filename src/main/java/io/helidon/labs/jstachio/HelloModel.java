package io.helidon.labs.jstachio;

import io.jstach.jstache.JStache;

@JStache(path = "hello.hbs")
public record HelloModel(String name) {
}

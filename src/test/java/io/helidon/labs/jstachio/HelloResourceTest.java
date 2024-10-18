package io.helidon.labs.jstachio;

import jakarta.ws.rs.client.WebTarget;

import io.helidon.microprofile.testing.junit5.HelidonTest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
class HelloResourceTest {

    @Test
    void testHello(WebTarget target) {
        String html = target.request().get(String.class);
        assertThat(html.contains("<h1>Hello Joe!</h1>"), is(true));
    }

    @Test
    void testHelloJack(WebTarget target) {
        String html = target.path("/Jack").request().get(String.class);
        assertThat(html.contains("<h1>Hello Jack!</h1>"), is(true));
    }
}

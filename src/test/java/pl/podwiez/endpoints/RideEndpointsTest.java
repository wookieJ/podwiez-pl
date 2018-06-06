package pl.podwiez.endpoints;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RideEndpointsTest {
    @Test
    public void getWithoutAuthorizationThen401Expected() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/rides/");
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(401));
    }
}
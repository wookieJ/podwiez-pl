package pl.podwiez.endpoints;

import org.junit.Before;
import pl.podwiez.endpoint.RideEndpoint;

public class RideEndpointsTest {
    RideEndpoint rideEndpoints;

    @Before
    public void setUp() {
        rideEndpoints = new RideEndpoint();
    }

//    @Test
//    public void getAllRidesOkStatusTest() throws Exception {
//        Assert.assertThat(rideEndpoints.getAllRides().getStatusCode(), is(HttpStatus.OK));
//    }
}
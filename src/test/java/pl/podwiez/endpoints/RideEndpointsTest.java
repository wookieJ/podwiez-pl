package pl.podwiez.endpoints;

import org.junit.Before;
import pl.podwiez.endpoint.RideEndpoints;

public class RideEndpointsTest {
    RideEndpoints rideEndpoints;

    @Before
    public void setUp() {
        rideEndpoints = new RideEndpoints();
    }

//    @Test
//    public void getAllRidesOkStatusTest() throws Exception {
//        Assert.assertThat(rideEndpoints.getAllRides().getStatusCode(), is(HttpStatus.OK));
//    }
}
package pl.podwiez.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ids")
public class IdGenerator {
    long rideId;

    public long getRideId() {
        return rideId;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public IdGenerator() {
    }

    public IdGenerator(long rideId) {
        this.rideId = rideId;
    }

    @Override
    public String toString() {
        return "IdGenerator{" + "rideId=" + rideId + '}';
    }
}

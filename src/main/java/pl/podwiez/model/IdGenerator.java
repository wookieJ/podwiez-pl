package pl.podwiez.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ids")
public class IdGenerator {
    long rideId;
    long accountId;

    public long getRideId() {
        return rideId;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public IdGenerator() {
    }

    public IdGenerator(long rideId) {
        this.rideId = rideId;
    }

    @Override
    public String toString() {
        return "IdGenerator{" + "rideId=" + rideId + ", accountId=" + accountId + '}';
    }
}

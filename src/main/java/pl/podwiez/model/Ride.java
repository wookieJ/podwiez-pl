package pl.podwiez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Time;

@Document(collection = "rides")
@XmlRootElement
public class Ride {
    @Id
    @JsonIgnore
    @XmlTransient
    private ObjectId _id;

    @JsonIgnore
    @XmlTransient
    @Indexed(name = "id", unique = true)
    public long id;

    private String fromPlace;
    private String toPlace;
    private boolean monRide;
    private boolean tueRide;
    private boolean wedRide;
    private boolean thuRide;
    private boolean friRide;
    private boolean satRide;
    private boolean sunRide;
    private Time departureTime;
    private Time arrivalTime;
    private double amount;

    @XmlTransient
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public boolean isMonRide() {
        return monRide;
    }

    public void setMonRide(boolean monRide) {
        this.monRide = monRide;
    }

    public boolean isTueRide() {
        return tueRide;
    }

    public void setTueRide(boolean tueRide) {
        this.tueRide = tueRide;
    }

    public boolean isWedRide() {
        return wedRide;
    }

    public void setWedRide(boolean wedRide) {
        this.wedRide = wedRide;
    }

    public boolean isThuRide() {
        return thuRide;
    }

    public void setThuRide(boolean thuRide) {
        this.thuRide = thuRide;
    }

    public boolean isFriRide() {
        return friRide;
    }

    public void setFriRide(boolean friRide) {
        this.friRide = friRide;
    }

    public boolean isSatRide() {
        return satRide;
    }

    public void setSatRide(boolean satRide) {
        this.satRide = satRide;
    }

    public boolean isSunRide() {
        return sunRide;
    }

    public void setSunRide(boolean sunRide) {
        this.sunRide = sunRide;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Ride() {
    }

    public Ride(String fromPlace, String toPlace, boolean monRide, boolean tueRide, boolean wedRide, boolean thuRide, boolean friRide, boolean satRide, boolean sunRide, Time departureTime, Time arrivalTime, double amount) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.monRide = monRide;
        this.tueRide = tueRide;
        this.wedRide = wedRide;
        this.thuRide = thuRide;
        this.friRide = friRide;
        this.satRide = satRide;
        this.sunRide = sunRide;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ride{" + "fromPlace='" + fromPlace + '\'' + ", toPlace='" + toPlace + '\'' + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", amount=" + amount + '}';
    }
}

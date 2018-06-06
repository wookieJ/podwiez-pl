package pl.podwiez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.podwiez.exception.CannotParticipateTwiceOnRide;
import pl.podwiez.exception.CannotParticipateYourOwnRide;
import pl.podwiez.exception.NoAvailablePlacesInRide;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "rides")
public class Ride {
    @Id
    @JsonIgnore
    private ObjectId _id;

    @Indexed(name = "id", unique = true)
    public long id;

    @DBRef
    private Account account;

    @DBRef
    private List<Account> members;
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
    private int availablePlaces;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Account> getMembers() {
        return members;
    }

    public void setMembers(List<Account> members) {
        this.members = members;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Ride() {
        this.members = new ArrayList<>();
    }

    public Ride(String fromPlace, String toPlace, boolean monRide, boolean tueRide, boolean wedRide, boolean thuRide, boolean friRide, boolean satRide, boolean sunRide, Time departureTime, Time arrivalTime, double amount, int availablePlaces) {
        this.members = new ArrayList<>();
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
        this.availablePlaces = availablePlaces;
    }

    @Override
    public String toString() {
        return "Ride{" + "id=" + id + ", account=" + account + ", members=" + members + ", fromPlace='" + fromPlace + '\'' + ", toPlace='" + toPlace + '\'' + ", monRide=" + monRide + ", tueRide=" + tueRide + ", wedRide=" + wedRide + ", thuRide=" + thuRide + ", friRide=" + friRide + ", satRide=" + satRide + ", sunRide=" + sunRide + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", amount=" + amount + ", availablePlaces=" + availablePlaces + '}';
    }

    /**
     * Adding account reference to members list and decreasing available amount places
     *
     * @param account account we want to add
     * @return account we added to members of this ride
     * @throws NoAvailablePlacesInRide      throwing when there are no available places in car
     * @throws CannotParticipateYourOwnRide throwing when try to be member in own ride
     * @throws CannotParticipateTwiceOnRide throwing when try to be member twice in same ride
     */
    public Account addToMembers(Account account) throws NoAvailablePlacesInRide, CannotParticipateYourOwnRide, CannotParticipateTwiceOnRide {
        if (getAccount().getEmail().equals(account.getEmail())) {
            System.out.println("You try to participate on your own ride!");
            throw new CannotParticipateYourOwnRide("You try to participate on your own ride!");
        }
        if (members.stream().anyMatch(e -> e.get_id().equals(account.get_id()))) {
            System.out.println("You are already member of this ride!");
            throw new CannotParticipateTwiceOnRide("You are already member of this ride!");
        }
        if (availablePlaces < 1)
            throw new NoAvailablePlacesInRide("There is no places in car!");
        members.add(account);
        availablePlaces--;
        return account;
    }
}

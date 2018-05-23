package pl.podwiez.model;//package pl.podwiez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "accounts")
public class Account {
    @Id
    @JsonIgnore
    private ObjectId _id;

    @JsonIgnore
    @Indexed(name = "id", unique = true)
    public long id;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @JsonIgnore
    private String passwordConfirm;

    public Account() {
    }

    public Account(String username, String password) {
        this.email = username;
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return "Account{" + "_id=" + _id + ", id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", passwordConfirm='" + passwordConfirm + '\'' + '}';
    }
}

package database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public", catalog = "pokemondb")
public class UsersEntity {
    private int id;
    private String firstname;
    private Date birthday;
    private String gender;
    private String email;
    private Serializable password;
    private String lastname;
    private Collection<FeedbackEntity> feedbacksById;
    private Collection<FeedbackEntity> feedbacksById_0;
    private Collection<ItemsArticlesEntity> itemsArticlesById;
    private Collection<PokemonsArticlesEntity> pokemonsArticlesById;
    private Collection<TradesEntity> tradesById;
    private Collection<TradesEntity> tradesById_0;
    private Collection<UsersRolesEntity> usersRolesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 20)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 6)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = true)
    public Serializable getPassword() {
        return password;
    }

    public void setPassword(Serializable password) {
        this.password = password;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 30)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByAuthorId")
    public Collection<FeedbackEntity> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<FeedbackEntity> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<FeedbackEntity> getFeedbacksById_0() {
        return feedbacksById_0;
    }

    public void setFeedbacksById_0(Collection<FeedbackEntity> feedbacksById_0) {
        this.feedbacksById_0 = feedbacksById_0;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<ItemsArticlesEntity> getItemsArticlesById() {
        return itemsArticlesById;
    }

    public void setItemsArticlesById(Collection<ItemsArticlesEntity> itemsArticlesById) {
        this.itemsArticlesById = itemsArticlesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<PokemonsArticlesEntity> getPokemonsArticlesById() {
        return pokemonsArticlesById;
    }

    public void setPokemonsArticlesById(Collection<PokemonsArticlesEntity> pokemonsArticlesById) {
        this.pokemonsArticlesById = pokemonsArticlesById;
    }

    @OneToMany(mappedBy = "usersByFirstUserId")
    public Collection<TradesEntity> getTradesById() {
        return tradesById;
    }

    public void setTradesById(Collection<TradesEntity> tradesById) {
        this.tradesById = tradesById;
    }

    @OneToMany(mappedBy = "usersBySecondUserId")
    public Collection<TradesEntity> getTradesById_0() {
        return tradesById_0;
    }

    public void setTradesById_0(Collection<TradesEntity> tradesById_0) {
        this.tradesById_0 = tradesById_0;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UsersRolesEntity> getUsersRolesById() {
        return usersRolesById;
    }

    public void setUsersRolesById(Collection<UsersRolesEntity> usersRolesById) {
        this.usersRolesById = usersRolesById;
    }
}

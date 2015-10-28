package cz.muni.fi.pa165.bookingmanager.entity;

import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Simple entity representing hotel customer.
 * <p/>
 * Created on 22.10.2015.
 *
 * @author Vladimir Caniga
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of this customer.
     */
    @NotNull
    private String name;

    /**
     * Surname of this customer.
     */
    @NotNull
    private String surname;

    /**
     * Email of this customer.
     */
    @NotNull
    private String email;

    /**
     * Username of this customer that he uses to log in to the reservation system.
     */
    private String username;

    /**
     * Password of this customer that he uses to log in to the reservation system.
     */
    private String password;

    /**
     * List of all reservation that are associated with this customer.
     */
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations = new ArrayList<Reservation>();

    private boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
 
    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public void setReservations(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.getName())) return false;
        if (!surname.equals(customer.getSurname())) return false;
        return email.equals(customer.getEmail());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}

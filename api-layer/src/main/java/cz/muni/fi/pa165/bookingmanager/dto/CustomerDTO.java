package cz.muni.fi.pa165.bookingmanager.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object of a Customer entity.
 *
 * Created on 22.11.2015.
 *
 * @author Vladimir Caniga
 */
public class CustomerDTO {

    /**
     * Generated ID of the customer
     */
    private Long id;

    /**
     * First name of the customer
     */
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    /**
     * Surname of the customer
     */
    @NotNull
    @Size(min = 1, max = 30)
    private String surname;

    /**
     * Email of the customer
     */
    @NotNull
    @Size(min = 3, max = 30)
    private String email;

    /**
     * Username of the customer
     */
    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    /**
     * Password of the customer
     */
    private String password;

    /**
     * Set to true if the customer has admin role assigned
     */
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    
    public boolean getIsAdmin(boolean admin) {
        return isAdmin;
    }
    
    public void setIsAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;

        CustomerDTO that = (CustomerDTO) o;

        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        return !(getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null);

    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}

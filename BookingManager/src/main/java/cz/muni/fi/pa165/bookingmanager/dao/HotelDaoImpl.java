package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Iveta Jurcikova <ivet.jurcikova@gmail.com>
 */
@Repository
public class HotelDaoImpl implements HotelDao {

    @PersistenceUnit
    private EntityManager em;

    @Override
    public void create(Hotel hotel) {
        this.validateHotel(hotel);
        em.persist(hotel);
    }

    @Override
    public Hotel findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return em.find(Hotel.class, id);
    }

    @Override
    public Hotel findByName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name is not valid");
        }
        try {
            return em.createQuery("Select h from Hotel h where h.name=:name", Hotel.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Hotel> findAll() {
        return Collections.unmodifiableList((em.createQuery("Select h from Hotel h", Hotel.class)).getResultList());
    }

    @Override
    public void update(Hotel hotel) {
        this.validateHotel(hotel);
        em.merge(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        this.validateHotel(hotel);
        em.remove(hotel);
    }

    /**
     * Method for validating attributes of hotel
     *
     * @param hotel
     * @exception IllegalArgumentException if some attribute is not valid
    *
     */
    private void validateHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel is null");
        }
        if (hotel.getId() != null) {
            throw new IllegalArgumentException("Hotel already exist");
        }
        if (hotel.getName() == null || hotel.getName().equals("")) {
            throw new IllegalArgumentException("Name is not valid");
        }

    }

}

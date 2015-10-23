package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Iveta Jurcikova <ivet.jurcikova@gmail.com>
 */
public class HotelDaoImpl implements HotelDao {
    
    @PersistenceUnit
    private EntityManager em;

    @Override
    public void create(Hotel hotel) {
        em.persist(hotel);
    }
    
    @Override
    public Hotel findById(Long id) {
        return em.find(Hotel.class, id);
    }
    
    @Override
    public Hotel findByName(String name) {
        return em.find(Hotel.class, name);
    }

    @Override
    public List<Hotel> findAll() {
        return Collections.unmodifiableList((em.createQuery("Select h from Hotel h",Hotel.class)).getResultList());
    }

    @Override
    public void update(Hotel hotel) {
        em.merge(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        em.remove(hotel);
    }
    
   
    
}

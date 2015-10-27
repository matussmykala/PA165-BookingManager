/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created on 24.10.2015.
 *
 * @author Iveta Jurcikova
 */

@Repository
public class HotelDaoImpl implements HotelDao {

    
    @PersistenceContext
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
        em.merge(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        em.remove(em.contains(hotel) ? hotel : em.merge(hotel));
    }

}
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entity.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * Class implements basic CRUD operations for entity Room.
 *
 * Created on 25.10.2015.
 *
 * @author Vladimir Caniga
 */
@Repository
public class RoomDaoImpl implements RoomDao {

    @PersistenceContext
    EntityManager em;

    /**
     * Create entry for room
     *
     * @param room room to be created
     */
    @Override
    public void create(Room room) {
        em.persist(room);
    }

    /**
     * Find room by given id
     *
     * @param id id of the room to look up
     * @return Room object if the room with given id was found, null otherwise
     */
    @Override
    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

    /**
     * Return list of all rooms
     *
     * @return list of all rooms stored in database
     */
    @Override
    public List<Room> findAll() {
        TypedQuery<Room> tq = em.createQuery("SELECT r FROM Room r", Room.class);
        return Collections.unmodifiableList(tq.getResultList());
    }

    /**
     * Update room entry
     *
     * @param room room object with updated information
     */
    @Override
    public void update(Room room) {
        em.merge(room);
    }

    /**
     * Delete room entry
     * This implementation can delete both managed and detached instances.
     *
     * @param room room object that is to be removed from database
     */
    @Override
    public void delete(Room room) {
        em.remove(room);
    }
}

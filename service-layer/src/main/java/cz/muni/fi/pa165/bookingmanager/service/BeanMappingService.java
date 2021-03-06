package cz.muni.fi.pa165.bookingmanager.service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

/**
 *
 * @author ivet
 */
public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}

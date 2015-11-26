
import cz.muni.fi.pa165.bookingmanager.service.BeanMappingService;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivet
 */
public class MappingService {
    
    @Mock
    Mapper mapper;
    
    
    @Autowired
    @InjectMocks
    BeanMappingService beanMappingService;
    
    
}

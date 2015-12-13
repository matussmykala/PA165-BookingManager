
package cz.muni.fi.pa165.rest.layer.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brossi
 */
@JsonIgnoreProperties({ "password"})
public class CustomerDTOMixin {
    
}
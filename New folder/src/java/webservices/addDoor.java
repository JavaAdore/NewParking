/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author orcl
 */
@Path("/addDoor")
public class addDoor {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int addDoor(String doorName) 
    {
        
        
        return 0;
    }
}

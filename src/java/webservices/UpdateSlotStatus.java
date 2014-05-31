/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotImp;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author orcl
 */
@Path("/update")

public class UpdateSlotStatus {

    @Path("/slotStatus")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSlot(@QueryParam("slotid") int slotid, @QueryParam("status") int status) {
        System.out.println(String.format("webservice has been called and slotid is %s status is %s", slotid, status));
        return GarageSlotImp.getInstance().UpdateGarageSlot(slotid, status);
    }

    @Path("/changeSlotStatus")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String updateSlotStatus(@FormParam("slotid") String slotid, @FormParam("status") String status) {
        if (status != null) {
            try {
                return GarageSlotImp.getInstance().UpdateGarageSlot(Integer.parseInt(slotid), Integer.parseInt(status));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return utils.Constants.FAILED+"";

    }

    @Path("/slotAvailability")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setSlotEnabled(@QueryParam("slotId") int slotId, @QueryParam("enabled") int enabled) {
        return GarageSlotImp.getInstance().setEnabled(slotId, enabled);
    }

    @Path("/garageAvailability")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setGarageEnabled(@QueryParam("garageid") int gargeId, @QueryParam("enabled") int enabled) {
        return GarageImp.getInstance().setEnabled(gargeId, enabled);
    }
}

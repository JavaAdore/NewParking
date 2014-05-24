/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotsStatusImp;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Utils;
import utils.WrappedGarageSlotsStatus;

/**
 *
 * @author orcl
 */
@Path("/garageoverview")
public class GarageOverView {

    GarageSlotsStatusImp garageSlotsStatusImp = GarageSlotsStatusImp.getInstance();
    GarageImp garageImp = GarageImp.getInstance();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getGarageOverView(@FormParam(value = "id") String id) {

        if (id != null) {
            int garageId = Integer.parseInt(id);
            return Utils.prepareGarageOverView(garageId).toString();
        }
        return new JSONObject().toString();
    }
}

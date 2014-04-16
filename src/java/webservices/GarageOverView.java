/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotsStatusImp;
import DAOS.UserImp;
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
import pojo.Garage;
import pojo.Users;
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
    public String getGarageOverView(@FormParam(value = "garageId") final String gId, @FormParam(value = "userId") final String uId) {

        System.out.println("garage id = "+ gId + " userId= "+uId );
        if (gId != null) {
            final int garageId = Integer.parseInt(gId);
            if (uId != null) {
                final int userId = Integer.parseInt(uId);
                new Thread() {
                    @Override
                    public void run() {
                        UserImp.getInstance().addVisit(new Users(userId), new Garage(garageId));
                    }
                }.start();

            }
            return Utils.prepareGarageOverView(garageId).toString();
        }
        return new JSONObject().toString();
    }
}

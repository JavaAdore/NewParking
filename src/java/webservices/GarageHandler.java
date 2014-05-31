/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.GarageSlotDoorsImp;
import GObjects.Garage;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author orcl
 */
@WebService
public class GarageHandler {

    GarageSlotDoorsImp garageSlotDoorsImp = GarageSlotDoorsImp.getInstance();

    public String handleThisGarage(String garageAsString, char identifier) {
        Gson gson = new Gson();
        String result = "";

        Garage garage = gson.fromJson(garageAsString, GObjects.Garage.class);

        pojo.Garage garage1 = GarageImp.getInstance().getGarage(garage.getGarageId());
        if (garage1 != null) {
            if (garage1.getGarageStatus().size() == 0) {
                int handleThisGaragePlease = garageSlotDoorsImp.handleThisGaragePlease(garage);
                if (handleThisGaragePlease == 0) {
                    result = "done";
                } else {
                    result = "failed";

                }
            } else {
                if (identifier == 'p') {

                    int handleThisGaragePlease = garageSlotDoorsImp.handleThisGaragePlease(garage);
                    if (handleThisGaragePlease == 0) {
                        result = "done";
                    } else {
                        result = "failed";
                    }

                } else {
                    result = "hasData";

                }

            }

        }
        return result;

    }
}

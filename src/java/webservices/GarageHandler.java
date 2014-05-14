/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

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

    public String handleThisGarage(String garageAsString) {
        Gson gson = new Gson();
        Garage garage = gson.fromJson(garageAsString, GObjects.Garage.class);
        return garageSlotDoorsImp.handleThisGaragePlease(garage) + " ";

    }
}

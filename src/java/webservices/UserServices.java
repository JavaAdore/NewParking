/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAOS.GarageImp;
import DAOS.UserImp;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.Users;
import utils.Utils;

/**
 *
 * @author orcl
 */
@Path("/User")
public class UserServices {

    @Path("updateProfile")
    @POST
    public String updateProfile(@FormParam("id") int id, @FormParam("userName") String userName, @FormParam("email") String email, @FormParam("password") String password) {
        System.out.println("update profile web service has been called");
        if (password.length() >= 6 && password.length() <= 25) {
            Users users = new Users(id, userName, email, password);
            return UserImp.getInstance().updateProfile(users) + "";
        }
        // validation exception
        return -4 + "";
    }

    @Path("login")
    @GET
    public String login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        JSONObject obj = new JSONObject();
        Users user = UserImp.getInstance().login(userName, password);

        if (user == null) {
            obj.put("error", "-1");
        } else {
            obj.put("error", "0");
            obj.put("userId", user.getUserId());
            obj.put("userName", (user.getUserName() != null) ? user.getUserName() : " ");
            obj.put("firstName", (user.getFirstName() != null) ? user.getFirstName() : " ");
            obj.put("lastName", (user.getLastName() != null) ? user.getLastName() : " ");
            obj.put("email", (user.getEmail() != null) ? user.getEmail() : " ");
            obj.put("password", (user.getPassword() != null) ? user.getPassword() : " ");

            if (user.getBirthDate() != null) {
                obj.put("birthDate", Utils.toString(user.getBirthDate()));
            } else {
                obj.put("birthDate", " ");
            }
            obj.put("gender", (user.getGender() != null) ? user.getGender() : " ");
            obj.put("phone", (user.getPhone() != null) ? user.getPhone() : " ");
        }
        return obj.toString();

    }

    @Path("register")
    @GET
    public String register(@QueryParam("userName") String userName, @QueryParam("email") String email, @QueryParam("password") String password) {

        UserImp userImp = UserImp.getInstance();

        Users user = new Users(userName, email, password);
        int result = userImp.register(user);
        JSONObject obj = new JSONObject();
        obj.put("error", result);
        switch (result) {
            case -3:
                //    out.print("this email adready registered");

                break;
            case -2:
                //out.print("user name is already registered");
                break;
            case -1:
                // out.print("some error happend please contact adminstrator");
                break;
            case 0:
                // out.print("registration has been done successfully");
                user = userImp.getUserByEmail(email);
                if (user != null) {

                    obj.put("userId", user.getUserId());
                    obj.put("userName", (user.getUserName() != null) ? user.getUserName() : " ");
                    obj.put("firstName", (user.getFirstName() != null) ? user.getFirstName() : " ");
                    obj.put("lastName", (user.getLastName() != null) ? user.getLastName() : " ");
                    obj.put("email", (user.getEmail() != null) ? user.getEmail() : " ");
                    obj.put("password", (user.getPassword() != null) ? user.getPassword() : " ");

                    if (user.getBirthDate() != null) {
                        obj.put("birthDate", Utils.toString(user.getBirthDate()));
                    } else {
                        obj.put("birthDate", " ");
                    }
                    obj.put("gender", (user.getGender() != null) ? user.getGender() : " ");
                    obj.put("phone", (user.getPhone() != null) ? user.getPhone() : " ");

                }
                break;
        }
        return obj.toString();

    }

    @Path("visitsHistory")
    @POST
    public String getVisitHistory(@FormParam("id") int userId) {

        JSONObject json = new JSONObject();

        System.out.println("get history has been visited ");
        return UserImp.getInstance().getVisitingHistory(userId).toString();
    }

    @Path("addApplicationFeedback")
    @POST
    public String addAppicationFeedback(@FormParam("id") String userId, @FormParam("message") String feedback) {
        System.out.println("feedback webervice has caled");
        if (userId != null) {
            try {

                return UserImp.getInstance().addApplciationFeedback(Integer.parseInt(userId), feedback) + "";

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return "";

    }

    @Path("addGarageFeedback")
    @POST
    public String addGarageFeedback(@FormParam("userId") String userId, @FormParam("garageId") int garageId, @FormParam("message") String feedback) {
        System.out.println("feedback webervice has caled");
        if (userId != null) {
            try {
                return GarageImp.getInstance().addFeedback(Integer.parseInt(userId), garageId, feedback) + "";

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";

    }
    
    @Path("getAboutUs")
    @POST
    public String getAboutUs(@FormParam("garageId") String garageId) {
        System.out.println("feedback webervice has caled");
        if (garageId != null) {
            try {
                return GarageImp.getInstance().prepapreAboutUs(Integer.parseInt(garageId));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";

    }
    
    

}

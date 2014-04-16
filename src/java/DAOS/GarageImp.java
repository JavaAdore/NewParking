
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Address;
import pojo.ContactNumber;
import pojo.DeleteEmployeeSchedule;
import pojo.DeleteGarageSchedule;
import pojo.EmailAddress;
import pojo.FaxContact;
import pojo.Feedback;
import pojo.Garage;
import pojo.GarageDoors;
import pojo.GarageStatus;
import pojo.Map;
import pojo.Users;
import utils.Constants;
import utils.CoordinateHelper;
import utils.GeoLocation;
import utils.Regex;
import utils.Utils;
import utils.WrappedGarage;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class GarageImp implements GarageDAO {

    private final Session garageSession = ConnectionHandler.getGarageSession();
    private static GarageImp instance;

    public static GarageImp getInstance() {
        if (instance == null) {
            instance = new GarageImp();
        }
        return instance;
    }

    private GarageImp() {
    }

    // will be called withing another session
    public Garage getGarage(int garageId) {
        Garage garage = (Garage) garageSession.get(Garage.class, garageId);
        garageSession.refresh(garage);
        return garage;
    }

    public Garage getGarage(String garageTitle) {
        Query query = garageSession.createQuery("from Garage where upper(title) like ?");
        query.setString(0, garageTitle.toUpperCase());
        Garage garage = (Garage) query.uniqueResult();
        if (garage != null) {
            garageSession.refresh(garage);
        }
        return garage;
    }

    public int deleteGarage(String garageTitle) {
        int result = -2;
        Garage garage = getGarage(garageTitle);
        if (garage != null) {
            result = deleteGarage(garage.getGarageId());
        }
        return result;

    }

    public int deleteGarage(int garageId) 
    {
        int result = 0;
        try {
            garageSession.beginTransaction();
            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                garageSession.delete(garage);
                deleteFromDeletePlan(garageId);
                System.out.println(String.format("dear mahmoud kindly be informed that garage %s has been deleted", garageId));

            }
            garageSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;

        }
        return result;
    }

    // dont forget to make history table to trace up these stuffs
    public int addGarage(Map map, Garage garage, Address address) {
        int result = 0;
        try {
//            if (getGarage(garage.getTitle()) != null) {
//                return -2;
//            }

            garageSession.beginTransaction();
            garageSession.persist(map);
            garageSession.persist(address);
            garageSession.persist(garage);
            garage.setMap(map);
            garage.setAddress(address);
            garageSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;

        }
        return result;
    }

    public int addGarage(int mapid, Garage garage) {
        int result = 0;
        try {
            garageSession.beginTransaction();
            Map map = (Map) garageSession.get(Map.class, mapid);
            garageSession.persist(map);
            garageSession.persist(garage);
            garage.setMap(map);

        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;

        } finally {
            garageSession.getTransaction().commit();
        }
        return result;
    }

    private Garage generateGarage(String title, String country, String city, double hourRateInRush, double hourRateOutOfRush, int slots, int doors, double lon, double lat) {

        return new Garage(title, country, city, hourRateInRush, hourRateOutOfRush, slots, doors, lon, lat);
    }

    private Map generateMap(String mapUrl, double ratio, int width, int height, String unit) {
        return new Map(mapUrl, ratio, width, height, unit);
    }

    public static void main(String[] args) {

        GarageImp.getInstance().myFunction();

    }

    public void myFunction() {

    }

    public void add(Object obj) {
        garageSession.beginTransaction();
        garageSession.persist(obj);
        garageSession.getTransaction().commit();

    }

    public ArrayList<Garage> getAllGarages() {
        Query q = garageSession.createQuery("from Garage");
        ArrayList<Garage> garages = (ArrayList<Garage>) q.list();
        return garages;
    }

    public ArrayList<WrappedGarage> getNearGarages(double lat, double lon) {
        GeoLocation geoLocation = GeoLocation.fromDegrees(lat, lon);
        GeoLocation[] boundingCoordinates = geoLocation.boundingCoordinates();
        CoordinateHelper coordinateHelper = new CoordinateHelper(boundingCoordinates);
        double minLon = coordinateHelper.getMinLon();
        double maxLon = coordinateHelper.getMaxLon();
        double minLat = coordinateHelper.getMinLat();
        double maxLat = coordinateHelper.getMaxLat();
        ArrayList<WrappedGarage> output = new ArrayList<WrappedGarage>();
        try {
            Query q = garageSession.createSQLQuery(" select Garage.title , sum(GarageStatus.status), count(GarageStatus.status) ,Garage.Lat , Garage.Lon     from Garage , GarageStatus   where  Garage.lon>=? and Garage.lon<=? and Garage.lat >= ? and Garage.lat <= ? and Garage.garageid =  GarageStatus.garage_garageid  group by Garage.garageId ,Garage.title  , Garage.Lat ,Garage.lon  ");
            q.setDouble(0, coordinateHelper.getMinLon());
            q.setDouble(1, coordinateHelper.getMaxLon());
            q.setDouble(2, coordinateHelper.getMinLat());
            q.setDouble(3, coordinateHelper.getMaxLat());
            List<Object[]> result = (List<Object[]>) q.list();
            for (Object[] g : result) {
                WrappedGarage tempWrappedGarage = new WrappedGarage();
                String title = (String) g[0];
                BigDecimal numberOfSlots = (BigDecimal) g[1];
                BigDecimal numberOfAvailableSlots = (BigDecimal) g[2];
                BigDecimal latitude = (BigDecimal) g[3];
                BigDecimal lontitude = (BigDecimal) g[4];
                tempWrappedGarage.setGarageId(getGarage(title).getGarageId());
                tempWrappedGarage.setGarageName(title);
                tempWrappedGarage.setNumberOfBusySlots(new Integer(numberOfSlots.toString()));
                tempWrappedGarage.setNumerOfAvailableSlots(new Integer(numberOfAvailableSlots.toString()));
                tempWrappedGarage.setLatitude(latitude);
                tempWrappedGarage.setLontitude(lontitude);
                output.add(tempWrappedGarage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output;
    }

    public String getImagePath(int id) {
        String garagePath = "";
        try {
            Garage garage = (Garage) garageSession.get(Garage.class, id);
            garagePath = garage.getMap().getMapUrl();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return garagePath;
    }

    public String setEnabled(int garageId, int enabled) {
        String result = "no such garage";
        Garage garage = getGarage(garageId);

        if (garage != null) {
            garage.setEnabled(enabled);
            switch (updateGarage(garage)) {
                case 0:
                    result = "garage availability updated";
                    break;
                case -1:
                    result = "looks like some error happend please contact admistrator";
                    break;
            }
        }
        return result;

    }

    public int updateGarage(Garage garage) {
        try {
            garageSession.beginTransaction();
            garageSession.persist(garage);
            garageSession.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int addContact(int garageId, String contact, String type) {

        try {
            garageSession.beginTransaction();

            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                switch (type) {
                    case Constants.FAX:

                        FaxContact fax = new FaxContact();
                        if (Double.isNaN(new Double(contact))) {
                            return Constants.NOT_PHONE_NUMBER;
                        }
                        FaxContact faxContact = new FaxContact(contact);
                        garageSession.persist(faxContact);

                        garage.getFaxNumbers().add(faxContact);
                        break;
                    case Constants.EMAIL:
                        if (!contact.matches(Regex.EMAIL_REGEX)) {
                            return Constants.NOT_AN__EMAIL;
                        }
                        EmailAddress emailAddress = new EmailAddress(contact);
                        garageSession.persist(emailAddress);
                        garage.getEmails().add(emailAddress);
                        break;
                    case Constants.PHONE:
                        if (Double.isNaN(new Double(contact))) {
                            return Constants.NOT_PHONE_NUMBER;
                        }
                        ContactNumber contactNumber = new ContactNumber(contact);
                        garageSession.persist(contactNumber);
                        garage.getContactNumbers().add(contactNumber);
                        break;
                }
                garageSession.getTransaction().commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Constants.FAILED;
        }
        return Constants.SUCCESS;
    }

    public int addFeedback(int userId, int garageId, String feedbackBody) {
        Feedback feedback = new Feedback(new Users(userId), new Garage(garageId), feedbackBody);

        try {
            garageSession.beginTransaction();
            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {

                garageSession.persist(feedback);
                garage.getFeedbacks().add(feedback);

            }
            garageSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    }

    public ArrayList<DeleteGarageSchedule> getDeleteGarageSchedule() {
        return (ArrayList<DeleteGarageSchedule>) garageSession.createQuery("from  DeleteGarageSchedule").list();

    }

    public int addToDeletePlan(DeleteGarageSchedule deleteGarageSchedule) {

        try {
            garageSession.beginTransaction();
            Garage tempGarage = (Garage) garageSession.get(Garage.class, deleteGarageSchedule.getGarageId());
            if (tempGarage != null) {
                tempGarage.setEnabled(0);
                garageSession.persist(deleteGarageSchedule);

            }
            garageSession.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            return utils.Constants.FAILED;
        }

        return utils.Constants.SUCCESS;
    }

    public void deleteFromDeletePlan(int id) {
        try {
            Query query = garageSession.createQuery("delete from DeleteGarageSchedule where garageId=:id ");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

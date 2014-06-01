
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import geolocator.AddressConverter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Address;
import pojo.ContactNumber;
import pojo.DeleteGarageSchedule;
import pojo.EmailAddress;
import pojo.FaxContact;
import pojo.Feedback;
import pojo.Garage;
import pojo.InActivePeriod;
import pojo.Map;
import pojo.Users;
import utils.AboutUs;
import utils.Constants;
import utils.CoordinateHelper;
import utils.GeoLocation;
import utils.Regex;
import utils.WrappedGarage;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class GarageImp {

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
        if (garage != null) {
//            garageSession.refresh(garage);
        }
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

    public int deleteGarage(int garageId) {
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

        deleteGarage(3);
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
            Query q = garageSession.createSQLQuery(" select Garage.title , sum(GarageStatus.status), count(GarageStatus.status) ,Garage.Lat , Garage.Lon     from Garage , GarageStatus   where  Garage.lon>=? and Garage.lon<=? and Garage.lat >= ? and Garage.lat <= ? and Garage.garageid =  GarageStatus.garage_garageid  group by Garage.garageId ,Garage.title  , Garage.lat ,Garage.lon  ");
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
            // if (enabled==0) {

            garage.setEnabled(enabled);
            //  }
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

    public synchronized int inActivateGarage(Garage garage) {

        Transaction beginTransaction = null;
        try {
            beginTransaction = garageSession.beginTransaction();
            garageSession.persist(garage);
            garageSession.persist(new InActivePeriod(garage));
            garageSession.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            if (beginTransaction != null) {
                beginTransaction.commit();
            }

        }
        return 0;
    }

    public synchronized int ActivateGarage(int garageId) {
        int result = Constants.FAILED;
        Transaction trans = null;
        try {
            trans = garageSession.beginTransaction();
            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                Query q = garageSession.createQuery("from InActivePeriod where garage=:garage order by id desc");

                q.setParameter("garage", garage);
                q.setMaxResults(1);
                InActivePeriod tempGarage = (InActivePeriod) q.uniqueResult();
                if (tempGarage != null) {
                    tempGarage.setToDate(new Timestamp(new Date().getTime()));
                }
                deleteFromDeletePlan(garage.getGarageId());
                garage.setEnabled(1);
                result = Constants.SUCCESS;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            if (trans != null) {
                trans.commit();
            }

        }
        return result;
    }

    public int deActivateGarage(int garageId) {
        int result = Constants.FAILED;
        Transaction beginTransaction = null;
        try {
            beginTransaction = garageSession.beginTransaction();
            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                garageSession.persist(new InActivePeriod(garage));
                garage.setEnabled(0);
            }
            result = Constants.SUCCESS;

        } catch (Exception ex) {
            result = Constants.FAILED;
            ex.printStackTrace();
        } finally {
            if (beginTransaction != null) {
                beginTransaction.commit();

            }

        }
        return result;
    }

    public int addContact(int garageId, String contact, String type) {

        try {
            garageSession.beginTransaction();
            System.out.println(contact);
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
        int result = utils.Constants.SUCCESS;
        Transaction transaction = null;
        try {
            transaction = garageSession.beginTransaction();
            Garage tempGarage = (Garage) garageSession.get(Garage.class, deleteGarageSchedule.getGarageId());
            if (tempGarage != null) {
                tempGarage.setEnabled(0);
                garageSession.persist(new InActivePeriod(new Garage(tempGarage.getGarageId())));
                garageSession.persist(deleteGarageSchedule);
            } else {

                result = utils.Constants.NOT_FOUND;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            result = utils.Constants.FAILED;
        } finally {
            if (transaction != null) {
                transaction.commit();
            }

        }

        return result;
    }

    public void deleteFromDeletePlan(int id) {

        try {

            Query query = garageSession.createQuery("delete from DeleteGarageSchedule where garageId=:id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String prepapreAboutUs(int garageId) {
        Garage garage = GarageImp.getInstance().getGarage(garageId);
        AboutUs aboutUs = new AboutUs();
        JsonObject jsonObject = new JsonObject();

        if (garage != null) {

            jsonObject.addProperty("garageName", garage.getTitle());
            
            jsonObject.addProperty("garageImage", garage.getImage());
            StringBuilder stringBuilder = new StringBuilder();
            if (garage.getDescription() != null) {
                stringBuilder.append("desc" + garage.getDescription() + "\n\n");
            }
            if (garage.getContactNumbers().size() > 0) {
                stringBuilder.append("Phone Numbers : ");
                for (ContactNumber contact : garage.getContactNumbers()) {
                    stringBuilder.append(contact.getPhoneNumber() + "\n");
                }
                stringBuilder.append("\n");
            }
            if (garage.getFaxNumbers().size() > 0) {
                stringBuilder.append("Fax Numbers : ");
                for (FaxContact fax : garage.getFaxNumbers()) {
                    stringBuilder.append(fax.getFaxNumber() + "\n");
                }
                stringBuilder.append("\n");
            }
            if (garage.getEmails().size() > 0) {
                stringBuilder.append("Email Address : ");
                for (EmailAddress email : garage.getEmails()) {
                    stringBuilder.append(email.getEmail() + "\n");
                }
                stringBuilder.append("\n");
            }
            jsonObject.addProperty("data", (stringBuilder.toString().length() > 0) ? stringBuilder.toString() : "");
        }
        return jsonObject.toString();
    }

    public static String nullHandler(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

}

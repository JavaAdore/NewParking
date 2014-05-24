/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Garage;
import pojo.Map;
import utils.CoordinateHelper;
import utils.GeoLocation;
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

    public int deleteGarage(int garageId) {
        int result = 0;
        try {
            garageSession.beginTransaction();
            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage == null) {
                result = -2;
            } else {
                if (garage.getGarageDoors().size() > 0) {
                    Query q = garageSession.createQuery("delete from GarageSlotsDoors where doorId in :garageDoors");
                    q.setParameterList("garageDoors", garage.getGarageDoors());
                    q.executeUpdate();
                }
                garageSession.delete(garage);

                garageSession.getTransaction().commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;

        }
        return result;
    }

    // dont forget to make history table to trace up these stuffs
    public int addGarage(Map map, Garage garage) {
        int result = 0;
        try {
            if (getGarage(garage.getTitle()) != null) {
                return -2;
            }

            garageSession.beginTransaction();

            garageSession.persist(map);

            garage.setMap(map);

            garageSession.persist(garage);
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

//        Map map = GarageImp.getInstance().generateMap("d:\\myimage.jpg", 1.7, 100, 100, "meter");
//
//        Garage garage = GarageImp.getInstance().generateGarage("iti garage", "egypt", "cairo", 100, 1, 12.6, 177.5);
//
//        int result = GarageImp.getInstance().addGarage(map, garage);
//
        //   GarageImp.getInstance().getImagePath(6);
        //   ArrayList<WrappedGarage> result = GarageImp.getInstance().getNearGarages(30.071531, 31.020756);
        //   System.out.println("cc");
        GarageImp.getInstance().deleteGarage("NTA");

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
            Query q = garageSession.createSQLQuery(" select Garage.title , sum(GarageStatus.status), count(GarageStatus.status) ,Garage.Lat , Garage.Lon     from Garage , GarageStatus   where  Garage.lon>=? and Garage.lon<=? and Garage.lat >= ? and Garage.lat <= ? and Garage.garageid =  GarageStatus.garage_garageid  group by Garage.title ,Garage.slots , Garage.Lat ,Garage.lon  ");
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
            return -1;
        }
        return 0;
    }
}

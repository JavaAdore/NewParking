/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Map;

/**
 *
 * @author orcl
 */
public class MapsImp  {

    static Session mapSession = ConnectionHandler.getMapSession();
    private static MapsImp instance;

    public static MapsImp getInstance() {
        if (instance == null) {
            instance = new MapsImp();
        }
        return instance;
    }

    private MapsImp()
    {
    }
    public Map getMap(String mapName) {
        Query query = mapSession.createQuery("from Map where upper(mapName) like ?");
        query.setString(0, mapName.toUpperCase());
        Map map = (Map) query.uniqueResult();
        return map;
    }

    public Map getMap(int mapId) {
        Query query = mapSession.createQuery("from Map where  mapId = ?");
        query.setInteger(0, mapId);
        Map map = (Map) query.uniqueResult();
        return map;
    }
/*
    public int addMap(String mapName, String mapUrl, double ratio, double width, double height, String unit) {
        int result = 0;

        try {
            mapSession.beginTransaction();
            //Map map = new Map(mapUrl, String.valueOf(ratio), (int) width, (int) height, unit);

            mapSession.save(map);

        } catch (Exception ex) {
            result = -1;
        } finally {
            mapSession.beginTransaction().commit();
        }

        return 0;
    }
    * */

    public int deleteMap(String mapName) {
        int result = 0;

        try {
            mapSession.beginTransaction();
            Query query = mapSession.createQuery("delete from Map where upper(mapName) like ?");
            query.setString(0, mapName);
            query.executeUpdate();

        } catch (Exception ex) {
            result = -1;
        } finally {
            mapSession.beginTransaction().commit();
        }

        return 0;
    }

    public int deleteMap(int mapId) {
        int result = 0;

        try {
            mapSession.beginTransaction();
            Map map = (Map) mapSession.get(Map.class, mapId);
            mapSession.delete(map);

        } catch (Exception ex) {
            result = -1;
        } finally {
            mapSession.beginTransaction().commit();
        }

        return 0;
    }
    
       public static ArrayList<Map> getAllMaps() {
        Query q = mapSession.createQuery("from Map");
        ArrayList<Map> maps = (ArrayList<Map>) q.list();
        
        return maps;
    }
 
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

/**
 *
 * @author orcl
 */
import Sessions.ConnectionHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mobilegsons.visitHistory;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.*;
import utils.Utils;

public class UserImp {

    static Session userSession = ConnectionHandler.getUserSession();
    static UserImp instance;

    public static UserImp getInstance() {
        if (instance == null) {
            instance = new UserImp();
        }
        return instance;
    }

    private UserImp() {
    }

    public int register(Users user) {
        int result = 0;
        try {
            userSession.beginTransaction();
            Query q = userSession.createQuery("from Users where upper(email) like ?");
            q.setString(0, user.getEmail().toUpperCase());
            Users u = (Users) q.uniqueResult();
            if (u != null) {
                return -3;
                // doublicated email

            } else {
                q = userSession.createQuery("from Users where upper(userName) like ?");
            }
            q.setString(0, user.getUserName().toUpperCase());
            u = (Users) q.uniqueResult();
            if (u != null) {
                return -2;
                //doubleicate userName
            }
            userSession.persist(user);

        } catch (Exception ex) {
            return -1;
        } finally {
            userSession.getTransaction().commit();

        }
        return result;
    }

    public Users login(String userName, String password) {
        Users user = null;
        try {
            Query q = userSession.createQuery("from Users where lower(userName) like ? and password like ?");
            q.setString(0, userName.toLowerCase());
            q.setString(1, password);
            user = (Users) q.uniqueResult();

        } catch (Exception ex) {
        } finally {

        }
        return user;
    }

    public Users getUserByEmail(String email) {
        Users user = null;
        try {
            Query q = userSession.createQuery("from Users where upper(email) like ?");
            q.setString(0, email.toUpperCase());
            user = (Users) q.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
        return user;
    }

    public Users getUserById(int userId) {

        return (Users) userSession.get(Users.class, userId);
    }

    public int addVisit(Users user, Garage garage) {
        int result = 0;
        try {
            Query q = userSession.createQuery("from Visit where user = :user and garage =:garage");
            q.setParameter("user", user);
            q.setParameter("garage", garage);
            Visit visit = (Visit) q.uniqueResult();
            if (visit != null) {
                visit.increaseNumberOfVisits();
                visit.setVisitDate(new Date());
            } else {
                visit = new Visit(garage, user);

            }
            userSession.beginTransaction();
            userSession.persist(visit);
            userSession.getTransaction().commit();

        } catch (Exception ex) {
            result = -1;
        }
        return result;
    }

    public int updateProfile(Users user) {

        Users u = (Users) userSession.get(Users.class, user.getUserId());
        userSession.evict(u);
        try {
            if (u != null) {
                String email = user.getEmail();
                String userName = user.getUserName();

                if (!u.getEmail().equalsIgnoreCase(email)) {
                    if (getUserByEmail(user.getEmail()) != null) {
                        return -3;
                    }
                }
                if (!u.getUserName().equalsIgnoreCase(userName)) {
                    if (getUserByUserName(user.getUserName()) != null) {
                        return -2;
                    }
                }
                userSession.beginTransaction();
                userSession.saveOrUpdate(user);
                userSession.getTransaction().commit();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

        return 0;
    }

    public Users getUserByUserName(String userName) {
        Query q = userSession.createQuery("from Users where upper(userName) like ?");
        q.setString(0, userName.toUpperCase());
        return (Users) q.uniqueResult();
    }

    public String getVisitingHistory(int userId) {
        List<visitHistory> result = new ArrayList();
        JsonArray historyArray = new JsonArray();
        Gson gson = new Gson();
        Users user = (Users) userSession.get(Users.class, userId);

        if (user != null) {
            for (Visit v : user.getVisits()) {

                JsonElement toJsonTree = gson.toJsonTree(new visitHistory(v.getGarage().getTitle(), v.getNumberOfVisits(), Utils.toTime(v.getVisitDate())), visitHistory.class);
                historyArray.add(toJsonTree);
            }

        }
        System.out.println(historyArray.toString());
        return historyArray.toString();
    }

    public String getPasswordByEmail(String email) {

        Query q = userSession.createQuery("from Users where upper(email) like ?");
        q.setString(0, email.toUpperCase());
        return getPassword((Users) q.uniqueResult());

    }

    public String getPasswordByUserName(String userName) {

        Query q = userSession.createQuery("from Users where upper(userName) like ?");
        q.setString(0, userName.toUpperCase());
        return getPassword((Users) q.uniqueResult());

    }

    public String getPassword(Users user) {

        if (user != null) {
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        String passwordByUserName = UserImp.getInstance().getPasswordByUserName("acxzCxzhmed");
        System.out.println();
    }
}

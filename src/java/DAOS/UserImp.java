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
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.*;

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
            if (u != null) 
            {
                return  -3;
                
            }else
            q = userSession.createQuery("from Users where upper(userName) like ?");
            q.setString(0, user.getUserName().toUpperCase());
            u = (Users) q.uniqueResult();
            if (u != null) {
               return -2;
            }
            userSession.save(user);

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
            userSession.beginTransaction();
            Query q = userSession.createQuery("from Users where lower(userName) like ? and password like ?");
            q.setString(0, userName.toLowerCase());
            q.setString(1, password);
            user = (Users) q.uniqueResult();

        } catch (Exception ex) {
        } finally {

            userSession.getTransaction().commit();
        }
        return user;
    }

    public Users getUserByEmail(String email) {
        Users user = null;
        try {
            userSession.beginTransaction();
            Query q = userSession.createQuery("from Users where lower(email) like ?");
            q.setString(0, email.toLowerCase());
            user = (Users) q.uniqueResult();
        } catch (Exception ex) {

        } finally {
            userSession.getTransaction().commit();

        }
        return user;
    }
}

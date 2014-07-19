/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import pojo.Roles;
import Sessions.*;
import java.sql.Clob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class RoleImp {

    static Session RoleSession = ConnectionHandler.getRoleSession();
    // will be called only within another session so it's not supposed to 

    
    private static RoleImp instance;
    
    public static RoleImp getInstance()
    {
        if(instance == null)
        {
        instance = new RoleImp();
        }
        return instance;
    }
    private RoleImp(){}
    public Roles getRole(String roleName) {
        Query query = RoleSession.createQuery("from Roles where upper(roleName) like ?");
        query.setString(0, roleName.toUpperCase());
        Roles role = (Roles) query.uniqueResult();
        return role;


    }

    
    public int addRole(int roleId ,String roleName, String description) {
        int result = 0;
        try {
            RoleSession.beginTransaction();
            if (getRole(roleName) != null) {
                result = 2;
            } else {
                Roles role = new Roles(roleId,roleName, description);
                RoleSession.save(role);
                result = 0;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            RoleSession.getTransaction().commit();
        }
        return result;
    }

    
    public int addRole(int roleId ,String roleName) 
    {
        return addRole(roleId ,roleName, "");
    }
    
    public Roles getRole(int roleId) 
    {
        return (Roles)RoleSession.get(Roles.class, roleId);
    }
}

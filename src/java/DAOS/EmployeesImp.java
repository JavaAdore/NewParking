/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.AdminsActions;
import pojo.Employees;
import pojo.Garage;
import utils.EmployeeWrapper;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class EmployeesImp implements EmployeesDAO {

    Session employeeSession = ConnectionHandler.getEmployeeSession();
    private static EmployeesImp instance;

    private EmployeesImp() {
    }

    public static EmployeesImp getInstance() {
        if (instance == null) {
            instance = new EmployeesImp();
        }
        return instance;
    }

    public int addEmployee(int addedBy, Employees employee) {
        int result = 0;
        try {
            employeeSession.beginTransaction();
            if (getEmployee(employee.getEmail()) != null) {
                result = -2;
            } else {
                employeeSession.save(employee);
                result = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;

        } finally {
            employeeSession.getTransaction().commit();
            if (result == 0) {
                addAdminAction(addedBy, employee.getEmail(), 'a');
            }

        }
        return result;
    }

    public Employees getEmployee(String email) {
        Query q = employeeSession.createQuery("from Employees where upper(email) like ?");
        q.setString(0, email.toUpperCase());
        Employees emp = (Employees) q.uniqueResult();
        return emp;
    }

    public Employees getEmployee(int employeeId) {
        return (Employees) employeeSession.get(Employees.class, employeeId);
    }

    public Employees signIn(String userName, String password) {

        Query q = employeeSession.createQuery("from Employees where upper(email) like ? and password like ?");
        q.setString(0, userName.toUpperCase());
        q.setString(1, password);

        return (Employees) q.uniqueResult();

    }
    // dont forget to update once u test

    public int addAdminAction(int adminId, String employeeEmail, char actionIdentifier) {
        int result = 0;
        try {
            employeeSession.beginTransaction();

            Query q = employeeSession.createQuery("from Employees where upper(email) like ?");
            q.setString(0, employeeEmail.toUpperCase());
            Employees employee = (Employees) q.uniqueResult();

            employeeSession.save(new AdminsActions(new Employees(adminId), employee, 'a'));
        } catch (Exception ex) {
            result = -1;

        } finally {

            employeeSession.getTransaction().commit();
            employeeSession.clear();
        }
        return result;
    }

    public static void main(String[] args) {

//int result = EmployeesImp.getInstance().addEmployee("mahmoud", "mohamed", "mahmoud@gmail.com", "123456", "male", "12-12-2012", 1, 1);
        System.out.println(getInstance().getAllAdminsInfo(false));

    }

    public int updateEmployeeWithoutRetriving(Employees emp) {
        int result = 0;
        try {
            employeeSession.beginTransaction();

            employeeSession.saveOrUpdate(emp);

            employeeSession.getTransaction().commit();
        } catch (Exception ex) {
            result = -1;

        }
        return result;
    }

    public int updateProfile(Employees updatedEmployee) {
        int result = 0;
        try {

            employeeSession.beginTransaction();

            Employees emp = (Employees) employeeSession.get(Employees.class, updatedEmployee.getEmployeeId());
            if (emp != null) {

                emp.setPassword(updatedEmployee.getPassword());

                emp.setFirstName(updatedEmployee.getFirstName());

                emp.setLastName(updatedEmployee.getLastName());

                emp.setBirthDate(updatedEmployee.getBirthDate());

                emp.setGarage(updatedEmployee.getGarage());

                emp.setRoles(updatedEmployee.getRoles());

                emp.setGender(updatedEmployee.getGender());

                employeeSession.saveOrUpdate(emp);

            } else {

                result = -2;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            employeeSession.getTransaction().commit();
        }
        return result;
    }

    public int updateProfileByEmail(Employees updatedEmployee) {
        int result = 0;
        try {

            employeeSession.beginTransaction();
            Query q = employeeSession.createQuery("from Employees where upper(email) like ?");
            q.setString(0, updatedEmployee.getEmail().toUpperCase());

            Employees emp = (Employees) q.uniqueResult();
            if (emp != null) {

                emp.setPassword(updatedEmployee.getPassword());
                emp.setFirstName(updatedEmployee.getFirstName());
                emp.setLastName(updatedEmployee.getLastName());
                emp.setRoles(updatedEmployee.getRoles());
                emp.setBirthDate(updatedEmployee.getBirthDate());

            } else {

                result = -2;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            employeeSession.getTransaction().commit();
        }
        return result;
    }

    public int revokePrivilege(Employees updatedEmployee) {
        int result = 0;
        try {

            employeeSession.beginTransaction();

            Employees emp = (Employees) employeeSession.get(Employees.class, updatedEmployee.getEmployeeId());
            if (emp != null) {
                emp.setGarage(null);
                emp.setRoles(null);
                addAdminAction(1, emp.getEmail(), 'r');
            } else {

                result = -2;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            employeeSession.getTransaction().commit();
        }
        return result;
    }

    public int deleteMemember(Employees employee) {

        int result = 0;
        try {

            employeeSession.beginTransaction();

            Employees emp = (Employees) employeeSession.get(Employees.class, employee.getEmployeeId());
            Query q;
            if (emp != null) {

                q = employeeSession.createQuery("delete from AdminsActions where admin = :admin or  employee=:employee");
                q.setParameter("admin", emp);
                q.setParameter("employee", emp);
                q.executeUpdate();

                employeeSession.delete(emp);
            } else {

                result = -2;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            employeeSession.getTransaction().commit();
        }
        return result;
    }

    public ArrayList<EmployeeWrapper> getAllAdminsInfo(boolean assigned) {
        ArrayList<EmployeeWrapper> output = new ArrayList<EmployeeWrapper>();
        Query query;

        if (assigned) {
            query = employeeSession.createQuery("select e.employeeId , e.firstName ,e.lastName , e.email  from Employees e where e.roles.roleId =2 and e.garage.garageId is not null");
        } else {
            query = employeeSession.createQuery("select e.employeeId , e.firstName ,e.lastName   , e.email from Employees e where e.roles.roleId =2 and e.garage.garageId is null");
        }

        List result = query.list();

        for (int i = 0; i < result.size(); i++) {
            int employeeId = (Integer) ((Object[]) result.get(i))[0];
            String firstName = (String) ((Object[]) result.get(i))[1];
            String lastName = (String) ((Object[]) result.get(i))[2];
            String email = (String) ((Object[]) result.get(i))[3];

            output.add(new EmployeeWrapper(employeeId, firstName, lastName, email));

        }

        return output;
    }

    public ArrayList<Employees> getAllEmployees() {

        Query q = employeeSession.createQuery("from Employees");

        ArrayList<Employees> employees = (ArrayList<Employees>) q.list();

        return employees;
    }

    public ArrayList<Employees> getAllEmployeesByGarageId(int garageId) {
        Query q = employeeSession.createQuery("from Employees e where garage = :garage");
        q.setParameter("garage", new Garage(garageId));
        ArrayList<Employees> employees = (ArrayList<Employees>) q.list();
        return employees;
    }

}

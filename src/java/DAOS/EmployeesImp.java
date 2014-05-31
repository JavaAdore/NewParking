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
import org.hibernate.Transaction;
import pojo.AdminsActions;
import pojo.DeleteEmployeeSchedule;
import pojo.Employees;
import pojo.Garage;
import utils.EmployeeWrapper;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class EmployeesImp {

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
        Transaction beginTransaction;
        int result = utils.Constants.FAILED;
        try {
            if (getEmployee(employee.getEmail()) != null) {
                result = -2;
            } else {

                Employees adder = (Employees) employeeSession.get(Employees.class, addedBy);
                if (adder != null) {
                   
                        beginTransaction = employeeSession.beginTransaction();
                        employeeSession.save(employee);
                        employeeSession.save(new AdminsActions(adder, employee, 'a'));
                        beginTransaction.commit();
                        result = utils.Constants.SUCCESS;
                    

                }
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // leave it failed as it;s

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
        Employees emp = (Employees) employeeSession.get(Employees.class, employeeId);
        if (emp != null) {
            employeeSession.refresh(emp);
        }
        return emp;
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

        } catch (Exception ex) {
            result = -1;

        } finally {

            employeeSession.getTransaction().commit();
            employeeSession.clear();
        }
        return result;
    }

    public static void main(String[] args) {

        Employees signIn = EmployeesImp.getInstance().signIn("mah"
                + "moud@gmail.com", "123456");
        System.out.println("fdsafdasfdsa");

    }

    public int updateProfile(Employees updatedEmployee) {
        int result = 0;
        try {

            employeeSession.beginTransaction();

            Employees emp = (Employees) employeeSession.get(Employees.class, updatedEmployee.getEmployeeId());
            if (emp != null) {
                employeeSession.persist(updatedEmployee);

            } else {

                result = -2;
            }
            employeeSession.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;
        }
        return result;
    }

    public int assignAdmin(int adminId, int garageId) {
        try {
            employeeSession.beginTransaction();
            Garage garage = (Garage) employeeSession.get(Garage.class, garageId);
            Employees emp = (Employees) employeeSession.get(Employees.class, adminId);

            if (emp != null && garage != null) {
                emp.setGarage(garage);

            }

            employeeSession.getTransaction().commit();

            return utils.Constants.SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            return utils.Constants.FAILED;

        }

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

    public int deleteMemember(int memeberId) {

        int result = 0;
        try {

            employeeSession.beginTransaction();

            Employees emp = (Employees) employeeSession.get(Employees.class, memeberId);
            Query q;
            if (emp != null) {

                q = employeeSession.createQuery("delete from AdminsActions where admin = :admin or  employee=:employee");
                q.setParameter("admin", emp);
                q.setParameter("employee", emp);
                q.executeUpdate();

                employeeSession.delete(emp);
                deleteFromDeletePlan(memeberId);
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

    public ArrayList<DeleteEmployeeSchedule> getDeleteEmployeeSchedule() {
        return (ArrayList<DeleteEmployeeSchedule>) employeeSession.createQuery("from  DeleteEmployeeSchedule").list();

    }

    public int addToDeletePlan(DeleteEmployeeSchedule deleteEmployeeSchedule) {
        int result = utils.Constants.SUCCESS;
        try {
            employeeSession.beginTransaction();
            Employees employee = (Employees) employeeSession.get(Employees.class, deleteEmployeeSchedule.getEmployeeId());
            if (employee != null) {
                employee.setActive(0);
                employeeSession.persist(deleteEmployeeSchedule);
            } else {
                result = utils.Constants.NOT_FOUND;
            }
            employeeSession.getTransaction().commit();
            System.out.println(String.format("dear mahmoud kindly be informed that user %s has been deleted", deleteEmployeeSchedule.getEmployeeId()));
        } catch (Exception ex) {
            ex.printStackTrace();
            result = utils.Constants.FAILED;
        }

        return result;
    }

    public void deleteFromDeletePlan(int in) {
        try {
            Query query = employeeSession.createQuery("delete from DeleteEmployeeSchedule where employeeId=:id ");
            query.setParameter("id", in);
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int updateProfile(int id, String password, String confirmPassword) {
        int result = utils.Constants.FAILED;
        if (utils.Validator.areTheSame(password, confirmPassword)) {
            try {
                employeeSession.beginTransaction();
                Employees emp = (Employees) employeeSession.get(Employees.class, id);
                if (emp != null) {
                    emp.setPassword(password);
                    result = utils.Constants.SUCCESS;
                    employeeSession.getTransaction().commit();
                }

            } catch (Exception ex) {
                // leave result as it's 
            }

        }
        return result;
    }

    public int updateProfile(String email, String password, String confirmPassword, String garage, String isActive) {

        int result = utils.Constants.SUCCESS;
        Transaction updateProfileTrasaction = null;
        if (!utils.Validator.validateUpdateProfile(email, password, confirmPassword)) {
            return utils.Constants.INVALID_INPUTS;
        }

        try {
            updateProfileTrasaction = employeeSession.beginTransaction();
            Query q = employeeSession.createQuery("from Employees where upper(email) like ?");
            q.setString(0, email.toUpperCase());
            Employees emp = (Employees) q.uniqueResult();
            if (emp != null) {
                if (!emp.getRoles().getRoleName().equalsIgnoreCase(utils.EmployeeRole.SERVICE_PROVIDER)) {
                    if (utils.Validator.validateUpdateProfile(garage, isActive)) {
                        int garageId = Integer.parseInt(garage);
                        if (emp.getGarage() != null) {
                            if (emp.getGarage().getGarageId() != garageId) {
                                emp.setGarage(GarageImp.getInstance().getGarage(garageId));
                            }
                        } else {
                            emp.setGarage(GarageImp.getInstance().getGarage(garageId));
                        }
                    }

                    int active = Integer.parseInt(isActive);
                    {
                        if (emp.getActive() != active) {
                            if (active == 1) {
                                EmployeesImp.getInstance().deleteFromDeletePlan(emp.getEmployeeId());
                                emp.setActive(active);
                            } else if (active == 0) {
                                emp.setActive(active);
                            }
                        }
                    }
                }
                emp.setPassword(password);
            } else {

                emp.setPassword(password);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return utils.Constants.FAILED;

        } finally {
            if (updateProfileTrasaction != null) {
                updateProfileTrasaction.commit();
            }

        }
        return utils.Constants.SUCCESS;
    }
}


package utils;

import DAOS.UserImp;
import DAOS.UsersDAO;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import pojo.Employees;
import pojo.Map;
import pojo.Users;


public class DatabaseDAO {

    private static DatabaseDAO DAO ;
    
    private static SessionFactory factory;
    
    static 
    {
        
        DAO = new DatabaseDAO()  ; 
        
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
   
    }

    private DatabaseDAO(){
        
    }
    
    
    public static DatabaseDAO getInstance(){
        
        return DAO  ;
    }
    
    private Session openSession(){
        
      Session session =  factory.openSession() ;
      
      session.beginTransaction() ; 
      
      return session ; 
    }
    
    private void closeSession(Session session){
      
        session.getTransaction().commit(); 
        
        session.clear(); 
        
        session.close();
    
    }
    
    public Employees signIn(String email , String password ){
        
        Session session = openSession() ; 
        
        Query query = session.createQuery("from Employees e where e.email =:email and e.password =:password") ; 
       
        query.setString("email", email);
       
        query.setString("password", password) ; 
        
        List result =  query.list() ; 

        closeSession(session);
        
        if(result.size() == 0 )           
        
            return null ; 
       
        else
        
            return (Employees)result.get(0) ; 
    }
    
    
    public static void main(String[]aa){
        
             
       //Users user = new Users("mahmoud","mohamed","mahmoudEldfsataieib", "mahmoudMohamed@gmail.com", "123456", "199dsa0-09-09", "m ", "01112256793"); 
       Users u = UserImp.getInstance().login("mahmoud@gmail.com", "123456");
       
       
       System.out.println(u==null);
        
    }
    
    
}

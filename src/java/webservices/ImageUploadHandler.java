//package webservices;
//
///**
// *
// * @author orcl
// */
//
//import com.sun.jersey.core.header.FormDataContentDisposition;
////import com.sun.jersey.multipart.FormDataParam;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.MediaType;
//import oracle.jdbc.OracleDriver;
//
///**
// *
// * @author orcl
// */
//@Path("test")
//public class ImageUploadHandler {
//    
//
//
//    @Path("myForm")
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public void myString(@FormDataParam("theName") String theName,@FormDataParam("thePassword") String thePassword, @FormDataParam("file") InputStream is, @FormDataParam("file") FormDataContentDisposition details) {
//        String path = "D:\\" + details.getFileName();
//        try {
//            System.out.println(theName);
//            OutputStream os = new FileOutputStream(new File(path));
//            handleFile(theName, thePassword ,is);
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            
//            while ((read = is.read(bytes)) != -1) {
//                os.write(bytes, 0, read);
//            }
//            os.flush();
//            os.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        ;
//    }
//
//    private void handleFile(String userName, String password ,InputStream is) {
//        
//        try {
//            DriverManager.registerDriver(new OracleDriver());
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:myuser/myuser@localhost:1521/XE");
//            PreparedStatement ps = connection.prepareStatement("insert into clients values(?,?,?)");
//            ps.setString(1, userName);
//            ps.setString(2,password);
//            ps.setBlob(3, is);
//            ps.executeUpdate();
//            
//            
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//    }
//
//}

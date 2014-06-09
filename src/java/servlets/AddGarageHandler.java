package servlets;

import DAOS.GarageImp;
import errors.ErrorMessage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Garage;
import pojo.Map;

public class AddGarageHandler extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private File file;
    private String jspFilePath;
    private ArrayList<String> parameterNames = new ArrayList<String>();
    private ArrayList<String> parameterValues = new ArrayList<String>();
    private String theNameOfTheFile;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, Exception {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String jspFilePath = getServletContext().getRealPath(request.getRequestURI()).replace('\\', '/');

        jspFilePath = jspFilePath.subSequence(0, jspFilePath.indexOf("/build")) + "/web/images/";

        out.println(jspFilePath);

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);

        response.setContentType("text/html");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        // factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.

        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet upload</title>");
        out.println("</head>");
        out.println("<body>");

        while (i.hasNext()) {
            FileItem fi = (FileItem) i.next();
            if (!fi.isFormField()) {
                // Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String fileName = fi.getName();
                String contentType = fi.getContentType();
                //  boolean isInMemory = fi.isInMemory();
                long sizeInBytes = fi.getSize();
                // Write the file
                if (fileName.lastIndexOf("\\") >= 0) 
                {
                    file = new File(jspFilePath + fileName.substring(fileName.lastIndexOf("\\")));
                            
                } else {

                    file = new File(jspFilePath
                            + fileName.substring(fileName.lastIndexOf("\\") + 1));
                }
                fi.write(file);
                out.println("Uploaded Filename: " + theNameOfTheFile + "<br>");
            } else {
                out.print(fi.getFieldName() + " " + fi.getString());
                parameterNames.add(fi.getFieldName());
                parameterValues.add(fi.getString());
            }
        }
        int result = save();
        switch (result) {
            case -2:
                request.setAttribute("error", new ErrorMessage("This garage title is already added"));
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
            case -1:
                request.setAttribute("error", new ErrorMessage("Looks Like some error happend please contact adminstrator"));
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
            case 0:
                request.setAttribute("error", new ErrorMessage("Garage added"));
                request.getRequestDispatcher("addgarage.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int save() {

        Garage g = new Garage();

        String garageName = parameterValues.get(0);

        String country = parameterValues.get(1);

        String city = parameterValues.get(2);

        int slots = Integer.parseInt(parameterValues.get(3));

        int hourRateInRushHours = Integer.parseInt(parameterValues.get(4));

        String MapUrl = file.getName();

        double ratio = Double.parseDouble(parameterValues.get(5));

        int width = Integer.parseInt(parameterValues.get(6));

        int heigth = Integer.parseInt(parameterValues.get(7));

        String unit = parameterValues.get(8);

        double lon = Double.parseDouble(parameterValues.get(9));

        double lat = Double.parseDouble(parameterValues.get(10));

        g.setCity(city);
        g.setCountry(country);
        g.setHourRateInRush(hourRateInRushHours);
        g.setLat(lat);
        g.setLon(lon);
        g.setSlots(slots);
        g.setTitle(garageName);

        Map map = new Map();

        map.setHeight(heigth);
        map.setWidth(width);
        map.setUnit(unit);
        map.setRatio(ratio);
        map.setMapUrl(MapUrl);

        return GarageImp.getInstance().addGarage(map, g);

    }
}
//        try {
//            
//            title = request.getParameter("title");
//            country = request.getParameter("country");
//            city = request.getParameter("city");
//            doors = Integer.parseInt(request.getParameter("doors"));
//            mapid = Integer.parseInt(request.getParameterValues("map")[0]);
//            lon = Double.parseDouble(request.getParameter("lon"));
//            lat = Double.parseDouble(request.getParameter("lat"));
//            
//            Garage garage= new Garage(title, country, city, doors, doors, lon, lat);
//            
//            GarageImp garageImp = GarageImp.getInstance();
//            result = garageImp.addGarage(mapid,garage);
//            if(result==0)
//            {
//                response.sendRedirect("addgarage.jsp");
//            }else
//            {
//                out.println("error");
//            }
//            
//        } finally {
//          
//            out.close();
//        }

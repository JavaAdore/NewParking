package servlets;

import DAOS.GarageImp;
import errors.ErrorMessage;
import geolocator.AddressConverter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import pojo.Address;
import pojo.Garage;
import pojo.Map;

public class UpdateGarageHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, Exception {

        response.setContentType("text/html;charset=UTF-8");

        boolean isMultipart;
        String filePath;
        File file = null;
        String jspFilePath;
        ArrayList<String> parameterNames = new ArrayList<String>();
        ArrayList<String> parameterValues = new ArrayList<String>();
        String theNameOfTheFile;
        String fileExtention;
        Map map;
        String MapUrl = "";
        String oldGarageURL = "";
        Garage currentGarage;
        currentGarage = (Garage) request.getSession().getAttribute("currentGarage");
        if (currentGarage != null) {
            oldGarageURL = currentGarage.getMap().getMapUrl();
        } else {

            request.getSession().invalidate();
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    cookies[i].setMaxAge(0);
                    ((HttpServletResponse) response).addCookie(cookies[i]);
                }
            }
            response.sendRedirect("login.jsp");
        }
        PrintWriter out = response.getWriter();

        jspFilePath = getServletContext().getRealPath(request.getRequestURI()).replace('\\', '/');

        jspFilePath = jspFilePath.subSequence(0, jspFilePath.indexOf("/build")) + "/web/images/";

        out.println(jspFilePath);

        isMultipart = ServletFileUpload.isMultipartContent(request);

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.

        // Parse the request to get file items.
        List fileItems = upload.parseRequest(request);

        // Process the uploaded file items
        Iterator i = fileItems.iterator();

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
                if (sizeInBytes != 0) {
                    if (fileName.lastIndexOf("\\") >= 0) {
                        fileExtention = fileName.substring(fileName.lastIndexOf("\\", fileName.length()));
                        file = new File(jspFilePath + fileName.substring(fileName.lastIndexOf("\\")));

                    } else {

                        file = new File(jspFilePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                }
            } else {
                parameterNames.add(fi.getFieldName());
                parameterValues.add(fi.getString().trim());
            }
        }

        int result = save(parameterValues, currentGarage, file);
        switch (result) {
            case -2:
                request.setAttribute("error", new ErrorMessage("This garage title is already added"));
                request.getRequestDispatcher("LoadAllGaragesInitializer?toPage=updategarageinfo.jsp").forward(request, response);
                break;
            case -1:
                request.setAttribute("error", new ErrorMessage("Looks Like some error happend please contact adminstrator"));
                request.getRequestDispatcher("LoadAllGaragesInitializer?toPage=updategarageinfo.jsp").forward(request, response);
                break;
            case 0:
                if (file != null) {
                    new File(jspFilePath + oldGarageURL).delete();
                    file.renameTo(new File(jspFilePath + String.format("%s,%s.%s", currentGarage.getLat(), currentGarage.getLon(), FilenameUtils.getExtension(file.getName()))));
                }
                request.setAttribute("error", new ErrorMessage("Garage Updated"));
                request.getRequestDispatcher("LoadAllGaragesInitializer?toPage=updategarageinfo.jsp").forward(request, response);
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

    private int save(List<String> parameterValues, Garage currentGarage, File file) {

        String garageName = parameterValues.get(0);

        double hourRateInRushHours = Double.parseDouble(parameterValues.get(1));

        double ratio = Double.parseDouble(parameterValues.get(2));

        int width = Integer.parseInt(parameterValues.get(3));

        int heigth = Integer.parseInt(parameterValues.get(4));

        String unit = parameterValues.get(5);

        double lat = Double.parseDouble(parameterValues.get(6));

        double lon = Double.parseDouble(parameterValues.get(7));

        int enabled = Integer.parseInt(parameterValues.get(8));

        Map map = currentGarage.getMap();

        if (file != null) {
            String MapUrl = String.format("%s,%s.%s", currentGarage.getLat(), currentGarage.getLon(), FilenameUtils.getExtension(file.getName()));
            map.setMapUrl(MapUrl);
        }
        Address tempAddress = new AddressConverter().getAddress(lat, lon);
        Address address = currentGarage.getAddress();
        address.setCity(tempAddress.getCity());
        address.setCountry(tempAddress.getCountry());
        currentGarage.setHourRateInRush(hourRateInRushHours);
        currentGarage.setLat(lat);
        currentGarage.setLon(lon);
        currentGarage.setTitle(garageName);
        map.setHeight(heigth);
        map.setWidth(width);
        map.setUnit(unit);
        map.setRatio(ratio);
        currentGarage.setMap(map);
        currentGarage.setAddress(address);

        if (enabled != currentGarage.getEnabled()) {
            if (enabled == 0) {
                GarageImp.getInstance().deActivateGarage(currentGarage.getGarageId());
            } else {
                GarageImp.getInstance().ActivateGarage(currentGarage.getGarageId());

            }
        }
        currentGarage.setEnabled(enabled);
        return GarageImp.getInstance().updateGarage(currentGarage);

    }
}

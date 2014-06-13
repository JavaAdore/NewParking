package utils;

import DAOS.*;
import GObjects.Step;
import GObjects.ValueContainer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import daosint.ReportsInterface;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import pojo.DailyHistory;
import pojo.Employees;
import pojo.Garage;
import pojo.Map;
import pojo.Users;
import reportsClasses.CustomDate;
import reportsClasses.ReportHistoryRecord;

public class Utils {

    private static EmployeesImp empDao;
    private static GarageImp garageDao;
    private static MapsImp mapsDao = MapsImp.getInstance();

    static {

        empDao = EmployeesImp.getInstance();

        garageDao = GarageImp.getInstance();
    }

    public static Date totDate(String dateAsString) {
        SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatedDate.parse(dateAsString);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    public static String toString(Date date) {
        String DATE_FORMAT_NOW = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String dateAsString = sdf.format(date);
        return dateAsString;

    }

    public static UserWrapper getUserWrapper(Users user) {

        return new UserWrapper(user.getUserId(), user.getUserName(), user.getEmail(), user.getBirthDate(), user.getGender(), user.getPhone());
    }

    public static EmployeeWrapper getEmployeeWrapper(Employees employee) {

        return new EmployeeWrapper(employee.getEmployeeId(), employee.getRoles(), employee.getGarage(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }

    public static String getAllAdminsInfo(boolean assign) {

        String res = empDao.getAllAdminsInfo(assign);

        StringBuilder stringBuilder = new StringBuilder();

        for (String str : res.split(",")) {

            String infos[] = str.split(":");

            if (infos.length == 2) {
                stringBuilder.append("<option value=" + infos[0] + ">");

                stringBuilder.append(infos[1]);

                stringBuilder.append("</option>");
            }

        }
        if (stringBuilder.length() == 0) {

            stringBuilder.append("<option value= -1 >");

            stringBuilder.append("currently there is no employees");

            stringBuilder.append("</option>");
        }

        return stringBuilder.toString();
    }

    public static String loadAllGaragesAsList(String identifier) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Garage garage : garageDao.getAllGarages()) {

            stringBuilder.append(String.format("<%s value = %s >", identifier, garage.getGarageId()));

            stringBuilder.append(garage.getTitle());

            stringBuilder.append(String.format("</%s>", identifier));

        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(String.format("<%s value=-1>", "option"));
            stringBuilder.append("currently there is no Garages");
            stringBuilder.append(String.format("</%s>", "option"));
        }
        return stringBuilder.toString();
    }

    public static Date getMinDate(ArrayList<ReportsInterface> dailyhistoryRecord, ArrayList<ReportsInterface> monthlyHistoryRecord, ArrayList<ReportsInterface> yearlyHistoryRecord) {
        if (yearlyHistoryRecord.size() > 0) {
            return yearlyHistoryRecord.get(0).getRecordDate();
        } else if (monthlyHistoryRecord.size() > 0) {
            return monthlyHistoryRecord.get(0).getRecordDate();
        } else if (dailyhistoryRecord.size() > 0) {
            return dailyhistoryRecord.get(0).getRecordDate();
        }
        return new Date();
    }

    public static ArrayList< ReportsInterface> mergeHistoryReports(ArrayList<ArrayList<ReportsInterface>> merged) {

        ArrayList<ReportsInterface> result = new ArrayList<>();
        for (ArrayList<ReportsInterface> category : merged) {
            for (ReportsInterface record : category) {

                result.add(record);
            }
        }
        return result;
    }

    public String loadAllMapsAsList(String identifier) {
        StringBuilder stringBuilder = new StringBuilder();

//        for (Map map : mapsDao.getAllMaps()) {
//            if (map.getGarages() == null) {
//                stringBuilder.append(String.format("<%s value = %s >%s", identifier, map.getMapId(), map.getMapUrl()));
//                stringBuilder.append(map.getMapUrl());
//                stringBuilder.append(String.format("</%s>", identifier));
//            }
//        }
        return stringBuilder.toString();
    }

    public static String loadAllEmployeesAsList(int myId, String identifier) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Employees employee : empDao.getAllEmployees()) {
            if (employee.getEmployeeId() == myId) {
                continue;
            }
            stringBuilder.append(String.format("<%s value = %s >", identifier, employee.getEmployeeId()));
            stringBuilder.append(employee.getEmail());
            stringBuilder.append(String.format("</%s>", identifier));

        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(String.format("<%s value = -1>", identifier));
            stringBuilder.append("currently there is no employees");
            stringBuilder.append(String.format("</%s>", identifier));
        }
        return stringBuilder.toString();
    }

    public static String loadAllEmployeesAsList(String identifier) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Employees employee : empDao.getAllEmployees()) {

            stringBuilder.append(String.format("<%s value = %s >", identifier, employee.getEmployeeId()));
            stringBuilder.append(employee.getEmail());
            stringBuilder.append(String.format("</%s>", identifier));

        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(String.format("<%s value= -1>", identifier));
            stringBuilder.append("currently there is no garages");
            stringBuilder.append(String.format("</%s>", identifier));
        }
        return stringBuilder.toString();
    }

    public static String loadAllEmployeesAsListByGarageId(int garageId) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Employees employee : empDao.getAllEmployeesByGarageId(garageId)) {
            stringBuilder.append(String.format("<%s value = %s >", "option", employee.getEmployeeId()));
            stringBuilder.append(employee.getEmail());
            stringBuilder.append(String.format("</%s>", "option"));

        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(String.format("<%s >", "option"));
            stringBuilder.append("currently there is no garages");
            stringBuilder.append(String.format("</%s>", "option"));
        }
        return stringBuilder.toString();
    }

    public static Employees getEmployeeById(int id) {

        return empDao.getEmployee(id);
    }

    public static String loadAllEmployeesAsListByGarageId(int garageId, int employeeId) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Employees employee : empDao.getAllEmployeesByGarageId(garageId)) {
            if (employee.getEmployeeId() == employeeId) {
                continue;
            }
            stringBuilder.append(String.format("<%s value = %s >", "option", employee.getEmployeeId()));
            stringBuilder.append(employee.getEmail());
            stringBuilder.append(String.format("</%s>", "option"));

        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(String.format("<%s value=-1>", "option"));
            stringBuilder.append("currently there is no Employee");
            stringBuilder.append(String.format("</%s>", "option"));
        }
        return stringBuilder.toString();
    }

    public GeoLocation[] boundingCoordinates(double latitude, double longitude) {
        GeoLocation location = GeoLocation.fromDegrees(latitude, longitude);
        return location.boundingCoordinates(Constants.distance, 6371.01);

    }

    public void test() {
    }

    public static void main(String[] args) {
        //   int result = GarageSlotDoorsImp.getInstance().addGarageSlot("asmaa slot", 140, 140, 1);
        //GarageSlotDoorsImp.getInstance().handleThisGaragePlease(GObjects.Home.prepareMeGarageDemoPlease(24));
//        prepareMeAPathPlease();

        ArrayList<ReportsInterface> d = DailyHistoryReportImp.getInstance().getDailyHistory(4);
        System.out.println();

    }

    public static JsonObject ConvertStepToJson(Step step) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stepOrder", step.getId());
        jsonObject.addProperty("x", step.getX());
        jsonObject.addProperty("y", step.getY());
        return jsonObject;

    }

    public static JsonObject ConvertStepToJson(int id, int x, int y) {
        Step step = new Step(id, x, y);
        return ConvertStepToJson(step);
    }

    public static String prepareMeAPathPlease(ArrayList<Step> steps) {

        JsonArray theArray = new JsonArray();

        for (GObjects.Step step : steps) {
            theArray.add(ConvertStepToJson(step.getId(), step.getX(), step.getY()));
        }
        System.out.println(theArray);
        return theArray.toString();
    }

    public static ArrayList<Step> prepareMePathArrayListPlease(Step... steps) {
        ArrayList<Step> tempSteps = new ArrayList<Step>();

        for (Step step : steps) {
            tempSteps.add(step);
        }
        return tempSteps;
    }

    public static void checkCurrentUserStatus(HttpServletRequest request) throws exceptions.CurrentClientNotAvailable {

        if (request.getSession().getAttribute("emp") == null) {
            System.out.println("this is no current employee");
            throw new exceptions.CurrentClientNotAvailable();
        } else {
            System.out.println("current employee is already exisits");

        }

    }

    public static void checkSession(HttpServletRequest request) throws exceptions.SessionException {

//        if (request.getSession().getAttribute("session") == null) {
//            throw new exceptions.SessionException();
//        }
    }

    public static String prepareSelectTag(ArrayList<ReportsInterface> list) {
        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        String minDate = Utils.toString(list.get(0).getRecordDate());
        String maxDate = Utils.toString(list.get(list.size() - 1).getRecordDate());

        System.out.println("min date = " + minDate);
        System.out.println("max date = " + maxDate);
        stringBuilder.append(String.format("From :  <input id ='from' type='date' min=%s max = %s value=%s />", minDate, maxDate, minDate, minDate));
        stringBuilder.append("&nbsp&nbsp");
        stringBuilder.append(String.format("To : <input id ='to' type='date' min=%s max = %s/>", minDate, maxDate, maxDate, maxDate));
        return stringBuilder.toString();

    }

    public static ReportHistoryRecord prepareHistoryRecord(ArrayList<ReportsInterface> list, String from, String to, int numberOfSlots) {
        ReportHistoryRecord result = new ReportHistoryRecord();
        double hours = 0;
        double income = 0;

        Date minDate = correctDates(totDate(from), totDate(to), "min");
        Date maxDate = correctDates(totDate(from), totDate(to), "max");

        for (ReportsInterface reportInterface : list) {
            if ((reportInterface.getRecordDate().after(minDate) || reportInterface.getRecordDate().equals(minDate)) && (reportInterface.getRecordDate().before(maxDate) || reportInterface.getRecordDate().equals(maxDate))) {
                hours += reportInterface.getHours();
                income += reportInterface.getIncome();
            }
        }
        result.setIncome(income);
        result.setRecordDate(new Date());
        result.setHours(hours);
        result.setFrom(toString(minDate));
        result.setTo(toString(maxDate));
       

        result.setAvrageOrConsumption((hours / (((daysBetween(minDate, maxDate) + 1) * 24) * numberOfSlots)));

        return result;
    }

    public static int daysBetween(Date d1, Date d2) {
        int result = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        return result;
    }

    public static Date correctDates(Date minDate, Date maxDate, String identifier) {
        Date tempDate = null;

        switch (identifier) {
            case "min":
                if (minDate.after(maxDate)) {
                    tempDate = maxDate;
                } else {
                    tempDate = minDate;
                }
                break;
            case "max":
                if (minDate.after(maxDate)) {
                    tempDate = minDate;
                } else {
                    tempDate = maxDate;
                }
                break;
        }
        return tempDate;
    }

    public static HashMap<Integer, ValueContainer> getTotalConsumedHours(Set list) {
        double result = 0;
        Set<ReportsInterface> tempList = (Set<ReportsInterface>) list;
        HashMap<Integer, ValueContainer> history = new HashMap<>();
        for (ReportsInterface rep : tempList) {
            int temp = extractIdentifierValue(rep);
            if (history.containsKey(temp)) {
                ValueContainer tempValueContainer = history.get(temp);
                history.get(temp).setValue(tempValueContainer.getValue() + rep.getHours());

            } else {
                history.put(temp, new ValueContainer(rep.getHours()));

            }
        }
        return history;
    }

    private static int extractIdentifierValue(ReportsInterface ri) {
        int result = 0;
        Calendar c = Calendar.getInstance();

        c.setTime(ri.getRecordDate());
        switch (ri.getIdentifier()) {
            case "yyyy":
                result = c.get(Calendar.YEAR);
                break;
            case "MM":
                // take care or plus one :)
                result = c.get(Calendar.MONTH) + 1;
                break;
            case "dd":
                result = c.get(Calendar.DAY_OF_MONTH);
                break;

        }
        return result;
    }
}

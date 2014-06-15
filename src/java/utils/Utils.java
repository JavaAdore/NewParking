package utils;

import DAOS.*;
import GObjects.Step;
import GObjects.ValueContainer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import daosint.ReportsInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojo.ContactNumber;
import pojo.DailyHistory;
import pojo.EmailAddress;
import pojo.Employees;
import pojo.Garage;
import pojo.GarageStatus;
import pojo.Map;
import pojo.Users;
import reportsClasses.ReportHistoryRecord;

public class Utils {

    private static EmployeesImp empDao;
    private static GarageImp garageDao;
    private static MapsImp mapsDao = MapsImp.getInstance();

    static {

        empDao = EmployeesImp.getInstance();

        garageDao = GarageImp.getInstance();
    }

    public static Date totDate(String dateAsString, String format) {
        SimpleDateFormat formatedDate = new SimpleDateFormat(format);
        Date date;
        try {
            formatedDate.setLenient(false);

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

        return new EmployeeWrapper(employee.getEmployeeId(), employee.getRoles(), employee.getGarage(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getActive());
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

    public static Date[] extractMinMaxDate(int garageId) {
        {
            //List<Date> dates = new ArrayList<Date>();

            //Garage garage = garageDao.getGarage(garageId);
            return new Date[]{new Date(), new Date()};

        }

    }

    public static Date[] extractMinMaxDate(List<ReportsInterface> reports) {
        Date[] dates = new Date[2];
        if (reports.size() == 1) {
            dates[0] = reports.get(0).getRecordDate();
            dates[1] = dates[0];
            return dates;
        } else if (reports.size() > 1) {
            dates[0] = reports.get(0).getRecordDate();
            dates[1] = reports.get(reports.size() - 1).getRecordDate();
            return dates;
        }

        return new Date[]{new Date(), new Date()};
    }

    public static Object getFirstElelemt(List list) {
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static Object getLastElement(List list) {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
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

    public static int olderThan(String birthdate) {

        Date date = totDate(birthdate, "MM/dd/yyyy");
        if (date == null) {
            return Constants.IS_NOT_A_DATE;
        }
        if (!isDateBefore(date, Constants.MINIMUM_ACCEPTED_AGE, Constants.YEAR)) {
            return Constants.VOLITILE_MINIMUM_ACCEPTED_AGE;
        }
        return Constants.SUCCESS;

    }

    public static void main(String[] args) {

        HashMap<GarageStatus, List<ReportsInterface>> detailed = detailed(164);
        System.out.println();
//       
    }

    public static int getNumberOfInActiveUsers(ArrayList<Garage> allGarages) {

        int counter = 0;
        for (Garage garage : allGarages) {
            if (garage.getEnabled() == 0) {
                counter++;
            }
        }
        return counter;
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

    public static boolean checkCurrentUserStatus(HttpServletRequest request) throws exceptions.CurrentClientNotAvailable {
        boolean result = true;
        EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");

        try {
            if (emp == null) {
                System.out.println("this is no current employee");
                result = false;
              //  throw new exceptions.CurrentClientNotAvailable();
            } else {
                Employees employee = empDao.getEmployee(emp.getEmployeeId());
                if (employee == null) {
                    result = false;
                //    throw new exceptions.CurrentClientNotAvailable();
                } else {
                    if (employee.getActive() == 0) {
                        result = false;
                //        throw new exceptions.CurrentClientNotAvailable();

                    }

                }

            }
        } finally {
            return result;
        }

    }

    public static void checkCurrentUserStatus(int employeeId) throws exceptions.CurrentClientNotAvailable {
        Employees employee = empDao.getEmployee(employeeId);
        if (employee == null) {
            throw new exceptions.CurrentClientNotAvailable();
        } else {
            if (employee.getActive() == 0) {
                throw new exceptions.CurrentClientNotAvailable();

            }

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

//
    public static HashMap<GarageStatus, ReportHistoryRecord> prepareSlotsHistoryRecord(HashMap<GarageStatus, List<ReportsInterface>> detailedReport, String from, String to, int numberOfSlots) {

        HashMap<GarageStatus, ReportHistoryRecord> result = new HashMap();

        Date minDate = correctDates(totDate(from, "MM/dd/yyyy"), totDate(to, "MM/dd/yyyy"), "min");
        Date maxDate = correctDates(totDate(from, "MM/dd/yyyy"), totDate(to, "MM/dd/yyyy"), "max");
        int counter = 0;
        double hours = 0;
        double income = 0;
        for (List<ReportsInterface> reportInterfaces : detailedReport.values()) {

            for (ReportsInterface reportInterface : reportInterfaces) {

                if ((reportInterface.getRecordDate().after(minDate) || reportInterface.getRecordDate().equals(minDate)) && (reportInterface.getRecordDate().before(maxDate) || reportInterface.getRecordDate().equals(maxDate))) {
                    hours += reportInterface.getHours();
                    income += reportInterface.getIncome();
                }

            }
            ReportHistoryRecord tempReportHistoryRecord = new ReportHistoryRecord();
            tempReportHistoryRecord.setIncome(income);
            tempReportHistoryRecord.setRecordDate(new Date());
            tempReportHistoryRecord.setHours(hours);
            tempReportHistoryRecord.setAvrageOrConsumption((hours / (((daysBetween(minDate, maxDate) + 1) * 24))));
            result.put((GarageStatus) detailedReport.keySet().toArray()[counter], tempReportHistoryRecord);
            counter++;
            hours = 0;
            income = 0;
        }

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

    public static HashMap<Date, ValueContainer> getTotalConsumedHours(Set list) {
        double result = 0;
        Set<ReportsInterface> tempList = (Set<ReportsInterface>) list;
        HashMap<Date, ValueContainer> history = new HashMap<>();
        for (ReportsInterface rep : tempList) {

            int temp = extractIdentifierValue(rep);
            if (history.containsKey(temp)) {
                ValueContainer tempValueContainer = history.get(temp);
                history.get(temp).setValue(tempValueContainer.getValue() + rep.getHours());

            } else {
                history.put(rep.getRecordDate(), new ValueContainer(rep.getHours()));

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

    public static JSONObject prepareGarageOverView(int id) {

        JSONArray list = new JSONArray();
        JSONObject obj, obj2;

        String imagePath = GarageImp.getInstance().getImagePath(id);
        obj2 = new JSONObject();
        ArrayList<WrappedGarageSlotsStatus> result = GarageImp.getInstance().getGarageSlotsStatus(id);
        obj2.put("imagePath", imagePath);
        for (WrappedGarageSlotsStatus wrappedGarageSlotsStatus : result) {
            obj = new JSONObject();
            obj.put("slotId", wrappedGarageSlotsStatus.getSlotId());
            obj.put("slotName", wrappedGarageSlotsStatus.getSlotName());
            obj.put("status", wrappedGarageSlotsStatus.getSlotStatus());
            obj.put("x", wrappedGarageSlotsStatus.getX());
            obj.put("y", wrappedGarageSlotsStatus.getY());

            list.add(obj);
        }
        obj2.put("slots", list);
        return obj2;

    }

    public static HashMap<GarageStatus, List<ReportsInterface>> detailed(int garageId) {

        Garage garage = GarageImp.getInstance().getGarage(garageId);
        HashMap slotsHistory = new HashMap<GarageStatus, List<ReportsInterface>>();
        List reports = new ArrayList();

        for (GarageStatus slot : garage.getGarageStatus()) {
            reports.clear();

            reports.add(slot.getDailyHistory());
            reports.add(slot.getMonthlyHistory());
            reports.add(slot.getYearlyHistorys());
            slotsHistory.put(slot, mergeSlotsHistory(reports));
        }
        return slotsHistory;
    }

    public static List<ReportsInterface> mergeSlotsHistory(List reports) {
        List result = new ArrayList();
        for (Object report : reports) {
            Set<DailyHistory> temp = (Set<DailyHistory>) report;
            for (ReportsInterface tempRep : temp) {

                result.add(tempRep);
            }

        }
        return result;
    }

    public static String toTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss a");
        String time = sdf.format(date);

        System.out.println(time);
        return time;
    }

    public static boolean isDateWithin(Date date) {
        return new Date().after(date);

    }

    public static boolean isDateBefore(Date date, int number, String identifier) {

        Calendar c = Calendar.getInstance();
        switch (identifier) {
            case Constants.DAY:
                c.add(Calendar.DAY_OF_MONTH, -1 * number);
                break;
            case Constants.MONTH:
                c.add(Calendar.MONTH, -1 * number);
                break;
            case Constants.YEAR:
                c.add(Calendar.YEAR, -1 * number);
                break;
        }

        return date.before(c.getTime());

    }

    public static CustomDate populateDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return new CustomDate(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));

    }

    public static String populateString(CustomDate customDate) {

        return String.format("%d-%d-%d", customDate.getYear(), customDate.getMonth(), customDate.getDay());
    }

    public static int numberOfRoleHolders(List<Employees> employees) {
        int counter = 0;
        for (Employees emp : employees) {
            if (emp.getRoles() != null) {
                if (emp.getRoles().getRoleName() != null) {
                    if (!emp.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {

                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    public static double hoursBetween(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (1000 * 60 * 60);

    }

    public static String prepareGarageContactsDetails(int garageId) {
        Garage garage = GarageImp.getInstance().getGarage(garageId);
        if (garage != null) {
            Collection<ContactNumber> contactNumbers = garage.getContactNumbers();
            JsonArray contacts = new JsonArray();
            Gson contact = new Gson();
            for (ContactNumber c : contactNumbers) {

                contacts.add(contact.toJsonTree(c));
            }
            return contacts.toString();
        }
        return new JsonArray().toString();
    }

    public static String prepareGarageEmailsList(int garageId) {
        Garage garage = GarageImp.getInstance().getGarage(garageId);
        if (garage != null) {
            Collection<EmailAddress> emails = garage.getEmails();
            JsonArray garageEmails = new JsonArray();
            Gson contact = new Gson();
            for (EmailAddress c : emails) {

                garageEmails.add(contact.toJsonTree(c));
            }
            return garageEmails.toString();
        }
        return new JsonArray().toString();
    }

    public static String loadContacts(int garageId, String deleteButtonFormatingClass, String deleteButtonMethod) {
        Garage garage = GarageImp.getInstance().getGarage(garageId);
        StringBuilder stringBuilder = new StringBuilder();
        if (garage != null) {

            for (ContactNumber contactNumber : garage.getContactNumbers()) {
                stringBuilder.append(String.format("<tr id=%s >", contactNumber.getId()));
                stringBuilder.append(String.format("<td>%s</td>", contactNumber.getPhoneNumber()));
                stringBuilder.append(String.format("<td><button class=%s onclick=%s(%s,d)> Delete </button> </td>", deleteButtonFormatingClass, deleteButtonMethod, contactNumber.getId()));
                stringBuilder.append(String.format("</tr>"));
            }
        }
        return stringBuilder.toString();

    }

    public static int numberOfInActiveEmployees(Collection<Employees> employees) {
        int counter = 0;
        for (Employees emp : employees) {
            if (emp.getActive() == 0) {
                counter++;
            }
        }
        System.out.println("number Of Inactive employees = " + counter);
        return counter;
    }

    public static String isDateBetween(Date date, Date date1, Date date2) {
        if (((date1.compareTo(date) <= 0) && (date2.compareTo(date) >= 0))) {
            return "";
        }

        CustomDate minDate = CustomDate.getCustomDate(date1);
        CustomDate maxDate = CustomDate.getCustomDate(date2);
        return String.format("Please enter date between %s/%s/%s and %s/%s/%s", minDate.getMonth(), minDate.getDay(), minDate.getYear(), maxDate.getMonth(), maxDate.getDay(), maxDate.getYear());
    }

    public static boolean compareDates(Date date1, Date date2, String identifier) {
        boolean result = false;
        switch (identifier) {
            case "b":
                if (date1.compareTo(date2) > 0) {
                    result = true;

                }
                break;

            case "a":
                if (date1.compareTo(date2) < 0) {
                    result = true;

                }
                break;

        }
        return result;
    }

}

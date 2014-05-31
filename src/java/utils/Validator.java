/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.*;
import errors.ErrorMessage;
import java.util.List;
import pojo.Employees;

/**
 *
 * @author orcl
 */
public class Validator {

    private static EmployeesImp employeeImp = EmployeesImp.getInstance();
    private static GarageImp garageImp = GarageImp.getInstance();

    public static final String EMAIL_REGEX
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN
            = "((?=.*\\d)(?=.*[A-Z]).{8,25})";

    private static final String NAME = "^[A-z]+$";

    private static final String IMAGE_PATTERN
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public static boolean isInteger(String number) {

        try {
            int d = Integer.parseInt(number);
        } catch (Exception ex) {

            return false;   
        }
        return true;

    }

    public static boolean isEmail(String email) {

        return email.matches(EMAIL_REGEX);

    }

    public static boolean isPassword(String password) {

        return password.matches(PASSWORD_PATTERN);
    }

    public static void main(String[] args) {

        boolean image = isName("555");
        System.out.println();
    }

    public static boolean validateAdmin(String firstName, String lastName, String email, String password, String confirmPassword, String gender, String birthdate, String role) {

        if (isName(firstName) && isName(lastName) && isEmailWithValidation(email) && isPassword(password) && areTheSame(password, confirmPassword) && areTheSame(password, confirmPassword) && isGender(gender) && isBirthDate(birthdate) && isEmployee(role)) {
            return true;
        }
        return false;
    }

    public static boolean isGender(String gender) {
        return gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("g");
    }

    public static boolean areTheSame(String string1, String string2) {
        return string1.equals(string2);
    }

    private static boolean isEmailWithValidation(String email) {
        if (isEmail(email)) {
            return employeeImp.getEmployee(email) == null;
        }
        return false;

    }

    private static boolean isBirthDate(String birthdate) {

        if (utils.Utils.olderThan(birthdate) == Constants.SUCCESS) {
            return true;
        }
        return false;
    }

    public static boolean validateUpdateProfile(String email, String password, String confirmPassword, String garage, String active) {

        return (isEmail(email) && isPassword(password) && areTheSame(password, confirmPassword) && isPosNumber(garage) && isPosNumber(active));

    }

    public static boolean validateUpdateProfile(String garage, String active) {

        return (isPosNumber(garage) && isPosNumber(active));

    }

    public static boolean validateUpdateProfile(String email, String password, String confirmPassword) {

        return (isEmail(email) && isPassword(password) && areTheSame(password, confirmPassword));

    }

    public boolean isDate() {

        //return totDate(birthdate, "MM/dd/yyyy")!=null;
        return false;
    }

    public static boolean isEmployee(String str) {
        int role;
        if (str == null) {
            return false;
        }
        try {
            role = Integer.parseInt(str);
            if (role == 2 || role == 3) {
                return true;
            }
        } catch (Exception ex) {

            return false;
        }
        return false;
    }

    public static boolean isName(String name) {
        if ((!(name.length() > 0 && name.length() <= 25))) {
            return false;
        }
        if (!name.matches(NAME)) {
            return false;
        }
        return true;
    }

    public static boolean isAnImage(String fileName) {
        return fileName.matches(IMAGE_PATTERN);
    }

    public static boolean isPosNumber(String str) {

        try {
            int temp = Integer.parseInt(str);

            if (temp > 0) {

                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static boolean isGarageNameAvailable(String garageName) {
        
        if (!isName(garageName)) {
            return false;
        }
        return garageImp.getGarage(garageName) == null;
    }

    public static boolean isDouble(String number, double min, double max) {
        
        try {
            double temp = Double.parseDouble(number);
            return (temp > min && temp < max);
        } catch (Exception ex) {

            return false;
        }
    }

    public static boolean isRatio(String ratio) {

        return isDouble(ratio, 0, 1);
    }
    
    
    

//    public static List<ErrorMessage> validateAddAdmin() {
//
//    }
}

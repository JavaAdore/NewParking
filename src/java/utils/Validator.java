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




public  class Validator {

    private static EmployeesImp employeeImp = EmployeesImp.getInstance();
    private static GarageImp garageImp = GarageImp.getInstance();

    public static final String EMAIL_REGEX
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN
            = "((?=.*\\d)(?=.*[A-Z]).{8,25})";

    private static final String NAME = "^[A-z]+$";
    private static final String NUMBER = "^\\d+$";
    

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

        boolean validateAddGarage = validateAddGarage("ddd", "12", "a.png", ".5", "15", "150", "m", "89", "89");
        System.out.println();
    }

    public static boolean validateAddGarage(String garageName, String hourRateInRushhours, String isImage, String ratio, String width, String height, String unit, String lat, String lon) {

        return (isGarageNameAvailable(garageName) && isDouble(hourRateInRushhours, 0, utils.Constants.MAX_ACCEPTED_HOUR_RATE) && isAnImage(isImage) && isRatio(ratio) && isDouble(width, 0, 99999) && isDouble(height, 0, 99999) && isUnit(unit) && isDouble(lon, -90, 90) && isDouble(lat, -90, 90));
    }

    public static boolean validateAdmin(String firstName, String lastName, String email, String password, String confirmPassword, String gender, String birthdate, String role) {

        if (isName(firstName)&&isName(lastName)&&isEmailWithValidation(email)&&isPassword(password)&&areTheSame(password, confirmPassword)&&isGender(gender)&&isBirthDate(birthdate)&&isEmployee(role)) {
            return true;
        }
        return false;
    }

    public static boolean isGender(String gender) {
        return gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f");
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

    public static boolean validateAddGarage(List<String> parameterValues) {
        return validateAddGarage(parameterValues.get(0), parameterValues.get(1), parameterValues.get(2), parameterValues.get(3), parameterValues.get(4), parameterValues.get(5), parameterValues.get(6), parameterValues.get(7), parameterValues.get(8));
    }

    public static boolean isANumber(String feedbackId) {

        try {
            int temp = Integer.parseInt(feedbackId);
        } catch (Exception ex) {

            return false;
        }
        return true;

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

    public static boolean isUnit(String unit) {
        return (unit.equalsIgnoreCase("m") || unit.equalsIgnoreCase("f") || unit.equalsIgnoreCase("i"));
    }

    public static boolean isActive(String str) {
        if (str != null) {
            try {
                double number = Double.parseDouble(str);
                return (number >= 0 || number <= 1);
            } catch (Exception ex) {
                return false;
            }
        }
        return false;

    }
}

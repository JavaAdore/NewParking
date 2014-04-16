/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.FetchType;

/**
 *
 * @author orcl
 */
public class Constants {

    public static final double distance = .5; // referes to 100 meter

    public static final String FAX = "FAX";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final int SUCCESS = 0;
    public static final int FAILED = -1;
    public static final int NOT_PHONE_NUMBER = -3;
    public static final int NOT_AN__EMAIL = -4;
    public static final int DELETE_AFTER = 10;
    public static final int INVALID_INPUTS = -22;

    public static final int IS_NOT_A_DATE = -99;
    public static final int MINIMUM_ACCEPTED_AGE = 16; // year
    public static final int VOLITILE_MINIMUM_ACCEPTED_AGE = -17;

    public static final String DAY = "DAY";
    public static final String MONTH = "MONTH";
    public static final String YEAR = "YEAR";

}

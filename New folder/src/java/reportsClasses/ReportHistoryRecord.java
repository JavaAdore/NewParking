package reportsClasses;

import java.util.Date;
import pojo.DailyHistory;

/**
 *
 * @author orcl
 */
public class ReportHistoryRecord extends DailyHistory {

    String from;
    String to;
    double avrageOrConsumption = 0;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    

    public ReportHistoryRecord() {
    }

    public ReportHistoryRecord(Date date, double hours, double avrageOrConsumption, String from, String to , double income) {
        super(date, hours, income);
        this.avrageOrConsumption = avrageOrConsumption;
        this.from = from;
        this.to = to;
        
    }

    public double getAvrageOrConsumption() {
        return avrageOrConsumption;
    }

    public void setAvrageOrConsumption(double avrageOrConsumption) {
        this.avrageOrConsumption = avrageOrConsumption;
    }
}

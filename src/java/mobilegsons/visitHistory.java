/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilegsons;

/**
 *
 * @author orcl
 */
public class visitHistory {

    String garageName;
    String numberOfVisits;

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(String numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public String getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(String lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }
    String lastVisitTime;

    public visitHistory() {
    }

    public visitHistory(String garageName, String numberOfVisits, String lastVisitTime) {
        this.garageName = garageName;
        this.numberOfVisits = numberOfVisits;
        this.lastVisitTime = lastVisitTime;
    }

}

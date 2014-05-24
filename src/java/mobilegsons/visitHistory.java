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
    int numberOfVisits;
    String lastVisitTime;

    public visitHistory() {
    }

    public visitHistory(String garageName, int numberOfVisits, String lastVisitTime) {
        this.garageName = garageName;
        this.numberOfVisits = numberOfVisits;
        this.lastVisitTime = lastVisitTime;
    }

}

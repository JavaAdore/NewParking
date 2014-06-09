/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author orcl
 */
public class MySessionListener implements HttpSessionListener {

    private static int counter = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        counter++;
        System.out.println("number of sessions = " + counter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("number of sessions = " + counter);
        counter--;
    }

    public static int getSessionCount() {
        return counter;
    }

}

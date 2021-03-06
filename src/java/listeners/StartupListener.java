/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import Sessions.ConnectionHandler;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utils.DeleteThread;

/**
 *
 * @author orcl
 */
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().setAttribute("serviceProviderHeader", httputils.HeaderLoader.getServiceProviderHeader());
        sce.getServletContext().setAttribute("adminHeader", httputils.HeaderLoader.getAdminHeader());
        sce.getServletContext().setAttribute("accountantHeader", httputils.HeaderLoader.getAccountatnHeader());
        ConnectionHandler.initiateSessions();
        new DeleteThread();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionHandler.destroySessions();

    }

}

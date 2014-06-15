/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import errors.ErrorMessage;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.*;

/**
 *
 * @author orcl
 */
public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String distination = ((HttpServletRequest) request).getServletPath();
        if (distination.endsWith("jsp") && !(distination.contains("login"))) {
            boolean checkCurrentUserStatus = Utils.checkCurrentUserStatus((HttpServletRequest) request);
            if (checkCurrentUserStatus == false) {
                Cookie[] cookies = ((HttpServletRequest) request).getCookies();
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        cookies[i].setMaxAge(0);
                        ((HttpServletResponse) response).addCookie(cookies[i]);
                    }
                }
                ((HttpServletRequest) request).getSession().invalidate();
                ((HttpServletRequest) request).setAttribute("error", new ErrorMessage("Your account not activated any more"));

                ((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}

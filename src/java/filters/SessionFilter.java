/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author orcl
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (!((HttpServletRequest) request).getHeader(HttpHeaders.REFERER).contains("Home")) {
//            {
//                if (((HttpServletRequest) request).getSession().getAttribute("session") == null) {
//                    ((HttpServletResponse) response).sendRedirect("Home");
//                } else {
//                }
//            }
//        }
        
                            chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}

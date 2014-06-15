/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httputils;

import javax.servlet.http.Cookie;

/**
 *
 * @author orcl
 */
public class HTTPUtils {

    public static Cookie eraseCookie(String strCookieName, String strPath) {
        Cookie cookie = new Cookie(strCookieName, "");
        cookie.setMaxAge(0);
        cookie.setPath(strPath);

        return cookie;
    }

}

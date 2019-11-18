package cn.luckyray.evaluation.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author young1lin
 * @version 1.0
 * @date 2019/11/12 11:15 下午
 */
public class CookieUtil {


    public static Cookie[] getCookies(HttpServletRequest request){
        return request.getCookies();
    }

    public static String getCookie(HttpServletRequest request,String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        Cookie[] cookies = getCookies(request);
        for (Cookie cookie : cookies){
            if (key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response,Cookie cookie){
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response,String key,String value,int maxAge,int expiry,
                                 String path,String domain){
        Cookie cookie = new Cookie(key,value);
        if(path != null){
            cookie.setPath(path);
        }
        if(domain != null){
            cookie.setDomain(domain);
        }
        setCookie(response,cookie);
    }
}

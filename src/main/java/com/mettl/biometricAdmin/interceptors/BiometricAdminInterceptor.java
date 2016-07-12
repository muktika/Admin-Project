package com.companyname.biometricAdmin.interceptors;

import com.companyname.biometricAdmin.Constants.AdminLoginConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dell on 22/12/15.
 */
@Component
public class BiometricAdminInterceptor extends HandlerInterceptorAdapter{
    private Logger logger = LoggerFactory.getLogger(BiometricAdminInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(AdminLoginConstants.LOGGED_IN_USER_EMAIL) == null) {
            String controllerName = _getNormalizedControllerName(request);
            logger.debug("in admin interceptor with controller name as {}", controllerName);

            // check if the correct session zone is accessed

            String returnUrl = _getReturnUrl(request, controllerName);
            response.sendRedirect(returnUrl);
            return false;
        }
        return true;
    }

    private String _getReturnUrl(HttpServletRequest request, String controllerName) {
        if (request.getMethod().equals("GET")) {
            return _getReturnUrlForGETMethod(request, controllerName);
        } else
            return _getReturnUrlForPOSTMethod(request);
    }

    private String _getReturnUrlForPOSTMethod(HttpServletRequest request) {
        return request.getContextPath() + "/admin/login";
    }

    private String _getReturnUrlForGETMethod(HttpServletRequest request, String controllerName) {
        String queryString = request.getQueryString();
        String pathWithQueryString = controllerName + (queryString != null ? ("?" + queryString) : "");
        String encodedPath;
        try {
            encodedPath = URLEncoder.encode(pathWithQueryString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.debug("Exception occured while encoding path", e);
            encodedPath = controllerName;
        }

        return request.getContextPath() + "/admin/login?returnUrl=" + encodedPath;
    }
    private String _getNormalizedControllerName(HttpServletRequest request) {
        String controllerName = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        controllerName = normalizeControllerName(controllerName);
        return controllerName;
    }

    private String normalizeControllerName(String controllerName) {
        return controllerName.startsWith("/") ? controllerName : "/" + controllerName;
    }
}

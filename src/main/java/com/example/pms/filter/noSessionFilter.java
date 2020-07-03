package com.example.pms.filter;

import com.example.pms.util.ExceptionUtil;
import com.example.pms.util.TokenSubjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class noSessionFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("isAccessAllowed");
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        System.out.println(httpServletRequest.getServletPath());
        String token=httpServletRequest.getParameter("token");
        System.out.println(token);
        Subject subject= TokenSubjectUtil.getSubject(token);
        if(subject==null){
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("{\"code\":50004,\"message\":\"未登录\"}");
        return false;
    }
}

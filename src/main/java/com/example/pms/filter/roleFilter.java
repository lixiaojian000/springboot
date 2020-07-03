package com.example.pms.filter;

import com.example.pms.service.redisService;
import com.example.pms.util.TokenSubjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class roleFilter extends AuthorizationFilter {
    @Autowired
    public redisService redisservice;
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        System.out.println(httpServletRequest.getServletPath());
        String token=httpServletRequest.getParameter("token");
        Subject subject= TokenSubjectUtil.getSubject(token);
        String user=redisservice.get(token);
        System.out.print(user);
        if(subject==null) {
            //subject=this.getSubject(servletRequest,servletResponse);
            return false;
        }
       //String[] rolesArray=(String[])mappedVlue;
        return false;
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return super.onAccessDenied(request, response, mappedValue);
    }
}

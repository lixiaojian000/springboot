package com.example.pms.config;

import com.example.pms.filter.noSessionFilter;
import com.example.pms.pojo.User;
import com.example.pms.repository.UserRepository;
import com.example.pms.util.ExceptionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShrioConfig {
    @Autowired
    public UserRepository userRepository;
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置登录路径
        shiroFilterFactoryBean.setLoginUrl("/web/login");
        //无权限时访问的路径
        //shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
        //自定义拦截器
        Map<String, Filter> filterMap=new HashMap<String,Filter>(1);
        filterMap.put("jwt",new noSessionFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //过滤规则
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        //游客访问时的路径
        //filterChainDefinitionMap.put("/web/getAll","anon");
        //用户，需要角色权限”user“
        //filterChainDefinitionMap.put("/web/getAlQ","roles[管理员]");
        //开放登录的接口
        filterChainDefinitionMap.put("/web/login","anon");
        //其余接口一律拦截
        filterChainDefinitionMap.put("/**","jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        //关闭session
        DefaultSubjectDAO subjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setSubjectFactory(ds());
        return securityManager;
    }

    @Bean
    public AuthorizingRealm customRealm(){
        return new AuthorizingRealm(){
            //做权限管理
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                String username=(String) SecurityUtils.getSubject().getPrincipal();
                SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
                Set<String> roles=userRepository.findRoleByUsername(username);
                info.setRoles(roles);
                return info;
            }
            //做用户名密码认证
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
                User user=userRepository.findUserByUsername(token.getUsername());
                if(user==null){
                    throw ExceptionUtil.NOTUSEREXCEPTION;
                }
                String password=user.getPassword();
                if(null==password){
                    throw ExceptionUtil.LOGINERROREXCEPTION;
                }else if(!password.equals(new String((char []) token.getCredentials()))){
                    throw ExceptionUtil.LOGINERROREXCEPTION;
                }
                return new SimpleAuthenticationInfo(token.getPrincipal(),password,getName());
            }
        };
    }
    @Bean
    public disableSession ds(){
        disableSession ds=new disableSession();
        return ds;
    }
}

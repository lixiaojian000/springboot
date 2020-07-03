package com.example.pms.util;

import com.example.pms.error.LoginErrorException;
import com.example.pms.error.NotPremissionException;
import com.example.pms.error.NotUserException;
import org.apache.shiro.authc.AuthenticationException;

public class ExceptionUtil {
    public static final AuthenticationException NOTUSEREXCEPTION=new NotUserException("用户名未注册","50001");
    public static final AuthenticationException LOGINERROREXCEPTION=new LoginErrorException("用户名密码错误","50002");
    public static final AuthenticationException NOTPRMISSIONEXCEPTION=new NotPremissionException("无权限","50003");

}

package com.example.pms.controller;


import com.example.pms.pojo.User;
import com.example.pms.service.UserService;
import com.example.pms.service.redisService;
import com.example.pms.util.TokenSubjectUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

@RestController
public class helloController {
    @Autowired
    public UserService userService;
    public TokenSubjectUtil tokenSubjectUtil;
    @Autowired
    public redisService redisservice;
    @RequestMapping("/web/hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation("用户测试类")
    @PostMapping("/hello1")
    public User hello1(@ApiParam("用户名") @RequestParam String username){
        User user= new User();
         user.setUsername(username);
        return user;
    }
    @ApiOperation("所有用户类")
    @GetMapping("/web/getAll")
    public List<User> getAl(){
        List<User> users= userService.findAll();
        return users;
    }
    @ApiOperation("高级所有用户类")
    @GetMapping("/web/getAlQ")
    public List<User> QusergetAll(){
        List<User> users= userService.findAll();
        return users;
    }
    @ApiOperation("登录测试类")
    @PostMapping("/web/login")
    public Map login(@RequestBody User user) {
        Map resultMap=new HashMap();
        System.out.print("成功");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken((String) user.getUsername(), (String) user.getPassword());
        //执行认证
        subject.login(token);
        //根据权限，指定返回数据
        String randomKey= UUID.randomUUID().toString();
        User us=userService.findUserByUsername(user.getUsername());
        Set<String> role=userService.findRoleByUsername(user.getUsername());
        Iterator<String> it = role.iterator();
        //判断是bai否有下一个du
        while(it.hasNext()){
            resultMap.put("roles",it.next());
        }
        resultMap.put("code",200);
        resultMap.put("msg","登录成功");
        resultMap.put("token",randomKey);
        JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(resultMap));
        redisservice.set(randomKey,jsonObject.toString(),3600L);
        tokenSubjectUtil.saveSubject(randomKey, subject);
        return resultMap;
    }
}

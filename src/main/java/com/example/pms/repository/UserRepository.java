package com.example.pms.repository;

import com.example.pms.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

//继承JpaRepository<实体类，主键类型>
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true,value = "select * from User  ")
    List<User> findAl();

    @Query(nativeQuery = true,value = "select * from User  where username=? and password=?")
    User login(String username,String password);

    @Query(nativeQuery = true,value = "select * from User  where username=? ")
    User findUserByUsername(String username);

    @Query(nativeQuery = true,value = "SELECT role.rolename FROM user,role WHERE user.roleid=role.roleid AND username=?")
    Set<String> findRoleByUsername(String username);
}

package com.example.pms.util;

import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
public class TokenSubjectUtil {
    private static Map<String, Subject> subjectMap=new HashMap<>();
    public static void saveSubject(String randomKey, Subject sub){

        subjectMap.put(randomKey,sub);
    }

    public static Subject getSubject(String randomKey){

         return subjectMap.get(randomKey);
    }
}

package com.example.pms.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

public class disableSession extends DefaultWebSubjectFactory {
    public Subject creatSubject(SubjectContext context){
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}

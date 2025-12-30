package com.vilelo.sdjpacreditcard.config;

import com.vilelo.sdjpacreditcard.interceptors.EncryptionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

    //@Autowired
    EncryptionInterceptor encryptionInterceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        //hibernateProperties.put("hibernate.session_factory.interceptor", encryptionInterceptor);
    }
}

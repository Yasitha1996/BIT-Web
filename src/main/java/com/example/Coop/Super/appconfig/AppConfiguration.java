package com.example.Coop.Super.appconfig;

import com.example.Coop.Super.bean.session.SessionBean;
import com.example.Coop.Super.common.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    @Bean
    Interceptor interceptor(SessionBean sessionBean){
        Interceptor interceptor = new Interceptor();
        interceptor.setSessionBean(sessionBean);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor(getSessionBean())).addPathPatterns("/**")
                .excludePathPatterns("/login*")
                .excludePathPatterns("/logout*")
                .excludePathPatterns("/**/logout*");
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionBean getSessionBean(){
        return new SessionBean();
    }
}

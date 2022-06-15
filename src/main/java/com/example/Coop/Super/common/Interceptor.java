package com.example.Coop.Super.common;

import com.example.Coop.Super.bean.session.SessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class Interceptor implements HandlerInterceptor {

    @Resource
    private SessionBean sessionBean;

    @Autowired
    ServletContext servletContext;

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("sssssssssssssssssssssssssssssssss");
        boolean status = false;
        RequestDispatcher requestDispatcher;
        HttpSession httpSession = request.getSession(false);
        System.out.println("test");
        System.out.println(request.getRequestURI().substring(request.getContextPath().length()));
        try {
            if (!request.getRequestURI().substring(request.getContextPath().length()).equals("/checkuser") &&
                    !request.getRequestURI().substring(request.getContextPath().length()).equals("/logout")) {
                System.out.println("in");
                if (sessionBean != null) {
                    System.out.println("test1");
                    Map<?, ?> sessionMap = (Map<?, ?>) servletContext.getAttribute("sessionMap");
                    if (sessionMap != null) {
                        System.out.println("test2");
                        String userName = sessionBean.getUsername();
                        if (sessionMap.get(userName).equals(httpSession.getId())) {
                            status = true;
                        }
                    } else {
                        requestDispatcher = request.getRequestDispatcher("logout");
                        requestDispatcher.forward(request, response);
                        status = false;
                    }
                } else {
                    status = false;
                }
            } else {
                status = true;
            }
        }catch (Exception e){
            System.out.println(e);
            response.sendRedirect("logout");
        }
            return status;
    }

        @Override
        public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
        modelAndView) throws Exception {
            System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqq");
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

}

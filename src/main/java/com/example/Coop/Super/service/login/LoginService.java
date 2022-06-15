package com.example.Coop.Super.service.login;

import com.example.Coop.Super.bean.login.LoginBean;
import com.example.Coop.Super.bean.session.SessionBean;
import com.example.Coop.Super.repository.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Service
@Scope("prototype")
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

   @Autowired
   SessionBean sessionBean;

   @Autowired
   ServletContext servletContext;

    public String getUser(LoginBean loginBean, HttpServletRequest httpServletRequest){
        String msg = "";
        try {
            msg = loginRepository.getUser(loginBean);
            if(msg == "Login success"){
                HttpSession httpSession = httpServletRequest.getSession(true);
                sessionBean.setSessionId(httpSession.getId());
                sessionBean.setUsername(loginBean.getUsername());
                Map<String, String> sessionMap = (Map<String, String>) servletContext.getAttribute("sessionMap");

                if (sessionMap == null) {
                    // Create a sessionMap instance.
                    sessionMap = new HashMap<>();
                }
                sessionMap.put(loginBean.getUsername(), httpSession.getId());
                servletContext.setAttribute("sessionMap", sessionMap);
                System.out.println("Session ID: " + sessionBean.getSessionId());

            }else if(msg == "") {
                msg = "Connection Error";
            }
        }catch (Exception e){
            System.out.println("Service: " + e);
        }
        return msg;
    }
}

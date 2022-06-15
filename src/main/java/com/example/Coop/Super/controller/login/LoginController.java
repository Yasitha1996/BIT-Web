package com.example.Coop.Super.controller.login;

import com.example.Coop.Super.bean.ResponseBean;
import com.example.Coop.Super.bean.login.LoginBean;
import com.example.Coop.Super.bean.session.SessionBean;
import com.example.Coop.Super.service.login.LoginService;
import com.example.Coop.Super.validator.login.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Scope("request")
public class LoginController {

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    LoginService loginService;

    @Autowired
    SessionBean sessionBean;

    @Autowired
    ServletContext servletContext;

    @GetMapping(value = "/login")
    public ModelAndView viewHomePage(@RequestParam(value = "error", required = false) Integer error, ModelMap modelMap) {
        System.out.println("VIEW LOGIN PAGE");
        return new ModelAndView("login", new ModelMap());
    }

    @GetMapping(value = "/checkuser")
    public ModelAndView getUserLogin(@ModelAttribute("login") LoginBean loginBean, ModelMap modelMap) {
        System.out.println("VIEW USER LOGIN PAGE");
        return new ModelAndView("login", new ModelMap());
    }



    @PostMapping(value = "/checkuser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseBean getCheckUser(@ModelAttribute("login") LoginBean loginBean, HttpServletRequest httpServletRequest) {
        System.out.println("GET USER VALIDATION");
        System.out.println("Username is: " + loginBean.getUsername());
        ResponseBean responseBean = null;
//        ModelAndView modelAndView = null;

        try {
            String msg = loginValidator.getValidateResult(loginBean);
            if (msg!= null && !msg.isEmpty()){
                System.out.println("BLANK FIELD");
                responseBean = new ResponseBean(false, null, msg);
//                modelMap.put("msg", msg);
//                modelAndView = new ModelAndView("login", modelMap);
            } else {
                msg = loginService.getUser(loginBean, httpServletRequest);
                if (msg.equals("Login success")){
                    responseBean = new ResponseBean(true, "Login success!", null);
                    System.out.println("LOGIN SUCCESS");
//                    modelMap.put("msg", msg);
//                    modelAndView = new ModelAndView("login", modelMap);
                }else {

                    System.out.println("LOGIN FAIL");
                    responseBean = new ResponseBean(false, null, msg);
//                    modelAndView = new ModelAndView("login", new ModelMap());
                }
            }

        } catch (Exception e) {
            responseBean = new ResponseBean(false, null, "Error!");
            System.out.println("Controller: " +e);
//            modelAndView = new ModelAndView("login", modelMap);
        }
        return responseBean;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(@RequestParam(value = "error", required = false) Integer error, ModelMap modelMap, HttpSession httpSession) {
        System.out.println("VIEW LOGOUT PAGE");
        try {
            String userName = sessionBean.getUsername();
            Map<String, String> sessionMap = (Map<String, String>) servletContext.getAttribute("sessionMap");
            sessionMap.remove(userName);
            System.out.println("logout");
        }catch (Exception e){
            System.out.println();
            sessionBean.setSessionId(null);
            sessionBean.setUsername(null);
        }
        httpSession.invalidate();
        return new ModelAndView("login", new ModelMap());
    }

    @ModelAttribute
    public void getLoginbean(Model map) throws Exception {
        map.addAttribute("login", new LoginBean());
    }
}

package com.example.Coop.Super.repository.login;

import com.example.Coop.Super.bean.login.LoginBean;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Scope("prototype")
public class LoginRepository {

    public String getUser(LoginBean loginBean){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String msg = "";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM admin WHERE admin_username=? AND admin_password=?");

            ps.setString(1, loginBean.getUsername());
            ps.setString(2, loginBean.getPassword());

            rs = ps.executeQuery();

            if(rs.next()){
                msg = "Login success";

            } else {
                msg = "Invalid user";
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return msg;
    }
}

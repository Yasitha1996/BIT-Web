package com.example.Coop.Super.repository.common;

import com.example.Coop.Super.bean.customer.CustomerDataBean;
import com.example.Coop.Super.bean.status.StatusBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Scope("prototype")
public class CommonRepository {

    public String getPackingCount(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String response = "";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS 'packingCount' FROM test_db.order WHERE status = '0'");
            rs = ps.executeQuery();
            while (rs.next()) {
               response = String.valueOf(rs.getInt("packingCount"));
            }
        }catch (Exception e){
            System.out.println("Repo Exception: " + e);
        }
        return response;
    }


    public String getDeliveringCount(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String response = "";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS 'deliveringCount' FROM test_db.order WHERE status ='2'");
            rs = ps.executeQuery();
            while (rs.next()) {
              response = String.valueOf(rs.getInt("deliveringCount"));

            }
        }catch (Exception e){
            System.out.println("Repo Exception: " + e);
        }
        return response;
    }

    public String getCompletedCount(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String response = "";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT COUNT(*) AS 'completedCount' FROM test_db.order WHERE status ='3'");
            rs = ps.executeQuery();
            while (rs.next()) {
                response = String.valueOf(rs.getInt("completedCount"));

            }
        }catch (Exception e){
            System.out.println("Repo Exception: " + e);
        }
        return response;
    }

    public String getTotalRev(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String response = "";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT SUM(total) AS 'totalRevenue' FROM test_db.order WHERE status ='3'");
            rs = ps.executeQuery();
            while (rs.next()) {
                response = String.valueOf(rs.getInt("totalRevenue"));

            }
        }catch (Exception e){
            System.out.println("Repo Exception: " + e);
        }
        return response;
    }

}

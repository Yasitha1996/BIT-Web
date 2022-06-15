package com.example.Coop.Super.repository.deliverer;

import com.example.Coop.Super.bean.customer.CustomerDataBean;
import com.example.Coop.Super.bean.deliverer.DelivererDataBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Scope("prototype")
public class DelivererRepository {

    public DataTableResponse<DelivererDataBean> getDelivererList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DataTableResponse<DelivererDataBean> response = new DataTableResponse<>();
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM deliverer");
            rs = ps.executeQuery();
            while (rs.next()) {
                DelivererDataBean dataBean = new DelivererDataBean();
                dataBean.setDel_id(rs.getString("del_id"));
                dataBean.setFirstname(rs.getString("firstname"));
                dataBean.setLastname(rs.getString("lastname"));
                dataBean.setLicenseNo(rs.getString("license_no"));
                dataBean.setContactNo(rs.getString("contact"));
                response.data.add(dataBean);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return response;
    }

    public String deleteDeliverer(String del_id) {
        String msg = "Error";
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("DELETE FROM deliverer WHERE del_id =?");

            ps.setString(1, del_id);

            rs = ps.executeUpdate();

            if (rs > 0) {
                msg = "";
            }

        } catch (Exception e) {
            System.out.println("Deliverer Exception:" + e);
            msg = "Failed to delete deliverer";
        }
        return msg;
    }
}

package com.example.Coop.Super.repository.customer;

import com.example.Coop.Super.bean.customer.CustomerDataBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Scope("prototype")
public class CustomerRepository {

 public DataTableResponse<CustomerDataBean> getCustomerList(){
     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     DataTableResponse<CustomerDataBean> response = new DataTableResponse<>();
     try {
         con = DBConnection.getConnection();
         ps = con.prepareStatement("SELECT * FROM customer");
         rs = ps.executeQuery();
         while (rs.next()) {
             CustomerDataBean dataBean = new CustomerDataBean();
             dataBean.setId(rs.getString("id"));
             dataBean.setFname(rs.getString("firstname"));
             dataBean.setLname(rs.getString("lastname"));
             dataBean.setAddress(rs.getString("address"));
             dataBean.setContactNo(rs.getString("phone"));
             response.data.add(dataBean);

         }
     }catch (Exception e){
         System.out.println("Repo Exception: " + e);
     }

     return response;
 }

    public String deleteCustomer(String id) {
        String msg = "Error";
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("DELETE FROM customer WHERE id =?");

            ps.setString(1, id);

            rs = ps.executeUpdate();

            if (rs > 0) {
                msg = "";
            }

        } catch (Exception e) {
            System.out.println("Product Exception:" + e);
            msg = "Failed to delete product";
        }
        return msg;
    }

}

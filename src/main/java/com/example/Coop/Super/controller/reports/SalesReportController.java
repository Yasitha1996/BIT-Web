package com.example.Coop.Super.controller.reports;

import com.example.Coop.Super.bean.reports.SalesReportBean;
import com.example.Coop.Super.service.reports.SalesReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SalesReportController {

    @Autowired
    SalesReportService salesReportService;

    @RequestMapping(value = "/sales", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> sales(String category){

        System.out.println("Test Report");
        ResponseEntity<byte[]> salesData = null;
        try {
           salesData  = salesReportService.getSalesData(category);

        } catch (Exception e){
            System.out.println("Exception  :  " + e);
        }
        return salesData;

        /*try{
            System.out.println("st1");

            List<SalesReportBean> salesList = null;

            Map<String, Object> param =  new HashMap<>();
            param.put("date", "40/343/4");
            param.put("category", "meat");

        JasperPrint empReport =
                JasperFillManager.fillReport
                        (
                                JasperCompileManager.compileReport(
                                        ResourceUtils.getFile("classpath:static/reports/BillReceipt1.jrxml")
                                                .getAbsolutePath()), param, new JRBeanCollectionDataSource(salesList) );

        HttpHeaders headers = new HttpHeaders();
            System.out.println("st2");
        //set the PDF format
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "BillReceipt1.pdf");
        //create the report in PDF format
        return new ResponseEntity<byte[]>
                (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

    } catch(Exception e) {
            System.out.println(e);
        return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
    }
}

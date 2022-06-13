package com.example.Coop.Super.service.reports;

import com.example.Coop.Super.bean.reports.SalesReportBean;
import com.example.Coop.Super.repository.reports.SalesReportRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("prototype")
public class SalesReportService {

    @Autowired
    SalesReportRepository salesReportRepository;


    public ResponseEntity<byte[]> getSalesData(String category){

        List<SalesReportBean> salesList = null;
        Map<String, Object> param =  new HashMap<>();

        try{

            salesList = salesReportRepository.getDataList(category);
            double netRev = 0;
            for (SalesReportBean s: salesList
                 ) {
                netRev =+ s.getTotalRevenue();
            }
            //param.put("date", date);
            param.put("category", category);
            param.put("netRevenue", netRev);

            JasperPrint salesReport =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:static/reports/sales.jrxml")
                                                    .getAbsolutePath()), param, new JRBeanCollectionDataSource(salesList) );

            HttpHeaders headers = new HttpHeaders();
            System.out.println("st2");
            //set the PDF format
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "sales.pdf");
            //create the report in PDF format
            return new ResponseEntity<byte[]>
                    (JasperExportManager.exportReportToPdf(salesReport), headers, HttpStatus.OK);
        } catch(Exception e){

            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

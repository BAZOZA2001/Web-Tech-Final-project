package com.datanavigator.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataExportService {

    public StreamingResponseBody exportDataAsCSV(List<Map<String, Object>> data) {
        return outputStream -> {
            writeDataAsCSV(outputStream, data);
        };
    }

    private void writeDataAsCSV(OutputStream outputStream, List<Map<String, Object>> data) throws IOException {
        String header = String.join(",", data.get(0).keySet()) + "\n";
        outputStream.write(header.getBytes());
        for (Map<String, Object> row : data) {
            String line = String.join(",", row.values().stream().map(Object::toString).toArray(String[]::new)) + "\n";
            outputStream.write(line.getBytes());
        }
    }

    public byte[] exportDataAsPDF(List<Map<String, Object>> data) {
        try {
            // Load Jasper template
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/sample_report.jrxml"));

            // Convert data to JRBeanCollectionDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            // Parameters (empty if not needed)
            Map<String, Object> parameters = new HashMap<>();

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}

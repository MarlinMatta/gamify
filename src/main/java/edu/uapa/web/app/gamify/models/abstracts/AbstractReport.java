package edu.uapa.web.app.gamify.models.abstracts;

import edu.uapa.web.app.gamify.models.Language;
import edu.uapa.web.app.gamify.models.ReportParameter;
import net.sf.jasperreports.engine.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractReport {

    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    protected Map<String, Object> parameters;

    public AbstractReport() {
        parameters = new HashMap<>();
    }

    protected void prepareJasper(String path, String language, String imgAddress) {
        try {
            jasperReport = JasperCompileManager.compileReport(path);
            addParameterImageLogo(imgAddress);
            addParameterLanguage(language);
        } catch (Exception ex) {
            Logger.getLogger(AbstractReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void fillReport(JRDataSource dataSource) {
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        } catch (JRException ex) {
            Logger.getLogger(AbstractReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addParameterLanguage(String language) {
        try {
            if (language.equals("1")) {
                parameters.put(ReportParameter.REPORT_RESOURCE_BUNDLE, Language.getInstance().getI18N(Language.SPANISH));

            } else if (language.equals("2")) {
                parameters.put(ReportParameter.REPORT_RESOURCE_BUNDLE, Language.getInstance().getI18N(Language.ENGLISH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addParameterImageLogo(String urlImg) {
        if (!urlImg.isEmpty())
            parameters.put(ReportParameter.IMAGE_LOGO, urlImg);
    }

    public byte[] getPDF() {
        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(AbstractReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ByteArrayOutputStream getBytesStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        } catch (JRException ex) {
            Logger.getLogger(AbstractReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public void save() {
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/yelson/Desktop/klk.pdf");
        } catch (JRException ex) {
            Logger.getLogger(AbstractReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
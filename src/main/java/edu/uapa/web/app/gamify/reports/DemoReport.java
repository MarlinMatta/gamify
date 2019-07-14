package edu.uapa.web.app.gamify.reports;

import edu.uapa.web.app.gamify.models.ReportParameter;
import edu.uapa.web.app.gamify.models.abstracts.AbstractReport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public class DemoReport extends AbstractReport {
    public DemoReport(ResourceLoader resourceLoader, String language) {
        super();
        try {
            String path = resourceLoader.getResource("classpath:reports/rpt_users.jrxml").getURI().getPath();
            prepareJasper(path, language, "");
            parameters.put(ReportParameter.ID, "1");
            fillReport(new JREmptyDataSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

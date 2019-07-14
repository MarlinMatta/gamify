package edu.uapa.web.app.gamify.controllers.report;

import edu.uapa.web.app.gamify.reports.DemoReport;
import edu.uapa.web.app.gamify.utils.Urls;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping(value = Urls.REPORT)
public class ReportController {

    private final ResourceLoader resourceLoader;

    public ReportController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = Urls.DEMO, method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> demo() {
        DemoReport demoReport = new DemoReport(resourceLoader, "1");
        HttpHeaders headers = this.header("demo", "pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_PDF));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(demoReport.getBytesStream().toByteArray());
    }

    private HttpHeaders header(String reportName, String format) {
        String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String random = String.valueOf(Math.round(Math.random() * 100000));
        String attachment = String.format("attachment; filename=%s_%s_%s.%s", reportName, dateNow, random, format);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", attachment);
        return headers;
    }
}

package com.tsystems.quarkus.sample;

import io.quarkus.qute.Template;
import io.quarkus.qute.api.ResourcePath;
import io.quarkus.scheduler.Scheduled;

import javax.inject.Inject;

public class ReportGenerator {

    @Inject
    SampleService service;

    @ResourcePath("reports/v1/report_01")
    Template report;

    @Scheduled(cron = "0 30 * * * ?")
    void generate() {
        String result = report
                .data("samples", service.get())
                .data("now", java.time.LocalDateTime.now())
                .render();
        // Write the result somewhere...
    }
}

package com.infosupport.hotel;

import io.opencensus.exporter.trace.stackdriver.StackdriverTraceConfiguration;
import io.opencensus.exporter.trace.stackdriver.StackdriverTraceExporter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class StackDriverConfiguration {
    private final Log log = LogFactory.getLog(StackDriverConfiguration.class);

    public StackDriverConfiguration() {
        long sleepTime = 10; /* seconds */
        int maxAttempts = 5;
        boolean traceExporterRegistered = false;

        for (int i = 0; i < maxAttempts; i++) {
            try {
                if (!traceExporterRegistered) {
                    StackdriverTraceExporter.createAndRegister(
                            StackdriverTraceConfiguration.builder().build());
                    traceExporterRegistered = true;
                }
            } catch (Exception e) {
                if (i == (maxAttempts - 1)) {
                    log.warn(
                            "Failed to register Stackdriver Exporter."
                                    + " Tracing and Stats data will not reported to Stackdriver. Error message: "
                                    + e.toString());
                } else {
                    log.info("Attempt to register Stackdriver Exporter in " + sleepTime + " seconds ");
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(sleepTime));
                    } catch (Exception se) {
                        log.warn("Exception while sleeping" + se.toString());
                    }
                }
            }
        }
    }
}


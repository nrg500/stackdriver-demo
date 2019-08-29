package com.infosupport.hotel;

import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
    private static final Tracer tracer = Tracing.getTracer();
    private final Log log = LogFactory.getLog(HotelController.class);

    @GetMapping("/room")
    public String getRoom() throws InterruptedException {
        Span span = SpanUtils.buildSpan(tracer,"HotelController getRoom").startSpan();
        int price = (int) (200 + Math.random()*400);
        Thread.sleep(price);
        if (Math.random() > 0.8) {
            log.warn("No rooms available!");
            throw new RuntimeException("Failed to find a room");
        }
        log.info("Room found for: " + price);
        span.end();
        return "You'll be staying at hotel Transylvania for: " + price + " euros a night.";
    }
}

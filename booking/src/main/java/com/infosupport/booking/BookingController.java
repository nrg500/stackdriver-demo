package com.infosupport.booking;

import io.opencensus.common.Scope;
import io.opencensus.trace.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BookingController {
    private final Log log = LogFactory.getLog(getClass());
    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/")
    public String getBooking(HttpServletRequest incomingRequest) throws URISyntaxException, InterruptedException {
        Span span = SpanUtils.buildSpan(tracer, "BookingController getBooking").startSpan();
        String url = "http://hotel/room";
        String result;
        Thread.sleep(100 + (int)Math.random()*200);
        try (Scope ws = tracer.withSpan(span)) {
            result = "Congratulations, we found you a room!"+ HttpUtils.callEndpoint(url, HttpMethod.GET);
        } catch (Exception e) {
            span.setStatus(Status.ABORTED);
            span.addAnnotation("Error while calling service");
            log.error("Error while calling service: {}" +  e.getMessage());
            result = "Sorry, we could not find you any hotel rooms.";
        }
        span.end();
        return result;
    }
}

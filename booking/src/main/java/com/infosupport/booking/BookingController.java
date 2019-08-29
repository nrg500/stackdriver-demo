package com.infosupport.booking;

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

    @GetMapping("/")
    public String getBooking(HttpServletRequest incomingRequest) throws URISyntaxException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://hotel/room");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        Thread.sleep(100 + (int)Math.random()*200);
        String result = "Sorry, we could not find you any hotel rooms.";
        try {
            ResponseEntity<String> hotelRoom = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
            if(hotelRoom.getStatusCode() == HttpStatus.OK){
                result = "Congratulations, we found you a room ! :" + hotelRoom.getBody();
                log.info("Room found " + hotelRoom.getBody());
            } else {
                result = "Sorry, we could not find you a room.";
            }
        } catch (RestClientException e) {
            log.error("Could not contact hotelservice. Exception: ", e);
        }
        
        return result;
    }
}

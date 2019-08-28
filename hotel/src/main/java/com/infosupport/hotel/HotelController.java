package com.infosupport.hotel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    @GetMapping("/room")
    public String getRoom() throws InterruptedException {
        int price = (int) (200 + Math.random()*400);
        Thread.sleep(price);
        if (Math.random() > 0.8) {
            throw new RuntimeException("Failed to find a room");
        }
        return "You'll be staying at the Mariott for: " + price + " euros a night.";
    }
}

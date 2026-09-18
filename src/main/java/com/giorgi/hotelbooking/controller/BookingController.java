package com.giorgi.hotelbooking.controller;

import com.giorgi.hotelbooking.dto.BookingRequest;
import com.giorgi.hotelbooking.model.Booking;
import com.giorgi.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequest request,
            Authentication authentication
    ) {
        Booking booking = bookingService.createBooking(request, authentication.getName());
        return ResponseEntity.ok(booking);
    }
}
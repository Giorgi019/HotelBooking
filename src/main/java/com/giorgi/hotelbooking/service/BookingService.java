package com.giorgi.hotelbooking.service;

import com.giorgi.hotelbooking.dto.BookingRequest;
import com.giorgi.hotelbooking.exception.InvalidBookingException;
import com.giorgi.hotelbooking.exception.ResourceNotFoundException;
import com.giorgi.hotelbooking.model.Booking;
import com.giorgi.hotelbooking.model.Room;
import com.giorgi.hotelbooking.model.User;
import com.giorgi.hotelbooking.repository.BookingRepository;
import com.giorgi.hotelbooking.repository.RoomRepository;
import com.giorgi.hotelbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public Booking createBooking(BookingRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + request.getRoomId()));

        if (request.getCheckInDate().isAfter(request.getCheckOutDate()) || request.getCheckInDate().isEqual(request.getCheckOutDate())) {
            throw new InvalidBookingException("Check-out date must be after check-in date.");
        }

        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                room.getId(), request.getCheckInDate(), request.getCheckOutDate()
        );

        if (!overlappingBookings.isEmpty()) {
            throw new InvalidBookingException("Sorry, the room is already booked for these dates.");
        }

        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .build();

        return bookingRepository.save(booking);
    }
}
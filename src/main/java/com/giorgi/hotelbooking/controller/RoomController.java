package com.giorgi.hotelbooking.controller;

import com.giorgi.hotelbooking.dto.RoomDto;
import com.giorgi.hotelbooking.model.Room;
import com.giorgi.hotelbooking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.addRoom(roomDto));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotel(hotelId));
    }
}
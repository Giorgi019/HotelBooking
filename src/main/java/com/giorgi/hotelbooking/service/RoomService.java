package com.giorgi.hotelbooking.service;

import com.giorgi.hotelbooking.dto.RoomDto;
import com.giorgi.hotelbooking.model.Hotel;
import com.giorgi.hotelbooking.model.Room;
import com.giorgi.hotelbooking.repository.HotelRepository;
import com.giorgi.hotelbooking.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public Room addRoom(RoomDto roomDto) {
        Hotel hotel = hotelRepository.findById(roomDto.getHotelId())
                .orElseThrow(() -> new RuntimeException("სასტუმრო ამ ID-ით არ მოიძებნა"));

        Room room = Room.builder()
                .roomNumber(roomDto.getRoomNumber())
                .capacity(roomDto.getCapacity())
                .pricePerNight(roomDto.getPricePerNight())
                .hotel(hotel)
                .build();

        return roomRepository.save(room);
    }

    public List<Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
}
package com.giorgi.hotelbooking.service;

import com.giorgi.hotelbooking.dto.HotelDto;
import com.giorgi.hotelbooking.model.Hotel;
import com.giorgi.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    // სასტუმროს დამატება
    public Hotel createHotel(HotelDto hotelDto) {
        Hotel hotel = Hotel.builder()
                .name(hotelDto.getName())
                .location(hotelDto.getLocation())
                .description(hotelDto.getDescription())
                .build();

        return hotelRepository.save(hotel);
    }

    // ყველა სასტუმროს წამოღება
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}

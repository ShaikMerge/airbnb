package com.shaik.projects.AirBnB.service;

import com.shaik.projects.AirBnB.dto.HotelDto;
import com.shaik.projects.AirBnB.entity.Hotel;
import com.shaik.projects.AirBnB.exception.ResourceNotFoundException;
import com.shaik.projects.AirBnB.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelDto createHotel(HotelDto hotel) {

        Hotel hotelEntity = modelMapper.map(hotel, Hotel.class);
        hotelEntity.setIsActive(false);
        hotelEntity =  hotelRepository.save(hotelEntity);

        return modelMapper.map(hotelEntity, HotelDto.class);

    }


     public HotelDto getHotelById(Long id) {

        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        return modelMapper.map(hotel, HotelDto.class);

    }

    public HotelDto updateHotel(Long id, HotelDto hotelDto) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        modelMapper.map(hotelDto, existingHotel);

        existingHotel.setId(id);
        Hotel updatedHotel = hotelRepository.save(existingHotel);

        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    public Boolean deleteHotel(Long id) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        hotelRepository.delete(existingHotel);

        // delete the future inventories of the hotel
        return true;
    }

    @Transactional
    public void activateHotel(Long id) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        existingHotel.setIsActive(true);

    }

}

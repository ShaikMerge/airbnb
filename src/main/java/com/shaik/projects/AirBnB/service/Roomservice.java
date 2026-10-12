package com.shaik.projects.AirBnB.service;


import com.shaik.projects.AirBnB.dto.RoomDto;
import com.shaik.projects.AirBnB.entity.Hotel;
import com.shaik.projects.AirBnB.entity.Room;
import com.shaik.projects.AirBnB.exception.ResourceNotFoundException;
import com.shaik.projects.AirBnB.repository.HotelRepository;
import com.shaik.projects.AirBnB.repository.RoomRepoository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Roomservice {

    private final RoomRepoository roomRepoository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;


    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {

         Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));
           Room roomEntity = modelMapper.map(roomDto, Room.class);
           roomEntity.setHotel(hotel);
           roomEntity =  roomRepoository.save(roomEntity);
           return modelMapper.map(roomEntity, RoomDto.class);
    }

    public List<RoomDto> getRoomsByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));

        return hotel.getRooms().
                stream().
                map((element) -> modelMapper.map(element, RoomDto.class))
                .toList();
    }

    public RoomDto getRoomById(Long roomId) {
        Room room = roomRepoository.findById(roomId).
                orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    public void deleteRoom(Long roomId) {
        Room existingRoom = roomRepoository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));

        roomRepoository.delete(existingRoom);
    }

}

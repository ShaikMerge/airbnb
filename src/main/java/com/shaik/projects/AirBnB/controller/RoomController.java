package com.shaik.projects.AirBnB.controller;

import com.shaik.projects.AirBnB.dto.RoomDto;
import com.shaik.projects.AirBnB.service.Roomservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final Roomservice roomservice;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {

        return ResponseEntity.ok(roomservice.createRoom(hotelId, roomDto));
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomservice.getRoomsByHotelId(hotelId));
    }

    @GetMapping
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long hotelId, @RequestParam Long roomId) {
        return ResponseEntity.ok(roomservice.getRoomById(roomId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRoom(@PathVariable Long hotelId, @RequestParam Long roomId) {
        roomservice.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

}

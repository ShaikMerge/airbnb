package com.shaik.projects.AirBnB.repository;

import com.shaik.projects.AirBnB.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepoository extends JpaRepository<Room, Long> {
}

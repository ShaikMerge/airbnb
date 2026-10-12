package com.shaik.projects.AirBnB.repository;

import com.shaik.projects.AirBnB.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository  extends JpaRepository<Hotel, Long> {
}

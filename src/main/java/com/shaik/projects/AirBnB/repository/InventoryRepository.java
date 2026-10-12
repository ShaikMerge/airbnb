package com.shaik.projects.AirBnB.repository;

import com.shaik.projects.AirBnB.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
}

package edu.lms.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.lms.service.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

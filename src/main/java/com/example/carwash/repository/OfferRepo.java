package com.example.carwash.repository;

import com.example.carwash.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepo extends JpaRepository<Offer, Long> {
}

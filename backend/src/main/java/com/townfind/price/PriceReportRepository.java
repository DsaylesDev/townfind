package com.townfind.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceReportRepository extends JpaRepository<PriceReport, Long> {
    Optional<PriceReport> findTopByStore_IdAndItem_IdOrderByCreatedAtDesc(Long storeId, Long itemId);
}

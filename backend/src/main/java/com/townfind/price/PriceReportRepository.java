package com.townfind.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PriceReportRepository extends JpaRepository<PriceReport, Long> {
    Optional<PriceReport> findTopByStore_IdAndItem_IdOrderByCreatedAtDesc(Long storeId, Long itemId);
    List<PriceReport> findTop10ByItem_IdOrderByCreatedAtDesc(Long itemId);
    List<PriceReport> findTop20ByStore_IdAndItem_IdOrderByCreatedAtDesc(Long storeId, Long itemId);
}

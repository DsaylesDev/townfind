package com.townfind.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByNameContainingIgnoreCase(String name);

    // ✅ Add:
    @Query("SELECT s FROM Store s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:city IS NULL OR LOWER(s.city) LIKE LOWER(CONCAT('%', :city, '%'))) " +
            "AND (:state IS NULL OR LOWER(s.state) = LOWER(:state))")
    List<Store> searchStores(@Param("name") String name,
                             @Param("city") String city,
                             @Param("state") String state);
}

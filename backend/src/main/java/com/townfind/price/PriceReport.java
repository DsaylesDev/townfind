package com.townfind.price;

import com.townfind.item.Item;
import com.townfind.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(indexes = {
        @Index(name = "idx_pr_store_item_created", columnList = "store_id,item_id,createdAt DESC"),
        @Index(name = "idx_pr_created", columnList = "createdAt")
})
public class PriceReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;

    // store as integer cents to avoid float rounding issues
    @Column(nullable = false)
    private Integer priceCents;

    private String imageUrl; // optional receipt/product photo

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
    }
}

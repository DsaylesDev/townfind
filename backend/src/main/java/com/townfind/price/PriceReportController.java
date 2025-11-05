package com.townfind.price;

import com.townfind.item.Item;
import com.townfind.item.ItemRepository;
import com.townfind.store.Store;
import com.townfind.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceReportController {
    private final PriceReportRepository repo;
    private final StoreRepository storeRepo;
    private final ItemRepository itemRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceReport create(@RequestBody Map<String, Object> body) {
        Long storeId = ((Number) body.get("storeId")).longValue();
        Long itemId  = ((Number) body.get("itemId")).longValue();
        Integer priceCents = ((Number) body.get("priceCents")).intValue();
        String imageUrl = (String) body.getOrDefault("imageUrl", null);

        Store store = storeRepo.findById(storeId).orElseThrow();
        Item item   = itemRepo.findById(itemId).orElseThrow();

        return repo.save(PriceReport.builder()
                .store(store).item(item)
                .priceCents(priceCents)
                .imageUrl(imageUrl)
                .build());
    }

    @GetMapping("/latest")
    public PriceReport latest(@RequestParam Long storeId, @RequestParam Long itemId) {
        return repo.findTopByStore_IdAndItem_IdOrderByCreatedAtDesc(storeId, itemId).orElseThrow();
    }
}

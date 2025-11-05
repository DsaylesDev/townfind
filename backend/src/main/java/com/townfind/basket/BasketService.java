package com.townfind.basket;

import com.townfind.item.ItemRepository;
import com.townfind.price.PriceReport;
import com.townfind.price.PriceReportRepository;
import com.townfind.store.Store;
import com.townfind.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final StoreRepository storeRepo;
    private final PriceReportRepository priceRepo;
    private final ItemRepository itemRepo;

    public BasketSummary findCheapestSingleStore(List<Long> itemIds) {
        List<Store> stores = storeRepo.findAll();
        BasketSummary best = null;

        for (Store s : stores) {
            long total = 0;
            List<BasketLine> lines = new ArrayList<>();
            boolean missing = false;

            for (Long itemId : itemIds) {
                Optional<PriceReport> pr = priceRepo.findTopByStore_IdAndItem_IdOrderByCreatedAtDesc(s.getId(), itemId);
                if (pr.isEmpty()) { missing = true; break; }
                PriceReport r = pr.get();
                total += r.getPriceCents();
                lines.add(new BasketLine(itemId, r.getPriceCents(), s.getId()));
            }

            if (!missing) {
                BasketSummary candidate = new BasketSummary(s.getId(), total, lines);
                if (best == null || candidate.totalCents() < best.totalCents()) best = candidate;
            }
        }
        return best; // may be null if no store has prices for all items
    }

    public record BasketLine(Long itemId, Integer priceCents, Long storeId) { }
    public record BasketSummary(Long storeId, Long totalCents, List<BasketLine> lines) { }
}


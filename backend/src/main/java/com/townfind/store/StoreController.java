package com.townfind.store;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository repo;

    @GetMapping
    public List<Store> getAll(@RequestParam(defaultValue = "") String q) {
        return q.isBlank() ? repo.findAll() : repo.findByNameContainingIgnoreCase(q);
    }

    @PostMapping
    public Store create(@RequestBody Store store) {
        return repo.save(store);
    }
    @GetMapping("/search")
    public List<Store> searchStores(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {
        return repo.searchStores(name, city, state);
    }

}


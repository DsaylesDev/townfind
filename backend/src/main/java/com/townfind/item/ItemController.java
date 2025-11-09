package com.townfind.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository repo;

    @GetMapping
    public List<Item> search(@RequestParam(defaultValue = "") String q) {
        return q.isBlank() ? repo.findAll() : repo.findByNameContainingIgnoreCase(q);
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return repo.save(item);
    }

    @GetMapping("/search")
    public List<Item> searchItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand) {
        return repo.searchItems(name, brand);
    }

}


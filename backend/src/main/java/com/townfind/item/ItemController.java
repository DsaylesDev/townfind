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
}


package com.townfind.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService service;

    @PostMapping("/cheapest")
    @ResponseStatus(HttpStatus.OK)
    public BasketService.BasketSummary cheapest(@RequestParam String itemIdsCsv) {
        List<Long> itemIds = Arrays.stream(itemIdsCsv.split(","))
                .map(String::trim).filter(s -> !s.isBlank())
                .map(Long::parseLong).toList();

        return service.findCheapestSingleStore(itemIds);
    }
}


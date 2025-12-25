package com.slotify.controller;

import com.slotify.domain.Resource;
import com.slotify.domain.Slot;
import com.slotify.repository.ResourceRepository;
import com.slotify.service.SlotGenerationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotGenerationController {

    private final SlotGenerationService slotGenerationService;
    private final ResourceRepository resourceRepository;

    public SlotGenerationController(
            SlotGenerationService slotGenerationService,
            ResourceRepository resourceRepository
    ) {
        this.slotGenerationService = slotGenerationService;
        this.resourceRepository = resourceRepository;
    }

    @PostMapping("/generate")
    public List<Slot> generateSlots(
            @RequestParam Long resourceId,
            @RequestParam String date,
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam int minutes
    ) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found"));

        return slotGenerationService.generateSlots(
                resource,
                LocalDate.parse(date),
                LocalTime.parse(start),
                LocalTime.parse(end),
                minutes
        );
    }
}

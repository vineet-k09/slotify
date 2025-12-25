package com.slotify.service;

import com.slotify.domain.*;
import com.slotify.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlotGenerationService {

    private final SlotRepository slotRepository;

    public SlotGenerationService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }
    
    @Transactional
    public List<Slot> generateSlots(
        Resource resource,
        LocalDate date,
        LocalTime start,
        LocalTime end,
        int minutesPerSlot
    ) {
        List<Slot> created = new ArrayList<>();

        LocalDateTime cursor = LocalDateTime.of(date, start);
        LocalDateTime boundary = LocalDateTime.of(date, end);

        while (cursor.plusMinutes(minutesPerSlot).compareTo(boundary) <= 0){
            LocalDateTime slotEnd = cursor.plusMinutes(minutesPerSlot);

            if(!slotRepository.existsByResourceAndStartTimeAndEndTime(resource,cursor,slotEnd)) {
                Slot slot = new Slot(resource, cursor, slotEnd, SlotStatus.AVAILABLE);

                created.add(slotRepository.save(slot));
            }

            cursor = slotEnd;
        }

        return created;
    }
}

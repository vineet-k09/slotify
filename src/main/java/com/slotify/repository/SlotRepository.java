package com.slotify.repository;

import com.slotify.domain.Slot;
import com.slotify.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    boolean existsByResourceAndStartTimeAndEndTime(
            Resource resource,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    List<Slot> findByResourceAndStartTimeBetween(
            Resource resource,
            LocalDateTime from,
            LocalDateTime to
    );
}

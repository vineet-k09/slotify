package com.slotify.slotify.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (
    name = "slots",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"resource_id", "start_time", "end_time"}
        )
    }
)
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status;

    protected Slot() {}

    public Slot (Resource resource, 
        LocalDateTime startTime,
        LocalDateTime endTime,
        SlotStatus status
    ) {
        this.resource = resource;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    //getter only (immutabilty-ish)
    public Long getId() {return id;}
    public Resource getResource() { return resource; }
    public LocalDateTime getStartTime() {return startTime;}
    public LocalDateTime getEndTime() {return endTime;}
    public SlotStatus getStatus() {return status;}
}

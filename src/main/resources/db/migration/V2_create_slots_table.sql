CREATE TABLE slots (
    id BIGSERIAL PRIMARY KEY,
    resource_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status TEXT NOT NULL,

    CONSTRAINT fk_slots_resources
        FOREIGN KEY (resource_id)
        REFERENCES resources(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_slot_time
        CHECK (start_time < end_time),

    CONSTRAINT uq_resource_slot
        UNIQUE (resource_id, start_time, end_time)
)
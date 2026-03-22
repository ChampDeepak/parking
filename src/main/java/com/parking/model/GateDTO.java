package com.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Raw DTO for a gate entry in the JSON file.
 */
public class GateDTO {

    @JsonProperty("id")
    public String id;

    @JsonProperty("location")
    public Location location;

    /** Convert raw DTO → domain Gate */
    public Gate toDomain() {
        return new Gate(id, location);
    }
}

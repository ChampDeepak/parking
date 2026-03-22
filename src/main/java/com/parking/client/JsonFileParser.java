package com.parking.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsible for reading JSON files from disk and returning domain objects.
 *
 * SINGLE RESPONSIBILITY: This class does exactly one thing — file → domain objects.
 * It knows about JSON and file I/O. Nothing else does.
 */
public class JsonFileParser {

    // ObjectMapper is thread-safe and expensive to create — reuse it
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads a slots JSON file and returns a list of domain Slot objects.
     *
     * @param filePath path to slots.json (from CLI args)
     * @return List<Slot> with SlotType resolved via SlotTypeRegistry
     */
    public List<Slot> parseSlots(String filePath) throws IOException {
        List<SlotDTO> dtos = objectMapper.readValue(
            new File(filePath),
            new TypeReference<List<SlotDTO>>() {}  // tells Jackson: parse as List<SlotDTO>
        );

        return dtos.stream()
                   .map(SlotDTO::toDomain)   // DTO → domain object (SlotType resolved here)
                   .collect(Collectors.toList());
    }

    /**
     * Reads a gates JSON file and returns a list of domain Gate objects.
     *
     * @param filePath path to gates.json (from CLI args)
     * @return List<Gate>
     */
    public List<Gate> parseGates(String filePath) throws IOException {
        List<GateDTO> dtos = objectMapper.readValue(
            new File(filePath),
            new TypeReference<List<GateDTO>>() {}
        );

        return dtos.stream()
                   .map(GateDTO::toDomain)
                   .collect(Collectors.toList());
    }
}

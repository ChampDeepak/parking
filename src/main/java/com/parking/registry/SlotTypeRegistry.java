package com.parking.registry;

import com.parking.model.SlotType;

import java.util.HashMap;
import java.util.Map;

/**
 * Central registry that maps string names (as they appear in JSON) to SlotType objects.
 *
 * WHY THIS EXISTS:
 * The JSON stores slot type as a plain string: "SMALL", "MEDIUM", "LARGE".
 * But our domain model uses SlotType objects (which carry hourlyRate, sizeOrder, etc.).
 * This registry is the single bridge between the two worlds.
 *
 * OCP BENEFIT:
 * To add a new slot type (e.g. "EV"), you register it here — ONE place.
 * Nothing else in the codebase needs to change.
 */
public class SlotTypeRegistry {

    private static final Map<String, SlotType> registry = new HashMap<>();

    static {
        // Register all known slot types here.
        // name (must match JSON string exactly) | hourlyRate | sizeOrder
        register(new SlotType("SMALL",  30.0, 1));
        register(new SlotType("MEDIUM", 50.0, 2));
        register(new SlotType("LARGE",  80.0, 3));

        // Adding a new type tomorrow? Just add one line:
        // register(new SlotType("EV", 100.0, 2));
    }

    private static void register(SlotType slotType) {
        registry.put(slotType.getName(), slotType);
    }

    /**
     * Resolve a string name from JSON to a SlotType object.
     * Throws clearly if the JSON contains an unknown type — fail fast is better
     * than silently returning null and crashing later.
     */
    public static SlotType resolve(String name) {
        SlotType slotType = registry.get(name);
        if (slotType == null) {
            throw new IllegalArgumentException(
                "Unknown SlotType: '" + name + "'. " +
                "Register it in SlotTypeRegistry before using it in JSON."
            );
        }
        return slotType;
    }
}
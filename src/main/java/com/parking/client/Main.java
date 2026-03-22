package com.parking.client;

import com.parking.model.Gate;
import com.parking.model.Slot;

import java.util.List;

/**
 * Entry point of the application.
 *
 * USAGE:
 *   java -jar parking.jar <path-to-slots.json> <path-to-gates.json>
 *
 * Example:
 *   java -jar parking.jar ./data/slots.json ./data/gates.json
 */
public class Main {

    public static void main(String[] args) {

        // ── 1. Validate CLI args ──────────────────────────────────────────────
        if (args.length < 2) {
            System.err.println("ERROR: Two file paths required.");
            System.err.println("Usage: java -jar parking.jar <slots.json> <gates.json>");
            System.exit(1);
        }

        String slotsFilePath = args[0];
        String gatesFilePath = args[1];

        System.out.println("Reading slots from : " + slotsFilePath);
        System.out.println("Reading gates from : " + gatesFilePath);

        // ── 2. Parse JSON files → domain objects ─────────────────────────────
        JsonFileParser parser = new JsonFileParser();

        List<Slot> slotsRepo;
        List<Gate> gatesRepo;

        try {
            slotsRepo = parser.parseSlots(slotsFilePath);
            gatesRepo = parser.parseGates(gatesFilePath);
        } catch (Exception e) {
            System.err.println("ERROR: Failed to parse input files.");
            System.err.println("Reason: " + e.getMessage());
            System.exit(1);
            return; // compiler needs this after System.exit
        }

        // ── 3. Validate parsed data ───────────────────────────────────────────
        System.out.println("Loaded " + slotsRepo.size() + " slots.");
        System.out.println("Loaded " + gatesRepo.size() + " gates.");

        if (slotsRepo.isEmpty()) {
            System.err.println("ERROR: No slots found. Check your slots.json.");
            System.exit(1);
        }
        if (gatesRepo.isEmpty()) {
            System.err.println("ERROR: No gates found. Check your gates.json.");
            System.exit(1);
        }

        // ── 4. Build ParkingLot via factory ───────────────────────────────────
        // ParkingLotFactory.get() will be called here once you wire it in.
        // The repos are ready — factory decides how to structure them internally.
        //
        // ParkingLot parkingLot = ParkingLotFactory.get(slotsRepo, gatesRepo);
        //
        // (Uncomment the line above once ParkingLotFactory is implemented)

        // ── 5. DEBUG: print a few entries to confirm parsing worked ──────────
        System.out.println("\n--- Sample Slots ---");
        slotsRepo.stream().limit(3).forEach(System.out::println);

        System.out.println("\n--- Sample Gates ---");
        gatesRepo.forEach(System.out::println);
    }
}

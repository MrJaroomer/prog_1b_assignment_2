package com.mycompany.prog_1b_assignment_2;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class RescueCaseTest {

    // tests the extra 5000 surgery fee
    @Test
    public void testInjuredAnimalCostWithSurgery() {
        InjuredAnimalRescue testCase = new InjuredAnimalRescue(
            "T001", "Elephant", "Kruger", "Ranger Pete", 
            "Pending", "Broken Leg", 4000.0, true
        );
        assertEquals(9000.0, testCase.calculateTotalCost(), 0.01);
    }

    // tests the extra 8000 specialist fee
    @Test
    public void testEndangeredSpeciesCostWithSpecialist() {
        EndangeredSpeciesRescue testCase = new EndangeredSpeciesRescue(
            "T002", "Rhino", "Limpopo", "Ranger Sam", 
            "Pending", "Critically Endangered", 5000.0, true
        );
        assertEquals(13000.0, testCase.calculateTotalCost(), 0.01);
    }

    // check if low, medium, and high flags map properly
    @Test
    public void testPriorityDeterminations() {
        InjuredAnimalRescue injuredHigh = new InjuredAnimalRescue("T003", "Lion", "Kruger", "Ranger Pete", "Pending", "Wound", 1000.0, true);
        assertEquals("High", injuredHigh.determineRescuePriority());

        EndangeredSpeciesRescue endangeredHigh = new EndangeredSpeciesRescue("T004", "Rhino", "Limpopo", "Ranger Sam", "Pending", "Critically Endangered", 5000.0, false);
        assertEquals("High", endangeredHigh.determineRescuePriority());

        EndangeredSpeciesRescue endangeredMed = new EndangeredSpeciesRescue("T005", "Leopard", "Limpopo", "Ranger Sam", "Pending", "Endangered", 3000.0, false);
        assertEquals("Medium", endangeredMed.determineRescuePriority());
        
        EndangeredSpeciesRescue endangeredLow = new EndangeredSpeciesRescue("T006", "Bird", "Limpopo", "Ranger Sam", "Pending", "Vulnerable", 1000.0, false);
        assertEquals("Low", endangeredLow.determineRescuePriority());
    }

    // checks that status cycles change
    @Test
    public void testStatusLifecycleUpdates() {
        OrphanedAnimalRescue testCase = new OrphanedAnimalRescue(
            "T007", "Cheetah", "Kruger", "Ranger John", 
            "Pending", 4, 2000.0, false
        );
        
        assertEquals("Pending", testCase.getStatus());
        
        testCase.startRescueOperation();
        assertEquals("In Progress", testCase.getStatus());
        
        testCase.completeRescueOperation();
        assertEquals("Completed", testCase.getStatus());
    }

    // test for duplicate id registration errors
    @Test
    public void testDuplicateIdDetectionLogic() {
        ArrayList<RescueCase> testList = new ArrayList<>();
        testList.add(new OrphanedAnimalRescue("WR102", "Rhino", "Hluhluwe", "Ranger Sam", "Pending", 2, 9000.0, false));
        
        String inputIdAttempt = "WR102"; 
        boolean duplicateFound = false;
        
        for (RescueCase rc : testList) {
            if (rc.getRescueCaseId().equalsIgnoreCase(inputIdAttempt)) {
                duplicateFound = true;
                break;
            }
        }
        
        assertTrue(duplicateFound);
    }
}

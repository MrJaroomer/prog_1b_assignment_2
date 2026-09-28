package com.mycompany.prog_1b_assignment_2;

public class EndangeredSpeciesRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String rescueCaseId, String species, String rescueLocation, String assignedRanger, String status, String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(rescueCaseId, "Endangered Species Rescue", species, rescueLocation, assignedRanger, status);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    @Override
    public double calculateTotalCost() {
        double total = securityCost;
        // add 8000 fee if specialist team is checked yes
        if (specialistTeamRequired) {
            total += 8000.00;
        }
        return total;
    }

    @Override
    public String determineRescuePriority() {
        // check classification text to get priority level
        if (conservationClassification.equalsIgnoreCase("Critically Endangered")) {
            return "High";
        } else if (conservationClassification.equalsIgnoreCase("Endangered")) {
            return "Medium";
        }
        return "Low";
    }
}

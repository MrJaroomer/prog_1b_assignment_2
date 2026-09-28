package com.mycompany.prog_1b_assignment_2;

public class OrphanedAnimalRescue extends RescueCase {
    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String rescueCaseId, String species, String rescueLocation, String assignedRanger, String status, int estimatedAgeMonths, double feedingCost, boolean fosterCareRequired) {
        super(rescueCaseId, "Orphaned Animal Rescue", species, rescueLocation, assignedRanger, status);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    @Override
    public double calculateTotalCost() {
        double total = feedingCost;
        // add extra 2500 fee if foster care is active
        if (fosterCareRequired) {
            total += 2500.00;
        }
        return total;
    }

    @Override
    public String determineRescuePriority() {
        // babies under 3 months are high priority
        return (estimatedAgeMonths < 3) ? "High" : "Medium";
    }
}

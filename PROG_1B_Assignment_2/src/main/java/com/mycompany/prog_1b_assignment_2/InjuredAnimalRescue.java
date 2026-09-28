package com.mycompany.prog_1b_assignment_2;

public class InjuredAnimalRescue extends RescueCase {
    private String injuryDescription;
    private double vetTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String rescueCaseId, String species, String rescueLocation, String assignedRanger, String status, String injuryDescription, double vetTreatmentCost, boolean surgeryRequired) {
        super(rescueCaseId, "Injured Animal Rescue", species, rescueLocation, assignedRanger, status);
        this.injuryDescription = injuryDescription;
        this.vetTreatmentCost = vetTreatmentCost;
        this.surgeryRequired = surgeryRequired;
    }

    @Override
    public double calculateTotalCost() {
        double total = vetTreatmentCost;
        // add extra 5000 fee if they need surgery
        if (surgeryRequired) {
            total += 5000.00;
        }
        return total;
    }

    @Override
    public String determineRescuePriority() {
        return surgeryRequired ? "High" : "Medium";
    }
}

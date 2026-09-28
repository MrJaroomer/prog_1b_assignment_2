package com.mycompany.prog_1b_assignment_2;

public abstract class RescueCase implements RescueOperations {
    private String rescueCaseId;
    private String rescueType;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private String status;

    public RescueCase(String rescueCaseId, String rescueType, String species, String rescueLocation, String assignedRanger, String status) {
        this.rescueCaseId = rescueCaseId;
        this.rescueType = rescueType;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.status = status;
    }

    // subclasses will handle these calculations
    public abstract double calculateTotalCost();
    public abstract String determineRescuePriority();

    @Override
    public void startRescueOperation() {
        this.status = "In Progress";
    }

    @Override
    public void completeRescueOperation() {
        this.status = "Completed";
    }

    @Override
    public void generateRescueSummary() {
        System.out.printf("Case ID        : %s\n", this.rescueCaseId);
        System.out.printf("Type           : %s\n", this.rescueType);
        System.out.printf("Species        : %s\n", this.species);
        System.out.printf("Location       : %s\n", this.rescueLocation);
        System.out.printf("Assigned Ranger: %s\n", this.assignedRanger);
        System.out.printf("Priority       : %s\n", determineRescuePriority());
        System.out.printf("Status         : %s\n", this.status);
        
        // replaces comma with space for rand currency layout
        String formattedCost = String.format(java.util.Locale.US, "%,.2f", calculateTotalCost()).replace(',', ' ');
        System.out.printf("Total Cost     : R%s\n", formattedCost);
        System.out.println();
    }

    public String getRescueCaseId() { return rescueCaseId; }
    public String getRescueType() { return rescueType; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

package com.mycompany.prog_1b_assignment_2;

import java.util.ArrayList;
import java.util.Scanner;

public class PROG_1B_Assignment_2 {
    // empty list to store our cases
    private static ArrayList<RescueCase> rescueCases = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;
        
        // loop the main menu options
        do {
            System.out.println("=========================================");
            System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Create Rescue Case");
            System.out.println("2. Search Rescue Case");
            System.out.println("3. Update Rescue Status");
            System.out.println("4. Display All Rescue Cases");
            System.out.println("5. Rescue Report");
            System.out.println("6. Exit");
            System.out.println("=========================================");
            System.out.print("Select an option: ");

            try {
                option = Integer.parseInt(scanner.nextLine().trim());
                switch (option) {
                    case 1 -> createRescueCase();
                    case 2 -> searchRescueCase();
                    case 3 -> updateRescueStatus();
                    case 4 -> displayAllRescueCases();
                    case 5 -> generateRescueReport();
                    case 6 -> System.out.println("Exiting application. Goodbye!");
                    default -> System.out.println("Invalid selection! Choose between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Menu selection must be a numeric integer value.");
            }
            System.out.println(); 
        } while (option != 6);
    }
    private static void createRescueCase() {
        int typeChoice = 0;
        // loop until valid menu type picked
        while (true) {
            System.out.println("\n--- Select Rescue Type ---");
            System.out.println("1. Injured Animal Rescue");
            System.out.println("2. Orphaned Animal Rescue");
            System.out.println("3. Endangered Species Rescue");
            System.out.print("Choice: ");
            try {
                typeChoice = Integer.parseInt(scanner.nextLine().trim());
                if (typeChoice >= 1 && typeChoice <= 3) break;
                System.out.println("Error: Selection must be 1, 2, or 3.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (1, 2, or 3).");
            }
        }

        String id = "";
        // loop to validate case id input
        while (true) {
            System.out.print("Enter Rescue Case ID: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Validation Error: ID field cannot be left blank.");
                continue;
            }
            // check if id already exists in list
            boolean duplicateFound = false;
            for (RescueCase rc : rescueCases) {
                if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                    duplicateFound = true;
                    break;
                }
            }
            if (duplicateFound) {
                System.out.println("Error: Rescue Case ID must be unique. This ID already exists.");
                continue;
            }
            break;
        }

        System.out.print("Enter Species: ");
        String species = scanner.nextLine().trim();
        System.out.print("Enter Rescue Location: ");
        String location = scanner.nextLine().trim();
        System.out.print("Enter Assigned Ranger: ");
        String ranger = scanner.nextLine().trim();
        
        // default starting status
        String status = "Pending";

        if (typeChoice == 1) {
            System.out.print("Enter Injury Description: ");
            String desc = scanner.nextLine().trim();
            
            double vetCost = 0;
            while (true) {
                System.out.print("Enter Veterinary Treatment Cost (R): ");
                try {
                    vetCost = Double.parseDouble(scanner.nextLine().trim());
                    if (vetCost >= 0) break;
                    System.out.println("Validation Error: Cost cannot be negative.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must be a valid numeric amount.");
                }
            }

            boolean surgery = false;
            while (true) {
                System.out.print("Is surgery required? (yes/no): ");
                String inputStr = scanner.nextLine().trim().toLowerCase();
                if (inputStr.equals("yes")) { surgery = true; break; }
                if (inputStr.equals("no")) { surgery = false; break; }
                System.out.println("Error: You must type exactly 'yes' or 'no'.");
            }

            rescueCases.add(new InjuredAnimalRescue(id, species, location, ranger, status, desc, vetCost, surgery));

        } else if (typeChoice == 2) {
            int age = 0;
            while (true) {
                System.out.print("Enter Estimated Age in Months: ");
                try {
                    age = Integer.parseInt(scanner.nextLine().trim());
                    if (age >= 0) break;
                    System.out.println("Validation Error: Age cannot be negative.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must be a valid whole number.");
                }
            }

            double feedCost = 0;
            while (true) {
                System.out.print("Enter Feeding Cost (R): ");
                try {
                    feedCost = Double.parseDouble(scanner.nextLine().trim());
                    if (feedCost >= 0) break;
                    System.out.println("Validation Error: Cost cannot be negative.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must be a valid numeric amount.");
                }
            }

            boolean foster = false;
            while (true) {
                System.out.print("Is foster care required? (yes/no): ");
                String inputStr = scanner.nextLine().trim().toLowerCase();
                if (inputStr.equals("yes")) { foster = true; break; }
                if (inputStr.equals("no")) { foster = false; break; }
                System.out.println("Error: You must type exactly 'yes' or 'no'.");
            }

            rescueCases.add(new OrphanedAnimalRescue(id, species, location, ranger, status, age, feedCost, foster));

        } else {
            System.out.print("Enter Conservation Classification: ");
            String classification = scanner.nextLine().trim();

            double securityCost = 0;
            while (true) {
                System.out.print("Enter Security Cost (R): ");
                try {
                    securityCost = Double.parseDouble(scanner.nextLine().trim());
                    if (securityCost >= 0) break;
                    System.out.println("Validation Error: Cost cannot be negative.");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Must be a valid numeric amount.");
                }
            }

            boolean specialist = false;
            while (true) {
                System.out.print("Is a specialist team required? (yes/no): ");
                String inputStr = scanner.nextLine().trim().toLowerCase();
                if (inputStr.equals("yes")) { specialist = true; break; }
                if (inputStr.equals("no")) { specialist = false; break; }
                System.out.println("Error: You must type exactly 'yes' or 'no'.");
            }

            rescueCases.add(new EndangeredSpeciesRescue(id, species, location, ranger, status, classification, securityCost, specialist));
        }
        System.out.println("Rescue case created successfully!");
    }
    private static void searchRescueCase() {
        System.out.print("Enter Rescue Case ID to search: ");
        String id = scanner.nextLine().trim();
        // find item in list matching id
        for (RescueCase rc : rescueCases) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                System.out.println("-----------------------------------------------------------------------------");
                rc.generateRescueSummary();
                return;
            }
        }
        System.out.println("Rescue case with ID " + id + " was not found.");
    }

    private static void updateRescueStatus() {
        System.out.print("Enter Rescue Case ID to update: ");
        String id = scanner.nextLine().trim();
        for (RescueCase rc : rescueCases) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                System.out.println("Current status: " + rc.getStatus());
                System.out.println("1. Start Rescue Operation (Set to 'Rescue in Progress')");
                System.out.println("2. Complete Rescue Operation (Set to 'Completed')");
                System.out.print("Select action option: ");
                String action = scanner.nextLine().trim();
                if (action.equals("1")) {
                    rc.setStatus("Rescue in Progress"); 
                    System.out.println("Status successfully updated!");
                } else if (action.equals("2")) {
                    rc.completeRescueOperation();
                    System.out.println("Status successfully updated!");
                } else {
                    System.out.println("Invalid selection entry.");
                }
                return;
            }
        }
        System.out.println("Rescue case with ID " + id + " not found.");
    }

    private static void displayAllRescueCases() {
        if (rescueCases.isEmpty()) {
            System.out.println("No operational cases registered.");
            return;
        }
        System.out.println("\n-----------------------------------------------------------------------------");
        for (RescueCase rc : rescueCases) {
            rc.generateRescueSummary();
        }
    }

    private static void generateRescueReport() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("=============================================================================");
        System.out.println();
        
        double grandTotalCost = 0.0;
        // loops through entries to print summary rows
        for (int i = 0; i < rescueCases.size(); i++) {
            RescueCase rc = rescueCases.get(i);
            rc.generateRescueSummary();
            grandTotalCost += rc.calculateTotalCost();
            
            // adds dashed divider lines between records
            if (i < rescueCases.size() - 1) {
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println();
            }
        }
        
        System.out.println("=============================================================================");
        System.out.println();
        System.out.println("Total Rescue Cases : " + rescueCases.size());
        
        // replaces comma with space for rand output formatting
        String formattedGrandTotal = String.format(java.util.Locale.US, "%,.2f", grandTotalCost).replace(',', ' ');
        System.out.println("Total Rescue Cost  : R" + formattedGrandTotal);
    }
}

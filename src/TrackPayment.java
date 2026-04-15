import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class TrackPayment {
    
    private float[] memberBalances = new float[100];
    private  float[] memberPayment = new float[100];
    Scanner scanner = new Scanner(System.in);
    
    public void manageMemberShare(Group group)
    {
        
        group.totalExpenses = group.getTotalAmount();
        System.out.println("\n                              Total Expenses for " + group.groupName + ": Php " + group.totalExpenses);

        float fairShare = group.totalExpenses / group.number_members;

        memberBalances = new float[group.number_members];

        System.out.println("\n                              Enter member contributions:");
        for (int i = 0; i < group.number_members; i++) {
            System.out.print("                                Enter contribution for Member " + group.group_members[i] + ": ");
            memberPayment[i] = scanner.nextFloat();
        }
        
        for (int i = 0; i < group.number_members; i++) {
            memberBalances[i] = fairShare - memberPayment[i];
        }

        System.out.println("\n                              Outstanding Balances:");
        boolean hasOutstanding = false;

        for (int i = 0; i < group.number_members; i++) {
            for (int j = i + 1; j < group.number_members; j++) {
                if (memberBalances[i] > 0 && memberBalances[j] < 0) {
                    float amountOwed = Math.min(memberBalances[i], -memberBalances[j]);
                    System.out.println("                                - " + group.group_members[i] + " owes Php " + String.format("%.2f", amountOwed) + " to " + group.group_members[j]);

                    hasOutstanding = true;
                } else if (memberBalances[i] < 0 && memberBalances[j] > 0) {
                    float amountOwed = Math.min(-memberBalances[i], memberBalances[j]);
                    System.out.println("                                - " + group.group_members[j] + " owes Php " + String.format("%.2f", amountOwed) + " to " + group.group_members[i]);

                    hasOutstanding = true;
                }
            }
        }              

        if (!hasOutstanding) {
            System.out.println("                              All balances are settled.");
        }

        System.out.println("\n                              Member contributions recorded successfully.");
    }
    
    public void paymentRecord(Group group, int i){
        float amount;
        
        while(true)
        {
            System.out.println("\n                              Pay an amount under or equal to you balance: " + memberBalances[i]);
            System.out.print("                              Enter member payment: ");
            amount = scanner.nextFloat();
            
            if ( amount <= memberBalances[i]) break;
            else System.out.println("                              Amount is too much.");
        }
        
        memberPayment[i] += amount;
        memberBalances[i] -= amount;
        
        while (amount > 0) {
            boolean paymentDistributed = false;

            for (int j = 0; j < group.number_members; j++) {
                if (memberBalances[j] < 0 && amount > 0) {
                    float payment = Math.max(memberBalances[j], amount);
                    memberBalances[j] += payment; // Reducse the creditor's balance
                    amount -= payment; // Reduce the payment amount
                    paymentDistributed = true;

                    System.out.println("\n                               - Paid Php " + payment + " to " + group.group_members[j]);
                }
            }

            // If payment hasn't been distributed in this iteration, break the loop
            if (!paymentDistributed) {
                break;
            }
        }
    }
    
    public void viewSummary(Group group)
    {
        System.out.println("                              ===============================================");
        System.out.printf("                              | %-43s |\n", "SPLITMATE SUMMARY");
        System.out.println("                              ===============================================");
        System.out.printf("                              | %-30s | %-10s |\n", "Group Name", group.groupName);
        System.out.printf("                              | %-30s | %-10.2f |\n", "Total Expenses", group.totalExpenses);
        System.out.println("                              ===============================================");

        System.out.println("\n                              Group Expenses:");
        System.out.println("                              -----------------------------------------------");
        System.out.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Description", "Amount");
        System.out.println("                              -----------------------------------------------");
        for (int i = 0; i < group.no_expense; i++) {
            System.out.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.description[i], group.expenseAmount[i]);
        }
        System.out.println("                              -----------------------------------------------");

        System.out.println("\n                              Member Shares:");
        System.out.println("                              -----------------------------------------------");
        System.out.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Share");
        System.out.println("                              -----------------------------------------------");
        for (int i = 0; i < group.number_members; i++) {
            System.out.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], memberPayment[i]);
        }
        System.out.println("                              -----------------------------------------------");

        System.out.println("\n                              Outstanding Balance:");
        System.out.println("                              -----------------------------------------------");
        System.out.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Balance");
        System.out.println("                              -----------------------------------------------");
        for (int i = 0; i < group.number_members; i++) {
            if (memberBalances[i] > 0) {
                System.out.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], memberBalances[i]);
            } else {
                System.out.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], 0.00);
            }
        }
        System.out.println("                              -----------------------------------------------");

        System.out.println("\n                              Receivable Balance:");
        System.out.println("                              -----------------------------------------------");
        System.out.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Balance");
        System.out.println("                              -----------------------------------------------");
        for (int i = 0; i < group.number_members; i++) {
            if (memberBalances[i] < 0) {
                System.out.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], -memberBalances[i]);
            } else {
                System.out.printf("                              | %-4d | %-20s | %-13s |\n", (i + 1), group.group_members[i], "--");
            }
        }
        System.out.println("                              -----------------------------------------------");


    }

    public void SaveSummary(Group group)
    {
        String filePath = "output.txt";

        PrintStream outFile = null;

        try
        {
            outFile = new PrintStream(new FileOutputStream(filePath));


            outFile.println("\n");
            outFile.println("\n");
            outFile.println("\t                    _______  _______  ___      ___   _______  __   __  _______  _______  _______ ");
            outFile.println("\t                   |       ||       ||   |    |   | |       ||  |_|  ||   _   ||       ||       |");
            outFile.println("\t                   |  _____||    _  ||   |    |   | |_     _||       ||  |_|  ||_     _||    ___|");
            outFile.println("\t                   | |_____ |   |_| ||   |    |   |   |   |  |       ||       |  |   |  |   |___ ");
            outFile.println("\t                   |_____  ||    ___||   |___ |   |   |   |  |       ||       |  |   |  |    ___|");
            outFile.println("\t                    _____| ||   |    |       ||   |   |   |  |   _   ||   _   |  |   |  |   |___ ");
            outFile.println("\t                   |_______||___|    |_______||___|   |___|  |__| |__||__| |__|  |___|  |_______|");
            outFile.println("\n");

            outFile.println("                              ===============================================");
            outFile.printf("                              | %-43s |\n", "SPLITMATE SUMMARY");
            outFile.println("                              ===============================================");
            outFile.printf("                              | %-30s | %-10s |\n", "Group Name", group.groupName);
            outFile.printf("                              | %-30s | %-10.2f |\n", "Total Expenses", group.totalExpenses);
            outFile.println("                              ===============================================");

            outFile.println("\n                              Group Expenses:");
            outFile.println("                              -----------------------------------------------");
            outFile.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Description", "Amount");
            outFile.println("                              -----------------------------------------------");
            for (int i = 0; i < group.no_expense; i++) {
                outFile.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.description[i], group.expenseAmount[i]);
            }
            outFile.println("                              -----------------------------------------------");

            outFile.println("\n                              Member Shares:");
            outFile.println("                              -----------------------------------------------");
            outFile.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Share");
            outFile.println("                              -----------------------------------------------");
            for (int i = 0; i < group.number_members; i++) {
                outFile.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], memberPayment[i]);
            }
            outFile.println("                              -----------------------------------------------");

            outFile.println("\n                              Outstanding Balance:");
            outFile.println("                              -----------------------------------------------");
            outFile.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Balance");
            outFile.println("                              -----------------------------------------------");
            for (int i = 0; i < group.number_members; i++) {
                if (memberBalances[i] > 0) {
                    outFile.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], memberBalances[i]);
                } else {
                    outFile.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i], 0.00);
                }
            }
            outFile.println("                              -----------------------------------------------");

            outFile.println("\n                              Receivable Balance:");
            outFile.println("                              -----------------------------------------------");
            outFile.printf("                              | %-4s | %-20s | %-13s |\n", "No.", "Member", "Balance");
            outFile.println("                              -----------------------------------------------");
            for (int i = 0; i < group.number_members; i++) {
                if (memberBalances[i] < 0) {
                    outFile.printf("                              | %-4d | %-20s | %-13.2f |\n", (i + 1), group.group_members[i],  memberBalances[i]);
                } else {
                    outFile.printf("                              | %-4d | %-20s | %-13s |\n", (i + 1), group.group_members[i], "--");
                }
            }
            outFile.println("                              -----------------------------------------------");
            outFile.println("\n");


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        } finally
        {
            if (outFile != null) {
                outFile.flush();
            }
        }
    }

}
import java.util.Scanner;

public class SplitMate {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Utilities utility = new Utilities();
        final int min = 1;
        
        while (true){
            // PRINT WELCOME MESSAGE
            utility.clearAndDisplayHeader();
            System.out.println("                            Welcome to SplitMate! This application helps you manage expenses and payments ");
            System.out.println("                                  within groups, whether you're tracking household costs or splitting");
            System.out.println("                                           a bill with friends. Let's get started!");
            System.out.print("\n                                        Press ENTER to continue or 'Q' to quit... ");


            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Q")) {
                utility.clearAndDisplayHeader();
                System.err.println("\n                              Exiting SplitMate. Thank you!\n");
                return;
            }
            utility.clearAndDisplayHeader();
            System.out.println("                              Creating a new group...\n");
            GroupManager groupManager = new GroupManager();
            Group newGroup = new Group();

            newGroup.GroupName();

            System.out.print("\n                              Enter the number of members: ");
            int number_members = scanner.nextInt();
            scanner.nextLine();
            
            newGroup.addMember(number_members);
            System.out.println();

            newGroup.addExpense(); 
            System.out.println();

            groupManager.create_group(newGroup.groupName, number_members, newGroup.group_members, newGroup.no_expense, newGroup.description, newGroup.expenseAmount);
            System.out.print("                              Group created successfully!");
            utility.sleep();
            
            while(true){
                utility.clearAndDisplayHeader();
                utility.display_main_menu();
                int mainMax = 6;
                int choice = utility.get_user_input(min, mainMax);
                int groupChoice;

                TrackPayment trackpayment = new TrackPayment();

                switch(choice){
                    case 1: 
                        utility.clearAndDisplayHeader();
                        System.out.println("                              Creating a new group...\n");
                        
                        Group anotherNewGroup = new Group(); 

                        anotherNewGroup.GroupName();

                        System.out.print("\n                              Enter the number of members: ");
                        number_members = scanner.nextInt();
                        scanner.nextLine();

                        anotherNewGroup.addMember(number_members);
                        System.out.println();

                        anotherNewGroup.addExpense(); 
                        System.out.println();

                        groupManager.create_group(anotherNewGroup.groupName, number_members, anotherNewGroup.group_members, anotherNewGroup.no_expense, anotherNewGroup.description, anotherNewGroup.expenseAmount);
                        System.out.print("                              Group created successfully!");
                        utility.sleep();

                        break;
                    case 2:
                        utility.clearAndDisplayHeader();

                        System.out.print("\n                              Enter the name of the group you want to search: ");
                        String groupName = scanner.nextLine();
                        groupManager.searchGroup(groupName);
                        System.out.print("\n                              Press ENTER to continue...");
                        scanner.nextLine();

                        break;
                    case 3:
                        utility.clearAndDisplayHeader();

                        System.out.print("\n                              Select the group you want to edit:");
                        groupManager.list_groups();

                        int groupMax = groupManager.groupCount;
                        groupChoice = utility.get_user_input(min, groupMax);
                        Group groupToEdit = groupManager.groups[groupChoice - 1];
                        
                        utility.clearAndDisplayHeader();
                        utility.display_editgroup_menu();
                        int editMax = 5;
                        int editChoice = utility.get_user_input(min, editMax);

                        while (editChoice != 0) {

                            switch (editChoice) {
                                case 1: // Rename Group
                                    groupManager.renameGroup(groupChoice);
                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;

                                case 2: // Add Member
                                    System.out.print("\n                              Enter the number of new members: ");
                                    int additionalMembers = Integer.parseInt(scanner.nextLine());
                                    //scanner.nextLine(); // Consume newline

                                    groupToEdit.addMember(additionalMembers);
                                    System.out.println("\n                              Members added successfully.");

                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;

                                case 3: // Remove Member
                                    System.out.println("                              Members:");
                                    groupManager.list_members(groupChoice - 1);

                                    int memberMax = groupToEdit.number_members + 1;
                                    int memberChoice = utility.get_user_input(min, memberMax);
                                    groupManager.removeMember(groupChoice, memberChoice);

                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;

                                case 4: // Delete Group
                                    groupManager.delete_group(groupChoice);
                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;

                                case 5: // Back
                                    editChoice = 0;
                                    break;
                            }

                            if(editChoice != 0){
                                utility.clearAndDisplayHeader();
                                utility.display_editgroup_menu();
                                editChoice = utility.get_user_input(min, editMax);
                            }
                        }
                        break;  
                    
                    case 4: 
                        utility.clearAndDisplayHeader();
                        System.out.print("\n                              Select the group you want to track:");
                        groupManager.list_groups();

                        groupMax = groupManager.groupCount;
                        groupChoice = utility.get_user_input(1, groupMax);
                        Group groupToTrack = groupManager.groups[groupChoice - 1];

                        utility.clearAndDisplayHeader();
                        utility.display_trackpayment_menu();
                        int trackMax = 4;
                        int trackChoice = utility.get_user_input(1, trackMax);

                        while (trackChoice != 0)
                        {

                            switch (trackChoice)
                            {
                                case 1: // Manage Member Share
                                    utility.clearAndDisplayHeader();
                                    trackpayment.manageMemberShare(groupToTrack);
                                    System.out.println("\n                              What would you like to do next?");
                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;

                                case 2: // Payment Record
                                    while(true){
                                        utility.clearAndDisplayHeader();

                                        System.out.println("\n                              Select the member from the list:");
                                        groupManager.list_members(groupChoice - 1);
                                        
                                        int memberMax = groupToTrack.number_members + 1;
                                        System.out.println("                             " + memberMax + ". Back");

                                        int memberChoice = utility.get_user_input(min, memberMax);

                                        if (memberChoice == memberMax){
                                            break;
                                        }

                                        utility.clearAndDisplayHeader();
                                        trackpayment.paymentRecord(groupToTrack, memberChoice - 1);
                                        System.out.println("\n                              What would you like to do next?");
                                        System.out.print("\n                              Press ENTER to continue...");
                                        scanner.nextLine();
                                    }
                                    break;

                                case 3: // View Summary
                                    utility.clearAndDisplayHeader();
                                    trackpayment.viewSummary(groupToTrack);

                                    System.out.println("\n                              What would you like to do next?");
                                    System.out.print("\n                              Press ENTER to continue...");
                                    scanner.nextLine();
                                    break;



                                case 4: // Back
                                    trackChoice = 0;
                                    break;
                            }

                            if(trackChoice != 0)
                            {
                                utility.clearAndDisplayHeader();
                                utility.display_trackpayment_menu();
                                trackChoice = utility.get_user_input(1, trackMax);
                            }
                        }
                        break;
                        case 5: // save Summary
                            utility.clearAndDisplayHeader();
                            System.out.print("\n                              Select the group you want to save:");
                            groupManager.list_groups();


                            groupMax = groupManager.groupCount;
                            groupChoice = utility.get_user_input(1, groupMax);
                            groupToTrack = groupManager.groups[groupChoice - 1];
                            utility.clearAndDisplayHeader();
                            trackpayment.SaveSummary(groupToTrack);
                            System.out.println("\n                             Your summary is now save in 'output.txt");
                            System.out.println("\n                              What would you like to do next?");
                            System.out.print("\n                              Press ENTER to continue...");
                            scanner.nextLine();
                            break;
                    case 6:
                        utility.clearAndDisplayHeader();
                        System.err.println("\n                              Exiting SplitMate. Thank you!\n");
                        return;
                }
            }  
        }
    }
}

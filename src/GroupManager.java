import java.util.Scanner;

public class GroupManager {
    public Group[] groups = new Group[100];
    public int groupCount = 0;
    Scanner scanner = new Scanner(System.in);

    public void create_group(String name, int number_members, String[] members, int no_expense, String[] description, float[] expenseAmount) {
        Group newGroup = new Group();
        newGroup.groupName = name;
        newGroup.number_members = number_members;
    
        newGroup.group_members = new String[number_members];
        for (int i = 0; i < number_members; i++) {
            newGroup.group_members[i] = members[i];
        }
    
        newGroup.no_expense = no_expense;
        newGroup.description = new String[no_expense];
        newGroup.expenseAmount = new float[no_expense];
        for (int i = 0; i < no_expense; i++) {
            newGroup.description[i] = description[i];
            newGroup.expenseAmount[i] = expenseAmount[i];
        }
    
        groups[groupCount++] = newGroup;
    }    

    public void list_groups() {
        for (int i = 0; i < groupCount; i++) {
            System.out.print("\n                              " + (i + 1) + ". " + groups[i].groupName);
        }
        System.out.println();
    }
    
    public void list_members(int i) {
        for (int j = 0; j < groups[i].number_members; j++){
            System.out.print("\n                             " + (j + 1) + ". " + groups[i].group_members[j]);
        }
        System.out.println();
    }
    
    public void renameGroup(int i) {
        System.out.print("\n                              Enter a new name for the group: ");
        groups[i - 1].groupName = scanner.nextLine();
        System.out.println("\n                              Group renamed to: " + groups[i - 1].groupName);
    }
    
    public void removeMember(int i, int j) {
        Group group = groups[i - 1]; // Retrieve the group
        for (int a = j - 1; a < group.number_members - 1; a++) {
            group.group_members[a] = group.group_members[a + 1]; // Shift members left
        }
        group.group_members[group.number_members - 1] = null; // Clear the last slot
        group.number_members--; // Reduce the member count

        System.out.println("                              Member removed.");
    }

    
    public void delete_group(int i) {
        for (int j = i; i < groupCount; i++) {
            groups[j] = groups[j + 1];
        }
        groupCount--;
        System.out.println("                              Group deleted susccessfully.");
    }
    
    public void searchGroup (String name) {
        for (int i = 0; i < groupCount; i++)
        {
            if (groups[i].groupName.equals(name)) {
                System.out.println("                              Group Name: " + groups[i].groupName);
                
                System.out.println("\n                              Members: ");
                for(int j = 0; j < groups[i].number_members; j++){
                    System.out.print("\n                             " + (j + 1) + ". " + groups[i].group_members[j]);
                }
                
                System.out.println("\n                              Group Expenses: ");
                for(int a = 0; a < groups[i].no_expense; a++){
                    System.out.println("\n                             " + (a + 1) + ". " + groups[i].description[a] + ": " + groups[i].expenseAmount[a]);
                }
                
                return;
            }
        }
        System.out.println("\n                              Group " + name + " not found.");
    }
}
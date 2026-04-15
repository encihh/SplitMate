import java.util.Scanner;

public class Group {
    Scanner scanner = new Scanner(System.in);
    public String groupName;
    public String[] group_members = new String[100];
    public int number_members;
    public int no_expense;
    public float totalExpenses = 0;
    public String[] description = new String[100];
    public float[] expenseAmount = new float[100];
    public float totalAmount = 0;  

    public void GroupName() {
        System.out.print("                              Please input your group name: ");
        groupName = scanner.nextLine();
    }

    public void addMember(int new_members) {
        System.out.println("\n                              Please input your members:");
        for (int i = 0; i < new_members; i++) {
            if (number_members >= group_members.length) {
                group_members = resizeArray(group_members);
            }
            System.out.print("                                Member " + (number_members + 1) + ": ");
            group_members[number_members] = scanner.nextLine();
            number_members++;
        }
    }

    private String[] resizeArray(String[] originalArray) {
        int newSize = originalArray.length * 2;
        String[] newArray = new String[newSize];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
}


    public void addExpense() {
        System.out.print("                              Please input the number of expense: ");
        no_expense = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < no_expense; i++) {
            System.out.println("\n                              Expense: " + (i + 1));
            getExpense(i);
            totalAmount += expenseAmount[i]; 
        }
        totalExpenses = getTotalAmount();
        System.out.println("\n                              The total amount spent for " + groupName + " is: " + totalAmount);
        totalAmount = 0; 
    }

    public void getExpense(int i) {
        System.out.print("                                Enter the expense description: ");
        description[i] = scanner.nextLine();

        System.out.print("                                Enter the amount spent: ");
        expenseAmount[i] = scanner.nextFloat();
        scanner.nextLine();

        totalAmount += expenseAmount[i];
    }

    public float getTotalAmount() {
        totalAmount = 0;
        for (float expense : expenseAmount) {
            totalAmount += expense;
        }
        return totalAmount;
    }
}
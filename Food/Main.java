package Food;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu input dialog
            String roleInput = JOptionPane.showInputDialog(
                    null,
                    "What role are you?\n1. Customer\n2. Staff\n0. Exit",
                    "Role Selection",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Handle potential null input (if user clicks cancel)
            if (roleInput == null) {
                JOptionPane.showMessageDialog(null, "Thank you for using our service!");
                break;
            }

            // Validate input
            try {
                int role = Integer.parseInt(roleInput);

                // Handle role selection
                switch (role) {
                    case 0:
                        JOptionPane.showMessageDialog(null, "Thank you for using our service!");
                        return;
                    case 1:
                        Customer.handleCustomer(scanner);
                        break;
                    case 2:
                        Staff.handleStaff(scanner);
                        break;
                    default:
                        JOptionPane.showMessageDialog(
                                null,
                                "Invalid input. Please enter 0, 1, or 2.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                }
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid number (0, 1, or 2).",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        scanner.close();
    }

    // getValidInput method to validate input when method called
    public static int getValidInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                System.out.println("Please enter a number between " + min + " and " + max + ":");

                // Read the entire line
                String input = scanner.nextLine().trim();

                // Check if input is empty
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be blank. Please enter a valid number.");
                    continue;
                }

                // Parse the input
                int inputNumber = Integer.parseInt(input);

                // Check if the number is within the specified range
                if (inputNumber < min || inputNumber > max) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input. Please enter a number between " + min + " and " + max + ".");
                    continue;
                }

                return inputNumber;
            } catch (NumberFormatException e) {
                // Catch non-numeric input
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter a valid number between " + min + " and " + max + ".");
            }
        }
    }
}
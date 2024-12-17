package Food;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Staff {

    public static void handleStaff(Scanner scanner) {
        String name = getNonBlankInput("Enter Staff Name (letters only):", scanner, true);
        String staffId = "";
        while (true) {
            System.out.println("Enter your Staff ID (valid: STAFF11, STAFF12):");
            staffId = scanner.nextLine().trim();
            if (staffId.equals("STAFF11") || staffId.equals("STAFF12")) {
                break;
            } else {
                System.out.println(">>> Invalid Staff ID. Please try again. <<<");
            }
        }

        System.out.println("Staff ID validated. Welcome, " + name);

        System.out.println("Choose an option:");
        System.out.println("1. Place order for Customer");
        System.out.println("2. Cancel order for Customer");
        System.out.println("3. Exit");

        int option = Main.getValidInput(scanner, 1, 3);

        switch (option) {
            case 1:
                placeOrderForCustomer(scanner);
                break;
            case 2:
                cancelOrderForCustomer(scanner);
                break;
            case 3:
                System.out.println("Exiting program...");
                break;
        }
    }

    public static void placeOrderForCustomer(Scanner scanner) {
        String name = getNonBlankInput("Enter customer name (letters only):", scanner, true);
        String phone = getValidPhoneNumber(scanner);
        String address = getNonBlankInput("Enter customer address:", scanner, false);

        System.out.println("Customer details: Name: " + name + ", Phone: " + phone + ", Address: " + address);

        Order order = new Order();
        order.selectFood(scanner);
        order.calculateTotal();

        System.out.println("Choose how the customer wants to receive the order:");
        System.out.println("1. Pickup");
        System.out.println("2. Delivery");

        int deliveryOption = Main.getValidInput(scanner, 1, 2);

        if (deliveryOption == 1) {
            System.out.println("Customer chose to pick up the order.");
        } else {
            System.out.println("Delivery information will be sent to the phone number of the customer shortly after the order is placed.");
        }

        Payment.processPayment(scanner);
    }

    public static void cancelOrderForCustomer(Scanner scanner) {
        String name = getNonBlankInput("Enter customer name to cancel order (letters only):", scanner, true);
        String phone = getValidPhoneNumber(scanner);

        while (true) {
            try {
                System.out.println("Do you want to cancel this order?");
                System.out.println("1. Yes");
                System.out.println("2. No");

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Input cannot be blank. Please enter 1 or 2.");
                    continue;
                }

                int confirmation = Integer.parseInt(input);
                if (confirmation < 1 || confirmation > 2) {
                    JOptionPane.showMessageDialog(null,"Invalid input. Please enter 1 or 2.");
                    continue;
                }

                if (confirmation == 1) {
                    System.out.println("The order of customer " + name + " is cancelled successfully.");
                    System.out.println("The amount will be refunded to the original payment method within a few business days.");

                    // Log the cancellation to the file ONLY if staff confirms
                    logOrderCancellation(name, phone);
                } else {
                    System.out.println("Cancelling operation aborted.");
                    // No logging if staff chooses not to cancel
                }

                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Invalid input. Please enter 1 or 2.");
            }
        }
    }

    // Writes the order cancellation details to a file.
    public static void logOrderCancellation(String name, String phone) {
        try (FileWriter writer = new FileWriter("order_cancellations.txt", true)) {
            writer.write("Customer Name: " + name + "\n");
            writer.write("Phone: " + phone + "\n");
            writer.write("Status: CANCELLED\n");
            writer.write("---------------------------\n");
        } catch (IOException e) {
            System.out.println("Error while writing to file");
        }
    }

    public static String getNonBlankInput(String promptMessage, Scanner scanner, boolean onlyLetters) {
        String input = "";
        while (true) {
            System.out.println(promptMessage);
            input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Input cannot be blank. Please try again.");
            } else if (onlyLetters && !input.matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
                JOptionPane.showMessageDialog(null,"Input can only contain letters and single spaces. Please try again.");
            } else {
                break;
            }
        }
        return input.trim();
    }

    public static String getValidPhoneNumber(Scanner scanner) {
        String phone = "";
        while (true) {
            System.out.println("Enter customer phone (10 digits):");
            phone = scanner.nextLine().trim();
            if (phone.matches("\\d{10}")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null,"INVALID phone number. Please enter a 10-digit number.");
            }
        }
        return phone;
    }
}

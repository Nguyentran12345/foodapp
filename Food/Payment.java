package Food;

import java.util.Scanner;

public class Payment {
    public static void processPayment(Scanner scanner) {
        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit/Debit card");

        int paymentChoice = Main.getValidInput(scanner, 1, 2);

        if (paymentChoice == 1) {
            // Show a message when cash is chosen
            System.out.println("Cash payment is not accepted for online orders. Please choose another payment method.");
            processPayment(scanner);
        } else {
            System.out.println("Enter card details:");

            String cardNumber = "";
            while (true) {
                System.out.println("Enter your card number (15-16 digits):");
                cardNumber = scanner.nextLine().trim();

                if (cardNumber.isEmpty()) {
                    System.out.println("Card number cannot be blank. Please enter a valid card number.");
                    continue;
                }

                if (cardNumber.length() >= 15 && cardNumber.length() <= 16 && cardNumber.matches("[0-9]+")) {
                    break;
                } else {
                    System.out.println("INVALID card number. Please enter a 15-16 digit card number.");
                }
            }

            String cvv = "";
            while (true) {
                System.out.println("Enter your CVV (3-4 digits):");
                cvv = scanner.nextLine().trim();

                if (cvv.isEmpty()) {
                    System.out.println("CVV cannot be blank. Please enter a valid CVV.");
                    continue;
                }

                // Match exactly 3 or 4 digits
                if (cvv.matches("\\d{3,4}")) {
                    break;
                } else {
                    System.out.println("INVALID CVV. Please enter a 3-4 digit CVV.");
                }
            }

            System.out.println("Valid CVV entered: " + cvv);
            System.out.println("Payment successful. The card number ends with " + cardNumber.substring(cardNumber.length() - 4));
            System.out.println("Thank you for using the service.");
        }
    }
}

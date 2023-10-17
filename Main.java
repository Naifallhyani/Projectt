package uqu;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String password;

        System.out.println("Welcome to Our Cinema");
        System.out.println("Enter your username and password to login to your account.");

        System.out.println("Username: ");
        input.next();

        while (true) {
            System.out.println("Password: ");
            password = input.next();

            UserAccount userAccount = new UserAccount(password);

            try {
                if (UserAccount.login(userAccount)) {
                    System.out.println("You are logged in!");
                    break;
                }
            } catch (UserAccount.IncorrectPasswordException e) {
                System.out.println(e.getMessage());
                System.out.println("Would you like to try again? (y/n): ");
                String choice = input.next();
                if (!choice.equalsIgnoreCase("y")) {
                    System.out.println("Login failed.");
                    break;
                }
            }
        }
    }
}
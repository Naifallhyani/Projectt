package uqu;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String password;

        System.out.println("Welcome to Our Cinema");
        System.out.println("Enter your username and password to login to your account.");

        System.out.print("Username: ");
        input.next();

        while (true) {
            System.out.print("Password: ");
            password = input.next();

            UserAccount userAccount = new UserAccount(password);

            try {
                if (UserAccount.login(userAccount)) {
                    System.out.println("You are logged in!");

                    // Create a Customer object
                    System.out.print("Enter your name: ");
                    String name = input.next();
                    System.out.print("Enter your age: ");
                    int age = input.nextInt();
                    System.out.print("Enter your ID: ");
                    int id = input.nextInt();

                    Customer customer = new Customer(name, age, id);

                    // Save customer information to a file
                    saveCustomerToFile(customer);

                    // Define movie packages with prices
                    MoviePackage[] moviePackages = {
                        new MoviePackage("The Dark Knight", MovieType.ACTION, 12),
                        new MoviePackage("Barbie", MovieType.COMEDY, 12),
                        new MoviePackage("Oppenheimer", MovieType.DRAMA, 12),
                        new MoviePackage("Dragon Castle Adventure - Pororo Movie", MovieType.ANIMATION, 10.99)
                    };

                    // Display movie packages and prices
                    System.out.println("Movies:");
                    for (int i = 0; i < moviePackages.length; i++) {
                        System.out.println((i + 1) + ". " + moviePackages[i].getName() +
                                " (" + moviePackages[i].getType() + ") - $" + moviePackages[i].getPrice());
                    }
                    System.out.print("Enter the number of the movie: ");
                    int selectedPackage = input.nextInt();

                    if (selectedPackage >= 1 && selectedPackage <= moviePackages.length) {
                        MoviePackage chosenPackage = moviePackages[selectedPackage - 1];
                        int ageRating = getAgeRatingForMovieType(chosenPackage.getType());

                        if (age < ageRating) {
                            System.out.println("You are not old enough to watch this movie.");
                        } else {
                            System.out.print("Enter the number of tickets you want: ");
                            int numberOfTickets = input.nextInt();

                            // Get the total cost for the movie
                            double movieCost = chosenPackage.getPrice() * numberOfTickets;

                            // Ask if the user wants appetizers
                            System.out.print("Do you want appetizers like popcorn or cola? (y/n): ");
                            String choice = input.next();
                            double appetizerCost = 0.0;

                            if (choice.equalsIgnoreCase("y")) {
                                System.out.print("How many popcorns do you want? ($5 each): ");
                                int popcornCount = input.nextInt();
                                appetizerCost += 5 * popcornCount;

                                System.out.print("How many colas do you want? ($3 each): ");
                                int colaCount = input.nextInt();
                                appetizerCost += 3 * colaCount;
                            }

                            // Calculate the total cost including movie and appetizers
                            double totalCost = movieCost + appetizerCost;

                            System.out.println("You have reserved " + numberOfTickets + " tickets for " + chosenPackage.getName() + " for a total cost of $" + movieCost);
                            System.out.println("Your total cost, including movie and appetizers, is: $" + totalCost);

                            System.out.println("Please choose " + numberOfTickets + " seats from the following:");

                            for (int i = 1; i <= numberOfTickets; i++) {
                                int seatNumber = -1;
                                while (seatNumber < 1 || seatNumber > 10) {
                                    // Display the seat diagram
                                    System.out.println("Seats: " + generateSeatnumber());

                                    System.out.print("Select Seat " + i + " (1-10): ");
                                    seatNumber = input.nextInt();

                                    if (seatNumber < 1 || seatNumber > 10) {
                                        System.out.println("Invalid seat selection. Please choose a seat between 1 and 10.");
                                    }
                                }

                                System.out.println("You have chosen Seat " + seatNumber);
                            }
                        }
                    } else {
                        System.out.println("Invalid movie package selection.");
                    }

                    break;
                }
            } catch (UserAccount.IncorrectPasswordException e) {
                System.out.println(e.getMessage());
                System.out.print("Would you like to try again? (y/n): ");
                String choice = input.next();
                if (!choice.equalsIgnoreCase("y")) {
                    System.out.println("Login failed.");
                    break;
                }
             
            }
        }
     // At the end of the main method
        System.out.println("Thank you and enjoy the movie"); }

    private static String generateSeatnumber() {
        // Generate a simple diagram for 10 seats
        StringBuilder diagram = new StringBuilder("[");
        for (int i = 1; i <= 10; i++) {
            diagram.append(" ");
            if (i <= 9) {
                diagram.append(i);
            } else {
                diagram.append("10");
            }
            diagram.append(" ");
        }
        diagram.append("]");
        return diagram.toString();
    }

    private static int getAgeRatingForMovieType(MovieType movieType) {
        // You can define age ratings for different movie types here
        // For simplicity, let's assume age ratings for all types are 12
        return 12;
    }
    

    private static void saveCustomerToFile(Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customer.txt"))) {
            writer.println("Name: " + customer.getName());
            writer.println("Age: " + customer.getAge());
            writer.println("ID: " + customer.getId());
        } catch (IOException e) {
            e.printStackTrace();
     
        }
     

    }
 
}

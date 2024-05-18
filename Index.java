import java.util.ArrayList;
import java.util.Scanner;

public class Index {
    static Movie[] movies = {
            new Movie("Godzilla", "9:00 AM - 12:00 PM"),
            new Movie("Avenger", "12:00 PM - 3:00 PM"),
            new Movie("SpiderMan", "3:00 PM - 6:00 PM"),
            new Movie("Fast and the Furious", "6:00 PM - 9:00 PM")
    };
    // Initializing the scanner class in order to take input from the user
    static Scanner sc = new Scanner(System.in);

    static final double ADULT_TICKET_PRICE = 20.00;
    static final double CHILD_TICKET_PRICE = 10.00;

    static ArrayList<Movie> cart = new ArrayList<>();

    public static void main(String[] args) {

        // Displaying options to the user
        int option = 1;

        while (option == 1) {
            System.out.println("\n---------------------------------------");
            System.out.println("| Option\t\t| Description\t|");
            System.out.println("---------------------------------------");
            System.out.println("| 1\t\t\t| Show Movie Sessions\t|");
            System.out.println("| 2\t\t\t| Checkout\t|");
            System.out.println("---------------------------------------");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            if (option == 1) {
                showMovieSessions();
            }
            else if (option == 2) {
                checkout();
            }
             else {
                System.out.println("Invalid option. Please try again.");
                option = 1;
            }
        }
    }

    public static void showMovieSessions() {
        System.out.println("\n-----------------------------------------");
        System.out.println("| No. | Movie Name          1| Show Time  |");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < movies.length; i++) {
            System.out.printf("| %-3d | %-18s | %-10s |\n", i + 1, movies[i].name, movies[i].showTime);
        }
        System.out.println("-----------------------------------------");
        System.out.println("\n| Press any other button to go to the starting menu  |\n");
        System.out.print("Choose a movie: ");
        int movieIndex = sc.nextInt();

        if (movieIndex < 1 || movieIndex > 4) {
            return;
        }

        System.out
                .println("You have selected " + movies[movieIndex - 1].name + " at " + movies[movieIndex - 1].showTime);
        Movie chosenMovie = movies[movieIndex - 1];

        if (!cart.contains(chosenMovie)) {
            cart.add(chosenMovie);
        }

        System.out.println("\n--------------------------------");
        System.out.println("| Category | Price    |");
        System.out.println("--------------------------------");
        System.out.printf("| %-9s | $%-7.2f |\n", "Adult", 20.00);
        System.out.printf("| %-9s | $%-7.2f |\n", "Child", 10.00);
        System.out.println("--------------------------------");

        int adultTickets = 0;
        while (adultTickets <= 0) {
            System.out.print("How many adult tickets do you want to buy? ");
            adultTickets = sc.nextInt();
            if (adultTickets < 0) {
                System.out.println("Invalid number of adult tickets. Please try again.");
                adultTickets = 0;
            } else {
                chosenMovie.adultTickets += adultTickets;
            }
        }

        int childTickets = 0;
        while (childTickets <= 0) {
            System.out.print("How many child tickets do you want to buy? ");
            childTickets = sc.nextInt();
            if (childTickets < 0) {
                System.out.println("Invalid number of children tickets. Please try again.");
                childTickets = 0;
            } else {
                chosenMovie.childTickets += childTickets;
            }
        }
    }

    public static void checkout() {
        System.out.println("\nCheckout Summary:");
        double totalPrice = 0;

        System.out.println("Booking Time: " + java.time.LocalDateTime.now());
        
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| Movie                | Show Time  | Adult Tickets | Child Tickets | Subtotal  |");
        System.out.println("---------------------------------------------------------------------------------");

        for (Movie movie : cart) {
            if (movie.adultTickets > 0 || movie.childTickets > 0) {
                double movieTotal = (movie.adultTickets * ADULT_TICKET_PRICE) + (movie.childTickets * CHILD_TICKET_PRICE);
                totalPrice += movieTotal;

                System.out.printf("| %-20s | %-10s | %-13d | %-13d | $%-8.2f |\n", 
                                  movie.name, movie.showTime, movie.adultTickets, movie.childTickets, movieTotal);
            }
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-61s | $%-8.2f |\n", "Total Price", totalPrice);
        System.out.println("---------------------------------------------------------------------------------");
    }
}
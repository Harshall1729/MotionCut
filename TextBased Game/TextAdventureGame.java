import java.util.Scanner;

public class TextAdventureGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("You find yourself in a dark forest.");

        // Storyline
        System.out.println("As you walk deeper into the forest, you encounter a fork in the path.");
        System.out.println("Do you take the left path or the right path? (left/right)");

        // User input
        String choice = scanner.nextLine();

        // Decision point
        if (choice.equalsIgnoreCase("left")) {
            System.out.println("You chose the left path.");
            System.out.println("You stumble upon a hidden cave.");
            System.out.println("Do you enter the cave? (yes/no)");

            // User input
            String enterCave = scanner.nextLine();

            // Outcome based on user choice
            if (enterCave.equalsIgnoreCase("yes")) {
                System.out.println("You enter the cave and find a treasure chest!");
                System.out.println("Congratulations, you win!");
            } else {
                System.out.println("You decide not to enter the cave and continue your journey.");
                System.out.println("Suddenly, you encounter a wild bear!");
                System.out.println("You are attacked and lose the game.");
            }
        } else if (choice.equalsIgnoreCase("right")) {
            System.out.println("You chose the right path.");
            System.out.println("You come across a river blocking your path.");
            System.out.println("Do you swim across the river or look for a bridge? (swim/bridge)");

            // User input
            String crossingOption = scanner.nextLine();

            // Outcome based on user choice
            if (crossingOption.equalsIgnoreCase("swim")) {
                System.out.println("You attempt to swim across the river.");
                System.out.println("Unfortunately, the current is too strong, and you drown.");
                System.out.println("Game over!");
            } else if (crossingOption.equalsIgnoreCase("bridge")) {
                System.out.println("You decide to look for a bridge.");
                System.out.println("After some searching, you find a sturdy bridge and safely cross the river.");
                System.out.println("You continue your journey deeper into the forest.");
                System.out.println("You hear strange noises in the distance...");
                System.out.println("To be continued...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        scanner.close();
    }
}

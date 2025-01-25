// Allows to read computers local time
import java.time.LocalTime;
// Can handle user input
import java.util.Scanner;

public class Guess
{
    public static void main(String[] args)
    {
        //Allows to read user inputs from terminal
        Scanner scanner = new Scanner(System.in);

        // Checks for previous save files
        FileHandler.loadScores();
        
        //turns the whole program keep running until the player inputs no at the end. 
        while (true) 
        {
            System.out.println("Welcome to the Guessing Game!");
            System.out.print("Would you like to play? (Yes/No) : ");
            
            // Important to at toLowerCase to get rid of case sensitivity
            String endGame = scanner.next().toLowerCase();

            // Ends the Game by breaking the loop.
            if (endGame.equals("no") || endGame.equals("n")) 
            {
                System.out.println("Goodbye!");
                break;   
            }

            // Checks to see if the player wants to clear the scores
            else if (endGame.equals("clear") || endGame.equals("cl"))
            {
                System.out.println("Are you sure you want to clear the scores?");
                String clearGame = scanner.next().toLowerCase();
                
                if (clearGame.equals("yes") || clearGame.equals("y"))
                {
                    FileHandler.clearScores();
                    System.out.println("Scores have been cleared.");
                    System.out.println(" ");
                    continue;
                }
            }

            int difficulty = start(scanner);

            while (true)
            {
                // It's important to check for a valid using '||' and not using commas
                if (difficulty == 1 || difficulty == 2 || difficulty == 3)
                {
                    break;
                }
                // Repeats the process until the user enters get's it right
                else
                {
                    System.out.println("That's not a difficulty. Try again.");
                }
            }
            
            // Calls local time and stores it to be used later
            LocalTime currentTime = LocalTime.now();

            int hour = currentTime.getHour();
            // System.out.println(hour);
            int minute = currentTime.getMinute();
            // System.out.println(minute);
            int second = currentTime.getSecond();
            // System.out.println(second);

            // We have a number for the user to guess
            int theNumber = checkDifficulty(difficulty, hour, minute, second);

            //This is just to test a benchmark. You need to comment this to make the guessing game work. 
            // System.out.println(theNumber);

            // The acutall code for the Guessing Game
            highLow(scanner, theNumber, difficulty);
        }
        scanner.close();
    }

    // Starts by welcoming the user for an input
    public static int start(Scanner scanner)
    {
        System.out.println(" ");
        System.out.println("Please choose a difficulty.");
        System.out.println("1 = Easy, 2 = Normal, 3 = Hard");
        System.out.print("What difficulty do you want? ");
        // byte difficulty = scanner.nextByte();
        return scanner.nextInt();
    }

    // Dependent on the difficulty, sends the time to the correct function. 
    public static int checkDifficulty(int difficulty, int hour, int minute, int second)
    {
        if (difficulty == 1)
        {
            return easyNumber(hour, minute, second);
        }
        else if (difficulty == 2)
        {
            return normalNumber(hour, minute, second);
        }
        else if (difficulty == 3)
        {
            return hardNumber(hour, minute, second);
        }
        else 
        {
            System.out.println("How did you get here? You found a bug...");
            return -1; // This will break the loop and prevent the program from crashing
        }
    }

    //Easy way to make a random number that will be relatively small
    public static int easyNumber(int hour, int minute, int second)
    {
        return (hour + minute + second) / 3;
    }
    
    //Generate a random number that will be a bit more challenging to guess
    public static int normalNumber(int hour, int minute, int second)
    {
        return (hour + minute + second);
    }

    //Generate a random number that will be a triple digit number
    public static int hardNumber(int hour, int minute, int second)
    {
        return (hour + minute + second) * 2;
    }

    // The game! Checks how many chances you get to guess and if you win or lose. 
    public static boolean highLow(Scanner scanner, int theNumber, int difficulty) 
    {
        int guess;
        int chances = 0;

        // Chances if statemens
        if (difficulty == 1)
        {
            chances = 10;
        }
        else if (difficulty == 2)
        {
            chances = 8;
        }
        else if (difficulty == 3)
        {
            chances = 5;
        }
        
        // A loop to check the number with your answer. 
        for (int i = 1; i <= chances; i++)
        {
            System.out.print("Attempt " + i + "/" + chances);
            System.out.print(" - Guess the number: ");
            guess = scanner.nextInt();

            if (guess > theNumber)
            {
                System.out.println("Your guess is too big. It's a smaller number.");
            }
            else if (guess < theNumber)
            {
                System.out.println("Your guess is too small. It's a larger number");
            }
            else 
            {
                System.out.println("Correct! It was " + theNumber + " You got it in " + i + " tries");
                FileHandler.savedScores(difficulty, i, true);
                return true;
            }
        }
        // If you didn't guess the number quick enough, then this tells you the number.
        System.out.println("You didn't guess the number. The number was " + theNumber);
        FileHandler.savedScores(difficulty, chances, false);
        return false;
    }    

}
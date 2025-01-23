import java.time.LocalTime;
import java.util.Scanner;


public class Guess
{
    public static void main(String[] args)
    {
        //Allows to read user inputs from terminal
        Scanner scanner = new Scanner(System.in);

        int difficulty = start(scanner);

        while (true)
        {
            difficulty = start(scanner);
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
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();

        // We have a number for the user to guess
        int theNumber = checkDifficulty(difficulty, hour, minute, second);

        //This is just to test a benchmark. You need to delete this and make the guessing game. 
        System.out.println(theNumber);

        scanner.close();
    }

    public static int start(Scanner scanner)
    {
        System.out.println("Welcome to the Guessing Game! Please choose a difficulty.");
        System.out.println("1 = Easy, 2 = Normal, 3 = Hard");
        System.out.print("What difficulty do you want? ");
        // byte difficulty = scanner.nextByte();
        return scanner.nextInt();
    }

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

    //Easy way to make a random number that will be in the double digits
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
}

// public class Main 
// {
//     public static void main(String[] args) 
//     {
//         System.out.println("Hello, World!");
//     }
// }
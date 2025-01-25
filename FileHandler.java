import java.io.*;

public class FileHandler
{
    private static final String FILE_NAME = "game_saves.txt";

    public static void savedScores(int difficulty, int chances, boolean won)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true)))
        {
            String result = won ? "Win" : "Lose";
            writer.write("Difficulty: " + difficulty + ", Attempts: " + chances + ", Result: " + result);
            writer.newLine();
        }
        
        catch (IOException e)
        {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void loadScores()
    {
        File file = new File(FILE_NAME);

        if (!file.exists())
        {
            System.out.println("You're new to the game! Lets get started!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while ((line = reader.readLine())!= null)
            {
                System.out.println(line);
            }
        }

        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }    

    public static void clearScores() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) 
        {
            System.out.println("Game scores have been cleared.");
        } 

        catch (IOException e) 
        {
            System.out.println("Error clearing file: " + e.getMessage());
        }
    }
}
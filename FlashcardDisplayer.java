import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FlashcardDisplayer
{
  ArrayList<Flashcard> cardList = new ArrayList<Flashcard>(); //a list of the Flashcards created
  FlashcardPQ cardQueue = new FlashcardPQ(); //a priority queue of Flashcards
  String fileName; //the name of the file with the Flashcards and that you're going to save data into

  /**
 * Creates a FlashcardDisplayer with the Flashcards in file. 
 * File has one Flashcard per line. Each line is formatted as:
 * priority,front,back
 @param String file - this is the name of the file you read in the card information from
 */
  public FlashcardDisplayer(String file)
  {
    fileName = file;
    File fileObject = new File(fileName);
    String line;
    Scanner keyboard = null;
    try 
    {
      keyboard = new Scanner(fileObject);
      while (keyboard.hasNextLine()) 
      {
        line = keyboard.nextLine();
        String[] lineSplitted = line.split(",");
        int priority = Integer.parseInt(lineSplitted[0]);
        cardList.add(new Flashcard(priority, lineSplitted[1], lineSplitted[2]));
      }  
    }
    catch (FileNotFoundException ex)
    {
      System.err.println(ex);
      System.exit(1);
    }
    keyboard.close();
    for (Flashcard x : cardList)
    {
      cardQueue.add(x);
    }
  }

/**
 * Writes out all Flashcards to the same file
 * they were read from so that they can be loaded by
 * the FlashcardDisplayer(String file) constructor. 
 * The FlashcardDisplayer should still have 
 * all the same Flashcards after this method is called 
 * as it did before the method was called.
 */
  public void saveFlashcards()
  {
    try 
    {
      FileWriter myWriter = new FileWriter(fileName);
      for (Flashcard x: cardQueue.getCardList())
      {
        myWriter.write(x.toString());
      }
      myWriter.close();
    } 
    catch (IOException e) 
    {
      System.err.println(e);
      System.exit(1);
    }
  }

 /**
  * Continuously displays Flashcards to the user
  * and checks their answers, updating the priority
  * of each Flashcard based on if the user answered 
  * correctly or not. Displays the highest priority
  * Flashcard first. Ends when the user enters 'save'.
  */
  public void displayFlashcards()
  {
    System.out.println("Welcome! This program will display your flashcards to you, check if you entered the correct answer and then ask you save or next? Saving will update the information in the file you provided. Here is your first card.");
    Scanner keyboard = new Scanner(System.in);
    String input = "next";
    while (input.equals("next"))
    {
      Flashcard topCard = cardQueue.peek();
      System.out.println("Card: ");
      System.out.println(topCard.getFrontText());
      System.out.println("Your answer: ");
      String answer = keyboard.nextLine();
      if (answer.equals(topCard.getBackText()))
      {
        System.out.println("Correct!");
        topCard.setPriority((topCard.getPriority() - 1));
        cardQueue.siftdown(0);     
      }
      else
      {
        System.out.println("Not quite!");
        topCard.setPriority((topCard.getPriority() + 2));
        System.out.println("The correct answer is: " + topCard.getBackText());
      }
      System.out.println("Save or next?");
      input = keyboard.nextLine();
    }
    if (input.equals("save"))
    {
      saveFlashcards();
    }
  }

  //the main method of the class. Creates a FlashcardDisplayer object and then runs the method displayFlashcards().
  public static void main (String[] args)
  {
    FlashcardDisplayer cardDisplayer = new FlashcardDisplayer(args[0]);
    cardDisplayer.displayFlashcards();
  }
}
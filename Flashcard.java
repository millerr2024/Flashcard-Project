import javax.lang.model.util.ElementScanner6;

/* This class is mainly used in terms of instances of itself. Stores front and back text as well as the card's priority.
It also stores its children cards
*/
public class Flashcard implements Comparable<Flashcard> 
{
  Integer priority; //this is the card's priority
  String front; //the front of the card
  String back; //the back of the card
  Flashcard rightChild; //the right Flashcard child
  Flashcard leftChild; //the left Flashcard child

  /**
  * Creates a new Flashcard with the given
  * priority level, text for the front of the card (front),
  * and text for the back of the card (back).
  @param Integer priorityInput - this is what the card's priority is set to
  @param String frontInput - this is what the card's front text is set to 
  @param String backInput - this is what the card's back text is set to
  */
  public Flashcard(Integer priorityInput, String frontInput, String backInput)
  {
    priority = priorityInput;
    front = frontInput;
    back = backInput;
  }

  /**
  * Gets the text for the front of this Flashcard.
  @return the text for the front of this Flashcard
  */
  public String getFrontText()
  {
    return front;
  }
  
  /**
  Gets the back of this Flashcard
  * @return String - returns the text for the Back of this Flashcard.
  */
  public String getBackText()
  {
    return back;
  }
  
  /**
  Gets the priority level of this Flashcard
  * @return Integer the priority level of this Flashcard.
  */
  public Integer getPriority()
  {
    return priority;
  }

  /**
  * Compares the priority levels of the Flashcard that called the method and another Flashcard
  @return int - returns 1 if the calling card if greater than the other Flashcard, 0 if they're equal, and -1 if the other Flashcard is bigger.
  @param Flashcard otherCard - the other Flashcard you're comparing the calling card to
  */
  public int compareTo(Flashcard otherCard)
  {
    return this.priority.compareTo(otherCard.priority);
  }

  /**
  * Returns a string in the format "priority,front,back\n"
  @return String
  */
  public String toString()
  {
    return "" + priority + "," + front + "," + back + "\n";
  }

  /**
  * Updates the priority of the card but doesn't allow priority to become negative.
  @param Integer newPriority - this is what you set the priority to (while still not allowing the priority to become negative)
  */
  public void setPriority(Integer newPriority)
  {
    if (newPriority >= 0)
    {
      priority = newPriority;
    }
  }

  /* Assigns a child to the calling Flashcard. 
  @param Flashcard child - this is the child added
  */
  public void addChild(Flashcard child)
  {
    if (leftChild == null)
    {
      leftChild = child;
    }
    else
    {
      rightChild = child;
    }
  }

  //removes one of the the calling flascard's children
  public void removechild()
  {
    if (rightChild != null)
    {
      rightChild = null;
    }
    else if (leftChild != null)
    {
      leftChild = null;
    }
  }

  /* Swaps the values of two Flashcards, the calling Flashcard and the one passed as a parameter
  @param Flashcard card - this is the Flashcard whose values are swapped with the calling card's
  */
  public void swap(Flashcard card)
  {
    Integer tempCardPriority = card.priority;
    String tempCardFront = card.front;
    String tempCardBack = card.back;
    card.priority = this.priority;
    card.front = this.front;
    card.back = this.back;
    this.priority = tempCardPriority;
    this.front = tempCardFront;
    this.back = tempCardBack;
  }

}

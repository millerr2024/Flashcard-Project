import java.util.ArrayList;
import java.util.NoSuchElementException;
public class FlashcardPQ implements PriorityQueue<Flashcard>
{
  int numOfThingsInHeap; //this is the number of Flashcards in the heap
  ArrayList<Flashcard> cardList = new ArrayList<Flashcard>(); //this is the list of all the Flashcards
  
  /** Adds the given item to the queue. 
  @param Flashcard item - this is the item you wish to add to the queue
  */
    public void add(Flashcard item)
    {
      cardList.add(item);
      numOfThingsInHeap++;
      while (cardList.indexOf(item) != 0 && item.compareTo(cardList.get((cardList.indexOf(item) -1) / 2)) == 1)
      {
        int newPositionOfItem = (cardList.indexOf(item) -1) / 2;
        item.swap(cardList.get((cardList.indexOf(item) -1) / 2));
        item = cardList.get(newPositionOfItem);
      }
      if (cardList.indexOf(item) != 0)
      {
        cardList.get(((cardList.indexOf(item) -1) / 2)).addChild(item);
      }
    }
    
    /** Removes the first item according to compareTo from the queue, and returns it.
     * Throws a NoSuchElementException if the queue is empty.
     @return Flashcard - this is the first Flashcard item
     */
    public Flashcard poll()
    {
      if (isEmpty())
      {
        throw new NoSuchElementException();
      }
      cardList.get(0).swap(cardList.get(cardList.size() -1));
      Flashcard toReturn = cardList.get(cardList.size() - 1);
      ArrayList<Flashcard> tempList = new ArrayList<Flashcard>();
      for (int i = 0; i < (cardList.size() - 1); i++)
      {
        tempList.add(cardList.get(i));
      }
      cardList = tempList;
      numOfThingsInHeap--;
      siftdown(0);
      return toReturn;
    }

    /* Moves a value to the correct spot in the array. This code is adapted code from OpenDSA's Heaps and Priority Queues
    @param int position - the position of the object you wish to move
    */
    public void siftdown(int position)
    {
      if ((position < 0) || (position >= numOfThingsInHeap)) return; // Illegal position
      while (!isLeaf(position)) {
        int childPosition = leftchild(position);
        if ((childPosition < (numOfThingsInHeap-1)) && (cardList.get(childPosition).compareTo(cardList.get(childPosition + 1)) < 0))
        {
          childPosition++; //leftChildPosition now points to the bigger child
        }
        if (cardList.get(position).compareTo(cardList.get(childPosition)) >= 0) 
        {
          return;
        }
        cardList.get(position).swap(cardList.get(childPosition));
        position = childPosition;  // Move down
      }
    }

    /* This method returns the location of the left child of the value at the position passed as a parameter. 
    This code is adapted code from OpenDSA's Heaps and Priority Queues.
    @param int position - this is the position of the value which you want to get the left child of
    @return int - returns an int that represents the location of the left child
    */
    public int leftchild(int position) {
      if (position >= numOfThingsInHeap/2)
      {
        return -1;
      }
      else
      {
        return 2*position + 1;
      }  
    }  
    
    /** Returns the first item according to compareTo in the queue, without removing it.
     * Throws a NoSuchElementException if the queue is empty.
     @return Flashcard - this is the card at the top of the queue
     */
    public Flashcard peek()
    {
      return cardList.get(0);
    }
    
    /** Returns true if the queue is empty. 
    @return boolean- true if the queue is empty, false if it isn't
    */
    public boolean isEmpty()
    {
      if (cardList.size() == 0)
      {
        return true;
      }
      return false;
    }
    
    /** Removes all items from the queue. */
    public void clear()
    {
      cardList.clear();
    }

    /* Checks if the value at the position passed as a parameter is a leaf (has no children).
    @param int position - this is the Flashcard you want to check and see if it's a lead
    @return boolean - returns true if it is a leaf, false if it's not a leaf
    */
    public boolean isLeaf(int position)
    {
      return (position >= numOfThingsInHeap/2) && (position < numOfThingsInHeap);
    }

    /* Returns this method's private instance variable cardList which contains a list of all the Flashcard objects in the queue
    @return ArrayList<Flashcard> - the list of all the Flashcard objects in the queue
    */
    public ArrayList<Flashcard> getCardList()
    {
      return cardList;
    }

    /* This is a function that creates some Flashcard objects and tests some of the 
    methods in the class, including add, peek, isEmpty, poll, and clear.
    */
    public static void test()
    {
        FlashcardPQ flashCardPriorityQueue = new FlashcardPQ();
        Flashcard cardOne = new Flashcard(1, "ni hao", "hello");
        Flashcard cardTwo = new Flashcard(4, "zai jian", "goodbye");
        Flashcard cardThree = new Flashcard(3, "ni hao ma?", "how are you?");
        Flashcard cardFour = new Flashcard(5, "wo hen hao", "I'm good");
        Flashcard cardFive = new Flashcard(1, "wo mama huhu", "I'm ok");
        flashCardPriorityQueue.add(cardOne);
        flashCardPriorityQueue.add(cardFive);
        flashCardPriorityQueue.add(cardThree);
        flashCardPriorityQueue.add(cardFour);
        flashCardPriorityQueue.add(cardTwo);
        System.out.println("The top card is: " + flashCardPriorityQueue.peek());
        System.out.println("Is the queue empty? " + flashCardPriorityQueue.isEmpty());
        System.out.println("Now we're removing the card: "+ flashCardPriorityQueue.poll());
        System.out.println("Now we're removing the card: "+ flashCardPriorityQueue.poll());
        System.out.println("The new top card is " + flashCardPriorityQueue.peek());
        flashCardPriorityQueue.clear();
        System.out.println("I've now cleared the array. Is it empty: " + flashCardPriorityQueue.isEmpty());
    }

    /* This is the class's main method. It runs the test() function.
    */
    public static void main (String[] args)
    {
      test();
    }

}
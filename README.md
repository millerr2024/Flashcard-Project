# Flashcard-Project
A Java project that uses a max-heap to sort flashcards by how frequently they are answered incorrectly. Created by Rachel "Lysander" Miller in fall of 2020 for the Carleton College course Introduction to Data Structures.

---------------------------------------------------

To run the program, first compile with 

javac* .java

Then, run the program with 

java FlashcardDisplayer *your txt file here*

-----------------------------------------------------

When the program is run, it will read in data from the txt file passed as a command line argument. This allows the user to enter any sort of flashcard content they want to be quizzed on. The proper ordering of input is shown in SampleFlashcards.txt:

7,Ottawa,Canada
Priority, front of flashcard, back of flashcard

The program creates an array of flashcards based on the provided input. The user is then quizzed on each flashcard. When the user answers correctly, the card's priority decreases. If the user answers incorrectly, the card's priority increases. Cards with a higher priority are shown more often, as they contain material the user needs to study more. This priority queue is implemented through a max-heap. 

When the user wants to quit the program, they can save their data. This will write the priorities of each card to the file the user previously passed as a command line argument. Thus, if the user wants to quit, they can save their data until their return.

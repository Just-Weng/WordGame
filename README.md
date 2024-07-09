# Word Game

# About
Word Game is a Project that I completed in CSCI 212 - Object Oriented Programming in Java

This project is loosely based on a word puzzle called the Spelling Beehive found in the Sunday New
York Times magazine. In it, a player is given a set of seven letters and has to find as many words as
possible using some portion, but at least five, of those seven letters. Letters may be used more than
once. Each correct word earns one point.

This Project utilizes Object Oriented Programming (OOP) and the javax.swing library. It also
uses a custom built Singly Linked List Data Structure to store solutions, and to store correctly guessed words in alphabetic order.

# How To Play
*  Choose a text file containing all words that a user can choose from
*  Once a file has been selected, two panels will appear
      *  The left panel stores the letters that you are allowed to make guesses with
      *  The right panel stores all correctly guessed words, as well as the score once you have made your first guess.
*  Input word guesses in the input field (minimum 5 letters long, and must be entirely lowercase)
*  Each guess awards 1 point, if all letters in the left panel are used in the guess, you are awarded 3 points
*  The game will end when all words have been guessed, or when user inputs "Stop"
*  After the game ends, you will be able to see how many words that you have guessed out of all the solutions. You will then be prompted to replay the game by entering "Yes" or "No"
*  If you decide you want to replay the game after replying "No", you have the ability to replay by clicking "Open" on the file menu

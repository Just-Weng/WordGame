import javax.swing.*;
import java.awt.*;
/**
 * Class PuzzleGUI contains the code that initializes a GUI for the game
 * with while loop for the logic of the game
 * 
 * NOTE #1: the only way to access the FileMenu in the game is to enter NO when asked to play game again, then you can start
 * a new game through the FileMenu(Open) or close the program(Quit)
 * 
 * NOTE #2: Could not find data file with illegal words provided, so play around with capitalization in input file
 * to test the exception handling
 * 
 * NOTE #3: Program can only handle files with 22 lines (1st line letters, rest wordBank) because still using arrays for value
 */
public class PuzzleGUI extends JFrame {
    TextArea left = new TextArea();
    TextArea right = new TextArea();

    /**
     * no-argument constructor that initializes PuzzleGUI object
     */
    public PuzzleGUI() {
        game();
    }
    
    /**
     * Improvement #2: Method creates FileMenu and adds it to the GUI
     * @return the Absolute File Path that is read by the JFileChooser
     */
    private String createFileMenu() {
        JMenuItem item;
        JMenuBar menuBar  = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        FileMenuHandler fmh  = new FileMenuHandler(this);
        item = new JMenuItem("Open");
        item.addActionListener( fmh );
        fileMenu.add( item );
        
        fileMenu.addSeparator();
      
        item = new JMenuItem("Quit");
        item.addActionListener( fmh );
        fileMenu.add( item );
        
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        
        setJMenuBar(menuBar);
        
        return fmh.readAbsolutePath();
     }

    // Change: Moved the constructor into a method game() so that it can be recalled if the user wishes to play again/selects a text file through FileMenu -> Open
    public void game() {
        try {
         // Setting up GUI
            setTitle("Spelling Beehive");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 2));
            
            String path = createFileMenu();
            TextFileInput in = null;
            in = new TextFileInput(path);

            // Fills in String with allowed Letters
            String line = in.readLine();
            String letters = line;

            String[] wordBank = new String[21];
            
            // Fills in Solutions wordBank
            for(int i = 0; i < wordBank.length; i++) {
                wordBank[i] = in.readLine();
            }
            
            UnsortedWordList wordListBank = new UnsortedWordList();
            
            // Converts wordBank to UnsortedWordList by adding it
            // Improvement #3: IllegalWordException try/catch
            for(int i = 0; i < wordBank.length; i++) {
                wordListBank.add(new Word(wordBank[i]));
            }

            // left TextFrame
            add(left);
            left.setEditable(false);
            left.append("Puzzle Letters:\n");
            for(int i = 0; i < letters.length(); i++) {
                left.append(letters.charAt(i) + "\n");
            }
            // Right TextFrame
            add(right);
            right.setEditable(false);
            right.append("Words Found:\n");
            setVisible(true);
            pack();
            
            // Condition for While Loop, score count
            int score = 0;
            SortedWordList foundWords = new SortedWordList();
            
            // Infinite While Loop for ongoing game, stop condition is "STOP", or until all words are found
            while(foundWords.length < wordListBank.length) {
                String input = JOptionPane.showInputDialog(null, "Please enter a Word Combination comprised of letters from the letter bank(Min. 5 letters). STOP to end game");
                
                // Is the input a new solution? Is the input only from the letters provided? Does the input already exist as a solution?
                boolean solution = false, usedLetter = false, exists = false;

                // gets nodes from the Unsorted/Sorted WordLists
                WordNode unsortedNode = wordListBank.first.next;
                WordNode sortedNode = foundWords.first.next;
                
                if(input == null) {
                    continue;
                }else if (input.equalsIgnoreCase("STOP")) {
                    break;
                }else if(input.length() < 5) {
                    JOptionPane.showMessageDialog(null, "Word length is too short, please enter a word that is at least 5 letters long");
                    continue;
                }else {
                    for (int i = 0; i < input.length(); i++) {
                        usedLetter = false;
                        for (int j = 0; j < letters.length(); j++) {
                            if (input.toLowerCase().charAt(i) == letters.charAt(j)) {
                                usedLetter = true;
                                continue;
                            }
                        }
                        if(!usedLetter) {
                            break;
                        }
                    }
                }
                
                // Word already found?
                while (sortedNode != null) {
                    if (input.equalsIgnoreCase(sortedNode.data.getWord())) {
                        JOptionPane.showMessageDialog(null, "Word has already been found");
                        solution = true;
                        exists = true;
                        break;
                    }
                    sortedNode = sortedNode.next;
                }
                
                // Back to Re-prompt if solution exists
                if(exists)
                    continue;
                
                // Is first letter of letters (l) contained in foundWords?
                boolean containsL = false;
                for(int i = 0; i < input.length(); i++) {
                    if(letters.charAt(0) == input.charAt(i)) {
                        containsL = true;
                        break;
                    }
                }
                
                // Does input use all of the letters contained in the String letters?
                boolean containsAllLetters = true;
                for (int i = 0; i < letters.length(); i++) {
                    if (input.indexOf(letters.charAt(i)) == -1) {
                        containsAllLetters = false;
                        break;
                    }
                }
                
                // Word is a Solution? + Score
                Word uInput = null;
                // Checks if the letters are all lower case
                if (!input.matches("^[a-z]*$")) {
                    throw new IllegalWordException("Word must contain only lowercase letters");
                }else {
                    uInput = new Word(input.toLowerCase());
                }
                
                unsortedNode = wordListBank.first.next;
                while (unsortedNode != null) {
                    if (input.equalsIgnoreCase(unsortedNode.data.getWord())) {
                        // Only add word to foundWords if it contains first letter of letters (l)
                        if(containsL) {
                            foundWords.add(uInput);
                            solution = true;
                        }else {
                            JOptionPane.showMessageDialog(null, "The Letter " + letters.charAt(0) + " Must be contained in the input");
                            break;
                        }
                        if(containsAllLetters) {
                            score += 3;
                        }else {
                            score += 1;
                        }
                        break;
                    }
                    unsortedNode = unsortedNode.next;
                }

                // Messages for if user used letter that is not part of String of letters allowed/user uses the letters, but not a solution
                if (!usedLetter) {
                    JOptionPane.showMessageDialog(null, "You may only use letters from Puzzle Letters");
                }else if(!solution || !containsL) {
                    JOptionPane.showMessageDialog(null, "Word is not a Solution");
                }

                right.setText("Words Found:\n");
                
                // Adds SortedWordList foundWords onto the right TextFrame
                sortedNode = foundWords.first.next;
                while (sortedNode != null) {
                    right.append(sortedNode.data.getWord() + "\n");
                    sortedNode = sortedNode.next;
                }
                right.append("Score: " + String.valueOf(score) + "\n");
                if(containsAllLetters) {
                    right.append("Bonus Points for using all letters!\n");
                }
            }
            
            // Game end/Win Message
            if(foundWords.length == wordListBank.length) {
                JOptionPane.showMessageDialog(null, "CONGRATULATIONS!!! YOU WIN!!!\nWords Found: " + foundWords.length + "\nFinal Score: " + score);
            }else if(foundWords.length < wordListBank.length){
                JOptionPane.showMessageDialog(null, "Better Luck Next Time!\nWords Found: " + foundWords.length + "\nFinal Score: " + score);
            }
            
            // Improvement #1: Play Again?
            while(true) {
                String again = JOptionPane.showInputDialog(null, "Would You Like To Play Again?(Enter (YES/NO))");
                if(again != null && again.toUpperCase().equals("YES")) {
                    left.setText("");
                    right.setText("");
                    game();
                    break;
                }else if(again != null && again.toUpperCase().equals("NO")) {
                    JOptionPane.showMessageDialog(null, "Thank You for Playing!");
                    left.setText("");
                    right.setText("");
                    break;
                }else {
                    continue;
                }
            }
        }catch(IllegalWordException IWE) {
            System.out.println(IWE);
        }catch(NullPointerException NPE) {
            System.out.println("No File Selected, please select a Valid File");
        }
    }
}
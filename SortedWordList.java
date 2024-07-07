/**
 * Class SortedWordList extends from WordList
 * has a no-argument Constructor that inherits from WordList
 * has a add method that adds a Word object to the SortedWordList alphabetically
 */
public class SortedWordList extends WordList{
    // no-argument Constructor inherited from WordList
    public SortedWordList() {
        super();
    }
    
    /**
     * adds a word to the SortedWordlist based on alphabetic order
     * 
     * @param w
     *         word that is to be added in the SortedWordList in alphabetic order instead of at the end like UnsortedWordList
     */
    public void add(Word w) {
        WordNode newNode = new WordNode(w);
        WordNode current = first.next;
        WordNode previous = first;

        // Traverse the sortedWordist to find where to add Word
        while (current != null && w.compareTo(current.data) > 0) {
            previous = current;
            current = current.next;
        }

        // adds the WordNode and moves the pointer
        newNode.next = current;
        previous.next = newNode;
        if (current == null) {
            last = newNode; // if the Word is added alphabetically in the end
        }
        length++;
    }
}
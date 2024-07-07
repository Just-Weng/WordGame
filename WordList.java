/**
 * Class WordList is a abstract Class that has a constructor to create a empty WordList
 * a getter for the number of elements in the WordList
 * a append method to add a new Word object to the end of the list
 * a abstract add method for the child classes that extend WordList
 */
public abstract class WordList {
    protected WordNode first;
    protected WordNode last;
    protected int length;
    
    // no-argument Constructor to create a WordList with a head and tail pointing to null and a length = 0 
    public WordList() {
        WordNode wn = new WordNode(null);
        first = wn;
        last = wn;
        length = 0;
    }

    /**
     * Gets the number of data values currently stored in this LinkedList.
     * 
     * @return the number of elements in the list.
     */

    public int getLength() {
        return length;
    }

    /**
     * Appends a String data element to this LinkedList.
     * 
     * @param data
     *            the data element to be appended.
     */
    public void append(Word w) {
        WordNode n = new WordNode(w);
        last.next = n;
        last = last.next;
        last.next = null;
        length++;
    }
    
    // Abstract method add(Word) that adds a Word to the WordList
    abstract void add(Word w);
}
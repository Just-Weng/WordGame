/**
 * Class Word creates a Word object, throws IllegalWordException is word is not all lower case
 * get Word object with getter
 * compares two word objects lexicographically
 */
public class Word {
    private String word;
    
    /**
     * 1-argument Constructor that creates a word object with String w as the data field
     * @param w
     *          String to be converted into a Word Object
     * @throws IllegalWordException
     *                              if word does not contain all lower case letters
     */
    public Word(String w) throws IllegalWordException {
        if(w.compareTo(w.toLowerCase()) != 0) throw new IllegalWordException(w + " must be entirely in lower case");
        word = w;
    }
    
    /**
     * @return the word String from Word object
     */
    public String getWord() {
        return word;
    }

    /**
     * compares Word objects
     * @param other
     *             the other Word object that is being comparedTo
     * @return 0, -1, 1 depending on if the Words are the same, lexicographically less than,
     *         or lexicographically greater than the other Word
     */
    public int compareTo(Word other) {
        return word.compareTo(other.getWord());
    }
}
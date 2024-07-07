/**
 * Class UnsortedWordList extends from WordList
 * has no-argument Constructor inherits from WordList
 * has a add method that uses the append method from WordList
 */
public class UnsortedWordList extends WordList {
    // no-argument Constructor inherited from WordList
    public UnsortedWordList() {
        super();
    }
    
    /**
     * @param word
     *             word to be added to UnsortedWordList through
     *             append method inherited from the WordList Class
     */
    public void add(Word word) {
        append(word);
    }
}

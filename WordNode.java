/**
 * Class WordNode creates a WordNode object with data and a pointer to the next variable
 */
public class WordNode {
   protected Word data;
   protected WordNode next;
   
   /**
    * 1-argument Constructor that initializes a WordNode
    * @param Word is the String that the data part of the node is set to
    */
   public WordNode(Word w) {
      data = w;
      next = null;
   }
}
/**
 * Class FileMenuHandler creates a FileMenu object
 * check for clicks with Action Listener
 * return the absolute path of file chosen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   PuzzleGUI game;
   
   /**
    * 1-argument constructor for the Jframe object passed in
    * @param jf
    *           sets the Jframe object to the File Menu field
    */
   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   
   /**
    * Action listener for when the Menu is clicked on
    * @return void
    */
   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Open")) {
          game = new PuzzleGUI();
      }else if (menuName.equals("Quit"))
          System.exit(0);
   }
   
   /**
    * Reads the Absolute path of the file chosen
    * @return AbsoluteFilePath of the chosen file
    */
   public String readAbsolutePath() {
       JFileChooser fd = new JFileChooser();
       fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
       fd.showOpenDialog(null);
       File f = fd.getSelectedFile();
       return f.getAbsolutePath();
   }
}
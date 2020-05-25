import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.util.ControllerFactory;
import cs3500.animator.util.ViewFactory;
import cs3500.animator.view.IView;


public class EasyAnimator {

  /**
   * Main Method to run the program.
   * @param args the program arguments.
   */
  public static void main(String[] args) {

    JPanel panel = new JPanel();
    String fileName = "";
    String viewType = "";
    String whereOutput = "";
    int tickSpeed = 1;

    for (int i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "-if":
          fileName = args[i + 1];
          break;
        case "-iv":
          viewType = args[i + 1];
          break;
        case "-speed":
          try {
            tickSpeed = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException e) {
            throw new NumberFormatException("Not an int");
          }
          break;
        case "-o":
          whereOutput = args[i + 1];
          break;
        default:
          JOptionPane.showMessageDialog(panel, "Invalid command input");
      }
    }
    AnimationFileReader reader = new AnimationFileReader();

    if (!fileName.equals("")) {
      ViewFactory.Factory viewFactory = ViewFactory.factory();
      ControllerFactory.Factory controllerFactory = ControllerFactory.factory();
      IView v = viewFactory.create(viewType);
      v.setFilePath(whereOutput);
      if (v == null) {
        JOptionPane.showMessageDialog(panel, "Bad view input");
      }
      v.setFilePath(whereOutput);
      try {
        IModel model = reader.readFile(fileName, new Model.Builder());
        IController c = controllerFactory.create(viewType, model, v);
        if (c == null) {
          JOptionPane.showMessageDialog(panel, "Bad controller input");
        }
        c.setFPS(tickSpeed);
        c.run();
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(panel, "File not found");
      }

    }
  }
}

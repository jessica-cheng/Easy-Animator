package cs3500.animator.util;

import cs3500.animator.controller.ControllerSVG;
import cs3500.animator.controller.HybridController;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.ControllerGUI;
import cs3500.animator.controller.ControllerText;
import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;

/**
 * Represents a factory of controllers that constructs an instance of the appropriate concrete
 * controller.
 */
public class ControllerFactory {
  /**
   * Constructs a Controller Factory.
   */
  private ControllerFactory() {

  }

  /**
   * Creates factory objects.
   *
   * @return factory object
   */
  public static Factory factory() {
    return new Factory();
  }

  /**
   * Represents a Factory class to create factory objects.
   */
  public static class Factory {
    public Factory() {
      //Needs to be public to be seen.
    }

    /**
     * Creates a controller of the given String name for a view.
     *
     * @param s String name for a view
     * @param m model
     * @param v view type
     * @return a controller object
     */
    public IController create(String s, IModel m, IView v) {
      if (s.equals("visual")) {
        return new ControllerGUI(m, v);
      }
      if (s.equals("text")) {
        return new ControllerText(m, v);
      }
      if (s.equals("svg")) {
        return new ControllerSVG(m, v);
      }
      if (s.equals("interactive")) {
        return new HybridController(m, v);
      }
      return null;
    }
  }


}

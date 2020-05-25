package cs3500.animator.util;

import cs3500.animator.view.GuiView;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;

/**
 * Represents a factory of views that constructs an instance of the appropriate concrete view.
 */
public class ViewFactory {
  /**
   * Constructs a View Factory.
   */
  private ViewFactory() {

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
     * Creates a view of the given name for a view.
     *
     * @param s string name for a view
     * @return a view object
     */
    public IView create(String s) {
      if (s.equals("visual")) {
        return new GuiView();
      }
      if (s.equals("text")) {
        return new TextView();
      }
      if (s.equals("svg")) {
        return new SvgView();
      }
      if (s.equals("interactive")) {
        return new HybridView();
      }
      return null;
    }
  }
}

import org.junit.Test;


import cs3500.animator.controller.ControllerGUI;
import cs3500.animator.controller.ControllerText;
import cs3500.animator.model.Model;
import cs3500.animator.util.ControllerFactory;
import cs3500.animator.util.ViewFactory;
import cs3500.animator.view.GuiView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertTrue;

public class FactoryTest {
  @Test
  public void testMakeText() {
    ViewFactory.Factory viewFactory = ViewFactory.factory();
    ControllerFactory.Factory controllerFactory = ControllerFactory.factory();

    assertTrue(viewFactory.create("text") instanceof TextView);
    assertTrue(viewFactory.create("visual") instanceof GuiView);
    assertTrue(viewFactory.create("svg") instanceof SvgView);
    assertTrue(controllerFactory.create("text", new Model(), new TextView())
            instanceof ControllerText);
    assertTrue(controllerFactory.create("svg", new Model(), new SvgView())
            instanceof ControllerText);
    assertTrue(controllerFactory.create("visual", new Model(), new GuiView())
            instanceof ControllerGUI);
  }

}

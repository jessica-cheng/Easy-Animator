import org.junit.Test;

import java.io.FileNotFoundException;


import cs3500.animator.controller.IController;
import cs3500.animator.controller.ControllerText;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.GuiView;

import static org.junit.Assert.assertEquals;

public class ControllerTest {


  @Test
  public void testTextController() {
    IModel model = null;
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    IView v = new TextView();
    ControllerText c = new ControllerText(model, v);
    c.setFPS(10);
    c.run();
    assertEquals(out.toString(), "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=100.0s\n" +
            "\n" +
            "Name: C\n" +
            "Type: oval\n" +
            "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
            "Appears at t=6.0s\n" +
            "Disappears at t=100.0s\n" +
            "\n" +
            "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=1.0s to t=5.0s\n" +
            "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=2.0s to t=7.0s\n" +
            "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=5.0s to t=8.0s\n" +
            "Shape R scales from Width: 50.0, Height: 100.0 " +
            "to Width: 25.0, Height: 100.0 from t=5.1s to t=7.0s\n" +
            "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=7.0s to t=10.0s\n");
  }

  @Test
  public void testTextController1() {
    IModel model = null;
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/toh-3.txt", new Model.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    IView v = new TextView();
    ControllerText c = new ControllerText(model, v);
    c.setFPS(10);
    c.run();
    assertEquals(out.toString(), "Shapes:\n" +
            "Name: disk1\n" +
            "Type: rectangle\n" +
            "Corner: (190.0,180.0), Width: 20.0, Height: 30.0, Color: (0.0018560142489150167," +
            "0.19547536969184875,0.35467055439949036)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=302.0s\n" +
            "\n" +
            "Name: disk2\n" +
            "Type: rectangle\n" +
            "Corner: (167.5,210.0), Width: 65.0, Height: 30.0, Color: (0.025823170319199562," +
            "0.9691984057426453,0.16393086314201355)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=302.0s\n" +
            "\n" +
            "Name: disk3\n" +
            "Type: rectangle\n" +
            "Corner: (145.0,240.0), Width: 110.0, Height: 30.0, Color: (0.044487323611974716," +
            "0.17937791347503662,0.6875181198120117)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=302.0s\n" +
            "\n" +
            "Shape disk1 moves from (190.0,180.0) to (190.0,50.0) from t=2.5s to t=3.5s\n" +
            "Shape disk1 moves from (190.0,50.0) to (490.0,50.0) from t=3.6s to t=4.6s\n" +
            "Shape disk1 moves from (490.0,50.0) to (490.0,240.0) from t=4.7s to t=5.7s\n" +
            "Shape disk2 moves from (167.5,210.0) to (167.5,50.0) from t=5.7s to t=6.7s\n" +
            "Shape disk2 moves from (167.5,50.0) to (317.5,50.0) from t=6.8s to t=7.8s\n" +
            "Shape disk2 moves from (317.5,50.0) to (317.5,240.0) from t=7.9s to t=8.9s\n" +
            "Shape disk1 moves from (490.0,240.0) to (490.0,50.0) from t=8.9s to t=9.9s\n" +
            "Shape disk1 moves from (490.0,50.0) to (340.0,50.0) from t=10.0s to t=11.0s\n" +
            "Shape disk1 moves from (340.0,50.0) to (340.0,210.0) from t=11.1s to t=12.1s\n" +
            "Shape disk3 moves from (145.0,240.0) to (145.0,50.0) from t=12.1s to t=13.1s\n" +
            "Shape disk3 moves from (145.0,50.0) to (445.0,50.0) from t=13.2s to t=14.2s\n" +
            "Shape disk3 moves from (445.0,50.0) to (445.0,240.0) from t=14.3s to t=15.3s\n" +
            "Shape disk1 moves from (340.0,210.0) to (340.0,50.0) from t=15.3s to t=16.3s\n" +
            "Shape disk3 changes color from (0.044487323611974716,0.17937791347503662," +
            "0.6875181198120117) to (0.0,1.0,0.0) from t=15.3s to t=16.1s\n" +
            "Shape disk1 moves from (340.0,50.0) to (190.0,50.0) from t=16.4s to t=17.4s\n" +
            "Shape disk1 moves from (190.0,50.0) to (190.0,240.0) from t=17.5s to t=18.5s\n" +
            "Shape disk2 moves from (317.5,240.0) to (317.5,50.0) from t=18.5s to t=19.5s\n" +
            "Shape disk2 moves from (317.5,50.0) to (467.5,50.0) from t=19.6s to t=20.6s\n" +
            "Shape disk2 moves from (467.5,50.0) to (467.5,210.0) from t=20.7s to t=21.7s\n" +
            "Shape disk1 moves from (190.0,240.0) to (190.0,50.0) from t=21.7s to t=22.7s\n" +
            "Shape disk2 changes color from (0.025823170319199562,0.9691984057426453," +
            "0.16393086314201355) to (0.0,1.0,0.0) from t=21.7s to t=22.5s\n" +
            "Shape disk1 moves from (190.0,50.0) to (490.0,50.0) from t=22.8s to t=23.8s\n" +
            "Shape disk1 moves from (490.0,50.0) to (490.0,180.0) from t=23.9s to t=24.9s\n" +
            "Shape disk1 changes color from (0.0018560142489150167," +
            "0.19547536969184875,0.35467055439949036) to (0.0,1.0,0.0) from t=24.9s to t=25.7s\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadInputs() {
    IController c1 = new ControllerText(null, null);
    IController c9 = new ControllerText(new Model(), null);
    IController c2 = new ControllerText(null, new GuiView());
    IController c3 = new ControllerText(null, new TextView());
    IController c4 = new ControllerText(null, new SvgView());
    IController c5 = new ControllerText(new Model(), null);
    IController c6 = new ControllerText(null, new GuiView());
    IController c7 = new ControllerText(null, new TextView());
    IController c8 = new ControllerText(null, new SvgView());
    IController c10 = new ControllerText(null, null);
  }
}

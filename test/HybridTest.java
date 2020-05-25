import org.junit.Test;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import cs3500.animator.controller.HybridController;
import cs3500.animator.controller.IHybridController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.shape.IShape;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.HybridView;
import cs3500.animator.util.AnimationFileReader;
import static org.junit.Assert.assertEquals;


public class HybridTest {
  HybridView hybrid = new HybridView();
  IModel model;
  IHybridController hybridController;
  ButtonListener buttonListener;
  IModel m = new Model();


  @Test
  public void testSetFps() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    hybridController.setFPS(20);
    assertEquals(20, hybridController.getFps());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    hybridController = new HybridController(model, hybrid);
  }

  // test needs to be written for getModel
  @Test
  public void testGetModel() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    for (IShape s : model.getListOfShapes()) {
      m.addShapes(s);
    }
    List<IShape> expected = m.getListOfShapes();
    List<IShape> actual = hybridController.getModel().getListOfShapes();
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).generateLoop(), actual.get(i).generateLoop());
      assertEquals(expected.get(i).getName(), actual.get(i).getName());
      assertEquals(expected.get(i).getColor().getGreen(),
              actual.get(i).getColor().getGreen(), 0.001);
      assertEquals(expected.get(i).getColor().getRed(),
              actual.get(i).getColor().getRed(), 0.001);
      assertEquals(expected.get(i).getColor().getBlue(),
              actual.get(i).getColor().getBlue(), 0.001);
    }
  }

  @Test
  public void testGetFps() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(0, hybridController.getFps());
  }

  @Test
  public void testSetPause() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    hybridController.loop();
    assertEquals(true, hybridController.getIsPaused());
    hybridController.setPause();
    assertEquals(true, hybridController.getIsPaused());
    hybridController.resume();
    assertEquals(false, hybridController.getIsPaused());
    hybridController.loop();
    assertEquals(false, hybridController.getIsPaused());
  }

  @Test
  public void testSetLoop() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(false, hybridController.getHasLoop());
    hybridController.setLoop();
    assertEquals(true, hybridController.getHasLoop());
  }

  // not sure if correcct
  @Test
  public void testRestart() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    hybridController.restart();
    assertEquals(1, hybridController.getFrameCount());
  }

  @Test
  public void testRestartFps() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    hybridController.timerRestart(10);
    hybridController.setAndRestart(100);
    assertEquals(100, hybridController.getFps());
  }

  // ButtonListener tests
  @Test
  public void testActionPerformed() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    buttonListener = new ButtonListener(hybridController);
    ActionEvent start = new ActionEvent(buttonListener, 10, "Start animation");
    buttonListener.actionPerformed(start);
    assertEquals(false, hybridController.getIsPaused());
    ActionEvent pause = new ActionEvent(buttonListener, 10, "Pause animation");
    buttonListener.actionPerformed(pause);
    assertEquals(true, hybridController.getIsPaused());
    ActionEvent resume = new ActionEvent(buttonListener, 10, "Resume animation");
    buttonListener.actionPerformed(resume);
    assertEquals(false, hybridController.getIsPaused());
  }

  @Test
  public void actionPerformedView() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(hybrid.getActionText(), "Ready to start");
    ActionEvent start = new ActionEvent(hybrid, 10, "Start animation");
    hybrid.actionPerformed(start);
    assertEquals(hybrid.getActionText(), "Animation was started");
    ActionEvent pause = new ActionEvent(hybrid, 10, "Pause animation");
    hybrid.actionPerformed(pause);
    assertEquals(hybrid.getActionText(), "Animation was paused");
    ActionEvent resume = new ActionEvent(hybrid, 10, "Resume animation");
    hybrid.actionPerformed(resume);
    assertEquals(hybrid.getActionText(), "Animation was resumed");
    hybrid.actionPerformed(pause);
    assertEquals(hybrid.getActionText(), "Animation was paused");
  }


  @Test
  public void testChangeEvent() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    hybridController.timerRestart(10);
    JSlider fpsControl = new JSlider(JSlider.HORIZONTAL, 1, 300, 20);
    fpsControl.addChangeListener(buttonListener);
    buttonListener = new ButtonListener(hybridController);
    ChangeEvent e = new ChangeEvent(fpsControl);
    buttonListener.stateChanged(e);
    assertEquals(20, hybridController.getFps());
  }

  @Test
  public void testSliderView() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    JSlider fpsControl = new JSlider(JSlider.HORIZONTAL, 1, 300, 20);
    fpsControl.addChangeListener(hybrid);
    ChangeEvent e = new ChangeEvent(fpsControl);
    hybrid.stateChanged(e);
    assertEquals(hybrid.getActionText(), "Speed changed to 20 fps.");
    fpsControl.setValue(30);
    hybrid.stateChanged(e);
    assertEquals(hybrid.getActionText(), "Speed changed to 30 fps.");
  }

  @Test
  public void testItemStateChanged() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    buttonListener = new ButtonListener(hybridController);
    JCheckBox loopButton = new JCheckBox("Loop");
    loopButton.setSelected(false);
    loopButton.setActionCommand("Loop animation");
    loopButton.addItemListener(buttonListener);
    ItemEvent loop = new ItemEvent(loopButton, 1, buttonListener, 1);
    buttonListener.itemStateChanged(loop);
    assertEquals(true, hybridController.getHasLoop());
    ItemEvent noLoop = new ItemEvent(loopButton, 1, buttonListener, 2);
    buttonListener.itemStateChanged(noLoop);
    assertEquals(false, hybridController.getHasLoop());
  }

  @Test
  public void testViewStateChanged() {
    AnimationFileReader reader = new AnimationFileReader();
    try {
      model = reader.readFile("resources/smalldemo.txt", new Model.Builder());
      hybridController = new HybridController(model, hybrid);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    buttonListener = new ButtonListener(hybridController);
    JCheckBox loopButton = new JCheckBox("Loop");
    loopButton.setSelected(false);
    loopButton.setActionCommand("Loop animation");
    loopButton.addItemListener(hybrid);
    assertEquals(hybrid.getActionText(), "Ready to start");
    ItemEvent loop = new ItemEvent(loopButton, 1, hybrid, 1);
    hybrid.itemStateChanged(loop);
    assertEquals(hybrid.getActionText(), "Looping...");
    ItemEvent loopback = new ItemEvent(loopButton, 1, hybrid, 0);
    hybrid.itemStateChanged(loopback);
    assertEquals(hybrid.getActionText(), "");
    hybrid.itemStateChanged(loop);
    assertEquals(hybrid.getActionText(), "Looping...");
    hybrid.itemStateChanged(loopback);
    assertEquals(hybrid.getActionText(), "");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void UnsupportedMethods() {
    hybrid.setFps(10);
    ArrayList<IShape> temp = new ArrayList<>();
    try {
      hybrid.drawForSVG(temp);
    } catch (IOException e) {
      e.printStackTrace();
    }
    hybrid.drawString("wow");
    hybrid.setFilePath("ojj.svg");
  }
}

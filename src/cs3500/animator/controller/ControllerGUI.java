package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import cs3500.animator.model.IModel;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.view.IView;

/**
 * Represents a controller for the visual animations.
 */
public class ControllerGUI implements IController {
  private int frameCount = 1;
  private Timer timer;
  private IModel model;
  private IView view;
  private int fps;

  /**
   * Constructs a GUI controller.
   *
   * @param model model the controller works on
   * @param view  the type of view the controller runs on
   */
  public ControllerGUI(IModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("bad view or model");
    }
    this.model = model;
    this.view = view;
  }


  @Override
  public void run() {
    timer = new Timer(1000 / fps, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        ArrayList<ReadableShape> forDrawing = model.getShapesAtFrame(frameCount);
        try {
          view.drawShapes(forDrawing);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        frameCount++;
        if (model.getMaxFrame() < frameCount) {
          timer.stop();
        }
      }
    });
    timer.start();
  }

  @Override
  public void setFPS(int fps) {
    this.fps = fps;
  }
}

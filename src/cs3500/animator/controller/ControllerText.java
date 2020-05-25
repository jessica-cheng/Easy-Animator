package cs3500.animator.controller;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.IModel;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.view.IView;

/**
 * Represents a controller for running the text animations.
 */
public class ControllerText implements IController {
  private int frameCount = 1;
  private IModel model;
  private IView view;
  private int fps;

  /**
   * Constructs a text controller.
   *
   * @param m model the controller works on
   * @param v the type of view the controller runs
   */
  public ControllerText(IModel m, IView v) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Bad view or model");
    }
    this.view = v;
    this.model = m;
  }


  @Override
  public void run() {
    while (frameCount < model.getMaxFrame()) {
      ArrayList<ReadableShape> temp;
      if (frameCount == 1) {
        view.drawString(model.getShapeDescription());
      }
      try {
        temp = model.getAnimTextAtFrame(frameCount, fps);
        view.drawShapes(temp);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      frameCount++;
    }
    view.export();
  }

  @Override
  public void setFPS(int fps) {
    this.fps = fps;
  }

  public int getFPS() {
    return this.fps;
  }
}

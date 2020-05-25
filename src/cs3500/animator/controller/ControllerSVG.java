package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;

public class ControllerSVG implements IController {
  private IModel model;
  private IView view;
  private int fps;

  public ControllerSVG(IModel m, IView v) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Bad view or model");
    }
    this.view = v;
    this.model = m;
  }

  @Override
  public void run() {
    this.view.setFps(this.fps);
    try {
      this.view.drawForSVG(model.getListOfShapes());
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.view.export();
  }

  @Override
  public void setFPS(int fps) {
    this.fps = fps;
  }
}

package cs3500.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Model;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.util.ButtonListener;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;

/**
 * Represents a controller for running the hybrid animations of both visual and SVG. The view it
 * works has a ButtonListener that takes in this {@link IHybridController} to listen to action
 * commands and performs the specific action which is described in the methods described in this
 * controller.
 */
public class HybridController implements IHybridController {
  private Timer timer;
  private IModel model;
  private IView view;
  private int fps;
  private int frameCount = 1;
  private boolean isPaused = true;
  private boolean hasLoop = false;

  /**
   * Constructs a hybrid controller.
   *
   * @param m model the controller works on
   * @param v the type of view the controller runs
   */
  public HybridController(IModel m, IView v) {
    if (m == null || v == null) {
      throw new IllegalArgumentException("Bad view or model");
    }
    this.model = m;
    this.view = v;
    v.setButtonListener(new ButtonListener(this));
    ArrayList<ReadableShape> forDrawing = model.getShapesAtFrame(frameCount);
    v.setShapes(forDrawing);
    v.export();
  }

  @Override
  public void run() {
    timerRestart(this.fps);
    model.set();
    timer.start();

  }

  @Override
  public void setFPS(int fps) {
    this.fps = fps;
  }

  @Override
  public void setAndRestart(int fps) {
    timer.stop();
    this.fps = fps;
    timerRestart(fps);
    timer.start();
  }

  @Override
  public void scrub(int frame) {
    if (frame != 0 && (frameCount <= frame || frameCount >= frame)) {
      timer.stop();
      frameCount = frame;
      ArrayList<ReadableShape> forDrawing = model.getShapesAtFrame(frameCount);
      timerRestart(this.fps);
      timer.start();
      try {
        view.drawShapes(forDrawing);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void timerRestart(int i) {
    timer = new Timer(1000 / i, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        ArrayList<ReadableShape> forDrawing = model.getShapesAtFrame(frameCount);
        try {
          view.drawShapes(forDrawing);
          //scrub(frameCount);
          System.out.println(view.getEnd());
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        if (!isPaused && !hasLoop) {
          if (frameCount != model.getMaxFrame() - 1) {
            frameCount++;
          }
        }
        if (!isPaused && hasLoop) {
          if (frameCount != model.getMaxFrame() - 1) {
            frameCount++;
          }
          loop();
          //scrub(frameCount);
        }
      }
    });
  }

  @Override
  public boolean getIsPaused() {
    return this.isPaused;
  }

  @Override
  public void setPause() {
    isPaused = true;
  }

  @Override
  public void resume() {
    isPaused = false;
  }

  @Override
  public void restart() {
    frameCount = 1;
    model.reset();
  }

  @Override
  public boolean getHasLoop() {
    return this.hasLoop;
  }

  @Override
  public void setLoop() {
    if (hasLoop) {
      this.hasLoop = false;
    } else {
      this.hasLoop = true;
    }
  }

  @Override
  public void loop() {
    if (this.frameCount == model.getMaxFrame() - 1 && hasLoop) {
      this.restart();
    }
  }

  @Override
  public IModel getModel() {
    IModel m = new Model();
    for (IShape s : this.model.getOriginal()) {
      m.addShapes(s);
    }
    return m;
  }

  @Override
  public int getFps() {
    return this.fps;
  }

  @Override
  public int getFrameCount() {
    return this.frameCount;
  }

  public boolean scurbContains(Point e){
    return view.scrubContains(e);
  }
}

package cs3500.animator.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.IHybridController;
import cs3500.animator.model.IModel;
import cs3500.animator.view.SvgView;

/**
 * Represents a button listener and takes in an {@link IHybridController}.
 * It is an object of {@link ActionListener} and processes an action event and invokes performed
 * actions/methods from {@link IHybridController}. It is also an object of {@link ChangeListener}
 * and listens for ChangeEvents. It is also an object of {@link ItemListener} and processes item
 * events. When item-selection event occurs, the ButtonListener's state change is invoked.
 */
public class ButtonListener implements ActionListener, ChangeListener, ItemListener, MouseListener {

  IHybridController hybridController;

  /**
   * Constructs a ButtonListener.
   *
   * @param hybridController the IHybridController
   */
  public ButtonListener(IHybridController hybridController) {
    this.hybridController = hybridController;
  }

  /**
   * Invokes performed actions or methods from IHybridController objects.
   *
   * @param e Action Event it listens to
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start animation":
        hybridController.resume();
        break;
      case "Pause animation":
        hybridController.setPause();
        break;
      case "Resume animation":
        hybridController.resume();
        break;
      case "Restart animation":
        hybridController.restart();
        break;
      case "Export to SVG":
        String path = JOptionPane.showInputDialog("Enter a path");
        ControllerFactory.Factory controllerFactory = ControllerFactory.factory();
        SvgView v = new SvgView();
        IModel m = hybridController.getModel();
        IController c = controllerFactory.create("svg", m, v);
        v.setFilePath(path);
        if (hybridController.getHasLoop()) {
          v.setLooping(m.getMaxFrame());
        }
        c.setFPS(hybridController.getFps());
        c.run();
        break;
      default:
    }
  }

  /**
   * Invoked when JSlider for changing fps has changed its state.
   *
   * @param e Change Event
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    String name = source.getName();
    //JSlider source2 = (JSlider) e.getSource();
    if (name.equals("FPS")) {
      if (!source.getValueIsAdjusting()) {
        int fps = (int) source.getValue();
        hybridController.setAndRestart(fps);
      }
    }
    if (name.equals("Scrub")) {
      if (source.getValueIsAdjusting()) {
        int frameCount = source.getValue();
        hybridController.setPause();
        hybridController.scrub(frameCount);
      }
    }

  }

  /**
   * Invoked when JCheckBox has been selected or deselected by the user. Loops animation if checked,
   * does not loop animation when unchecked.
   *
   * @param e Item Event
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    String s = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (s.equals("Loop animation")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        hybridController.setLoop();
        hybridController.loop();
      } else {
        hybridController.setLoop();
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    return;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    return;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (hybridController.scurbContains(e.getPoint())) {
      hybridController.resume();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    return;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    return;
  }

}

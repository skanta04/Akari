package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class CompoundView implements ModelObserver {
  private final ControllerImpl controller;

  public CompoundView(ControllerImpl controller) {
    this.controller = controller;
  }

  public Parent render() {
    VBox layout = new VBox();

    TitleView titleView = new TitleView(controller);
    ControlView controlsView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    puzzleIndexView indexView = new puzzleIndexView(controller);
    SolvedView solveView = new SolvedView(controller);
    layout.getChildren().add(titleView.render());
    layout.getChildren().add(indexView.render());
    layout.getChildren().add(controlsView.render());
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(solveView.render());

    return layout;
  }

  @Override
  public void update(Model model) {}
}

package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class SolvedView {
  private final ControllerImpl controller;

  public SolvedView(ControllerImpl controller) {

    this.controller = controller;
  }

  public Parent render() {
    HBox layout = new HBox();
    if (controller.isSolved()) {
      Label successLabel = new Label("Congrats! You solved the puzzle!");
      successLabel.setStyle("-fx-font-size: 20; -fx-text-fill: green;");
      layout.getChildren().add(successLabel);
      layout.setAlignment(Pos.CENTER);
      return layout;

    } else {
      Label notSolved = new Label("Puzzle not solved");
      notSolved.setStyle("-fx-font-size: 20; -fx-text-fill: red");
      layout.getChildren().add(notSolved);
      layout.setAlignment(Pos.CENTER);
      return layout;
    }
  }
}

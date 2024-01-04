package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView {
  private final ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  public Parent render() {
    HBox controls = new HBox();
    controls.setAlignment(Pos.TOP_CENTER);

    // reset button
    Button reset = new Button("Reset");
    reset.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    controls.getChildren().add(reset);

    // randomize button
    Button random = new Button("Random Puzzle");
    random.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    controls.getChildren().add(random);

    // previous button
    Button prev = new Button("Previous");
    prev.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    controls.getChildren().add(prev);

    // next button
    Button next = new Button("Next");
    next.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    controls.getChildren().add(next);

    return controls;
  }
}

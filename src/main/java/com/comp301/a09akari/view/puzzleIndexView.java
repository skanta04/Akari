package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class puzzleIndexView {
  private final ControllerImpl controller;

  public puzzleIndexView(ControllerImpl controller) {
    this.controller = controller;
  }

  public Parent render() {
    HBox layout = new HBox();

    Text puzzle = new Text("Puzzle ");
    puzzle.setStyle("-fx-font-size: 20");
    layout.getChildren().add(puzzle);

    int index = controller.getPuzzleIndex();
    String puzzleNum = Integer.toString(index + 1);
    Text i = new Text(puzzleNum);
    i.setStyle("-fx-font-size: 20");
    layout.getChildren().add(i);

    Text of = new Text(" of");
    of.setStyle("-fx-font-size: 20");
    Text five = new Text(" 5");
    five.setStyle("-fx-font-size: 20");
    layout.getChildren().add(of);
    layout.getChildren().add(five);

    layout.setPadding(new Insets(15, 12, 15, 12));
    layout.setAlignment(Pos.CENTER);
    return layout;
  }
}

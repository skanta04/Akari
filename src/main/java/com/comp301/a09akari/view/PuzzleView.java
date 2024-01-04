package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import java.awt.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class PuzzleView {
  private final ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  public Parent render() {
    GridPane board = new GridPane();
    board.setAlignment(Pos.CENTER);
    board.setGridLinesVisible(true);

    int rows = controller.getActivePuzzle().getHeight();
    int cols = controller.getActivePuzzle().getWidth();

    for (int i = 0; i < cols; i++) {
      ColumnConstraints colConst = new ColumnConstraints();
      colConst.setMaxWidth(100);
      colConst.setMinWidth(40);
      board.getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < rows; i++) {
      RowConstraints rowConst = new RowConstraints();
      rowConst.setMaxHeight(80);
      rowConst.setMinHeight(40);
      board.getRowConstraints().add(rowConst);
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {

        if (controller.getActivePuzzle().getCellType(i, j) == CellType.WALL) {
          StackPane tile = new StackPane();
          tile.setStyle("-fx-background-color: black");
          board.add(tile, j, i, 1, 1);
        }

        if (controller.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          StackPane tile = new StackPane();
          Image img = new Image("light-bulb.png");
          ImageView imgView = new ImageView(img);
          imgView.setFitHeight(30);
          imgView.setFitWidth(30);
          tile.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

          int row = i;
          int col = j;

          tile.setOnMouseClicked(
              (event) -> {
                controller.clickCell(row, col);
              });

          if (controller.isLit(i, j)) {
            tile.setStyle(
                "-fx-background-color: lightyellow; -fx-border-color: black; -fx-border-width: 1px;");
          }

          if (controller.isLamp(i, j)) {
            if (controller.isIllegal(i, j)) {
              Label label = new Label("X");
              label.setStyle("-fx-text-fill: red");
              tile.getChildren().add(label);
            } else {
              tile.getChildren().add(imgView);
              tile.setStyle(
                  "-fx-background-color: yellow; -fx-border-color: black; -fx-border-width: 1px;");
            }
          }
          board.add(tile, j, i);
        }

        if (controller.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          StackPane tile = new StackPane();
          tile.setStyle("-fx-background-color: black");

          int clueNum = controller.getActivePuzzle().getClue(i, j);
          String clue = Integer.toString(clueNum);
          Label label = new Label(clue);
          label.setStyle("-fx-text-fill: white");
          tile.getChildren().add(label);

          board.add(tile, j, i);

          if (controller.isClueSatisfied(i, j)) {
            tile.setStyle(
                "-fx-background-color: green; -fx-border-color: black; -fx-border-width: 1px;");
          }
        }
      }
    }
    return board;
  }
}

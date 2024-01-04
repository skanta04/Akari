package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TitleView {
  private final ControllerImpl controller;

  public TitleView(ControllerImpl controller) {
    this.controller = controller;
  }

  public Parent render() {
    HBox layout = new HBox();
    Text title = new Text("AKARI!!!");
    title.setStyle("-fx-font-size: 65");
    layout.setPadding(new Insets(15, 12, 15, 12));
    layout.getChildren().add(title);
    layout.setAlignment(Pos.CENTER);
    return layout;
  }
}

package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricliquid.gui.nodes.MovableNodeBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Ignacio Slater Muñoz.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class CitricLiquid extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";
  GameController controller = new GameController();

  /**
   * @param stage primario
   */
  @Override
  public void start(@NotNull Stage stage) throws FileNotFoundException {
    stage.setTitle("99.7% Citric Liquid");
    stage.setResizable(false);
    Image boardImage = new Image(new FileInputStream(RESOURCE_PATH + "800px-A-Practice_Field" +
            ".png"));
    ImageView imageView = new ImageView(boardImage);
    imageView.setX(255);
    imageView.setY(45);
    imageView.setFitHeight(450);
    imageView.setFitWidth(450);
    imageView.setPreserveRatio(true);

    Group root = new Group(); // nodo raiz que agrupa otras cosas (nodos), clase padre de todos
    // los componentes
    int width = 960;
    int height = 540;
    Scene scene = new Scene(root, width, height); //Stage->Scene->Group
    var sprite = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite.png")
                                              .setPosition(100, 100) //360,150
                                              .setSize(45, 45)
                                              .build();
    var background =
        new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);
    root.getChildren().add(imageView);
    root.getChildren().add(setupButton()); //agrega el boton
    root.getChildren().add(sprite.getNode());
    stage.setScene(scene);
    stage.show();
  }

  private @NotNull Button setupButton() {
    Button button = new Button("Play Kai's Theme");
    button.setLayoutX(620);
    button.setLayoutY(495);
    button.setFocusTraversable(false);
    button.setOnAction(CitricLiquid::playSound); //accion que se genera al oprimir el boton
    return button;
  }



  private static void playSound(ActionEvent event) {
    String audioFilePath = RESOURCE_PATH + "kai's-theme.wav";
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
          new File(audioFilePath))) {
          sound.open(audioInputStream);
          sound.start();
          sound.loop(Clip.LOOP_CONTINUOUSLY);
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }
}
package com.jason;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Saravanababu
 */
public class CubeDemo extends Application {

    private Timeline animation;

    @Override
    public void start(Stage primaryStage) {

        Group cube = new Group();
        //size of the cube
        double size = 75;
        //set color for the cube
        Color color = Color.DARKCYAN;
        cube.getChildren().addAll(
                RectangleBuilder.create() // back face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.5 * 1), 1.0))
                .translateX(-0.5 * size)
                .translateY(-0.5 * size)
                .translateZ(0.5 * size)
                .build(),
                RectangleBuilder.create() // bottom face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.4 * 1), 1.0))
                .translateX(-0.5 * size)
                .translateY(0)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .build(),
                RectangleBuilder.create() // right face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.3 * 1), 1.0))
                .translateX(-1 * size)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .build(),
                RectangleBuilder.create() // left face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.2 * 1), 1.0))
                .translateX(0)
                .translateY(-0.5 * size)
                .rotationAxis(Rotate.Y_AXIS)
                .rotate(90)
                .build(),
                RectangleBuilder.create() // top face
                .width(size).height(size)
                .fill(color.deriveColor(0.0, 1.0, (1 - 0.1 * 1), 1.0))
                .translateX(-0.5 * size)
                .translateY(-1 * size)
                .rotationAxis(Rotate.X_AXIS)
                .rotate(90)
                .build(),
                RectangleBuilder.create() // front face
                .width(size).height(size)
                .fill(color)
                .translateX(-0.5 * size)
                .translateY(-0.5 * size)
                .translateZ(-0.5 * size)
                .build());
        cube.getTransforms().addAll(new Rotate(45, Rotate.X_AXIS), new Rotate(45, Rotate.Y_AXIS));
        StackPane root = new StackPane();
        //add cube to root 
        root.getChildren().add(cube);
        //animate the created cube
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                new KeyValue(cube.rotationAxisProperty(), Rotate.Z_AXIS),
                new KeyValue(cube.rotateProperty(), 0d)),
                new KeyFrame(Duration.seconds(5),
                new KeyValue(cube.rotationAxisProperty(), Rotate.Z_AXIS),
                new KeyValue(cube.rotateProperty(), 360d)));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        Scene scene = new Scene(root, 300, 250, true);
        scene.setCamera(new PerspectiveCamera());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cube Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
package com.sahajamit.rx;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by amitrawat on 1/5/18.
 */
public class SimpleButton extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        VBox vBox = new VBox();
        vBox.setMaxWidth(200);
        vBox.setMaxHeight(400);
        Button button = new Button("Press Me");
        Label countLabel = new Label("0");
        Label doneLabel = new Label("");

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> 1)
                .scan(0,(x,y) -> x + y)
                .take(5)
                .subscribe(
                        clickCount -> countLabel.setText(clickCount.toString()),
                        Throwable::printStackTrace,
                        () -> doneLabel.setText("Done!")
                );

        vBox.getChildren().addAll(button,countLabel,doneLabel);
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

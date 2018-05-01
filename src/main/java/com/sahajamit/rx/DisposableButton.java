package com.sahajamit.rx;

import io.reactivex.disposables.Disposable;
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
public class DisposableButton extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Button unsubscribeButton = new Button("Unsubscribe");

        Label countLabel = new Label("0");

        Disposable disposable = JavaFxObservable.actionEventsOf(button)
                .map(ae -> 1)
                .scan(0,(x,y) -> x + y)
                .subscribe(clickCount -> countLabel.setText(clickCount.toString()));

        unsubscribeButton.setOnAction(e -> disposable.dispose());


        vBox.getChildren().addAll(button,unsubscribeButton,countLabel);
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

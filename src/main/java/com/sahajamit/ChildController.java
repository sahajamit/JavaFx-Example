package com.sahajamit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by amitrawat on 13/5/18.
 */
public class ChildController {
    private boolean answer;
    private Stage stage;
    @FXML
    private Button yesBtn;
    @FXML
    private Button noBtn;


    public boolean display(String title, String message) throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/childWindow.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(gridPane, 500, 400));
        yesBtn = (Button) gridPane.getChildren().get(1);
        noBtn = (Button) gridPane.getChildren().get(2);
        setButtonHandlers();

        stage.showAndWait();
        return answer;
    }

    private void setButtonHandlers(){
        yesBtn.setOnMouseClicked(e->{
            answer = true;
            stage.close();
        });

        noBtn.setOnMouseClicked(e->{
            answer = false;
            stage.close();
        });
    }
}

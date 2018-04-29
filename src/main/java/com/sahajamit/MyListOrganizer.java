package com.sahajamit;

import com.sahajamit.model.MyListCell;
import com.sahajamit.model.TodoItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.vocab.Vocabulary.PREFIX;


public class MyListOrganizer extends Application {

    public static final ObservableList<String> todoItems = FXCollections.observableArrayList(
            "Mail birthday card",
            "Doctor's Appointment",
            "Finish design proposal for client",
            "Pickup Doug at the train station",
            "Pick up dry cleaning"
    );

    @Override
    public void start(Stage stage) throws Exception {
        ListView<String> todoListView = new ListView<>();
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.setCellFactory(param -> new MyListCell());
        todoListView.setPrefWidth(500);
        todoListView.getItems().setAll(todoItems);


//        todoListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("clicked on " + todoListView.getSelectionModel().getSelectedItem());
//            }
//        });

        VBox layout = new VBox(todoListView);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(
                this.getClass().getResource("/styles.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(MyListOrganizer.class);
    }
}

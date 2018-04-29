package com.sahajamit.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.List;

import static com.sahajamit.MyListOrganizer.todoItems;

/**
 * Created by amitrawat on 29/4/18.
 */
public class MyListCell extends ListCell<String> {

    public MyListCell() {
        ListCell thisCell = this;
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        setAlignment(Pos.CENTER_LEFT);

        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem());
            dragboard.setContent(content);
            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
//            if (db.hasString() && dragSource.get() != null) {
                ObservableList<String> items = getListView().getItems();
                int draggedIdx = items.indexOf(db.getString());
                int thisIdx = items.indexOf(getItem());

                String temp = todoItems.get(draggedIdx);
                todoItems.set(draggedIdx, todoItems.get(thisIdx));
                todoItems.set(thisIdx, temp);

                items.set(draggedIdx, getItem());
                items.set(thisIdx, db.getString());

                List<String> itemscopy = new ArrayList<>(getListView().getItems());
                getListView().getItems().setAll(itemscopy);
                success = true;
                System.out.println(String.format("Dragged the Item '%s' over '%s'",
                        todoItems.get(thisIdx),
                        todoItems.get(draggedIdx)));
            }
            event.setDropCompleted(success);
            event.consume();

        });

        setOnDragDone(DragEvent::consume);

        setOnMouseClicked(event->{
            if (!thisCell.isEmpty()) {
                System.out.println("You clicked on " + thisCell.getItem());
                event.consume();
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText(item);
        }
    }
}

package org.example.goalsimulator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class GoalSimulator extends Application {
    @Override
    public void start(Stage stage) {{

        AtomicReference<Double> goal = new AtomicReference<>((double) 0);
        AtomicReference<Double> saved = new AtomicReference<>((double) 0);
        AtomicReference<Double> remaining = new AtomicReference<>((double) 0);

        Button setGoal = new Button("Set goal");
        Button addMoney = new Button("Add money");
        Button CheckProgress = new Button("Check progress");
        Label goalMoney = new Label();
        Label savedMoney = new Label();
        Label remainingMoney = new Label();

        TextInputDialog textInputDialog  = new TextInputDialog();

        setGoal.setOnAction(event -> {
            textInputDialog.setHeaderText("Enter goal: ");
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent() == true) {
                try {
                    double amount = Double.parseDouble(result.get());
                    if (amount > 0) {
                        goal.set(amount);
                        textInputDialog.getEditor().clear();
                    }else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Number need greater 0");
                        alert.show();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error");
                    alert.show();
                }
            }
        });
        addMoney.setOnAction(event -> {
            textInputDialog.setHeaderText("Add money: ");
            Optional<String> result = textInputDialog.showAndWait();
            if (result.isPresent() == true) {
                try {
                    double amount = Double.parseDouble(result.get());
                    if (amount > 0) {
                        saved.set(saved.get()+amount);
                        textInputDialog.getEditor().clear();
                    }else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Number need greater 0");
                        alert.show();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error");
                    alert.show();
                }
            }
        });
        CheckProgress.setOnAction(event -> {
            goalMoney.setText("Goal: "+goal.get());

            savedMoney.setText("Saved: "+saved.get());

            remaining.set(goal.get()- saved.get());
            remainingMoney.setText("Remaining: "+remaining.get());
        });


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(setGoal,addMoney,CheckProgress,goalMoney,savedMoney,remainingMoney);

        Scene scene = new Scene(vbox, 500, 500);

        stage.setTitle("Goal simulator");
        stage.setScene(scene);
        stage.show();

    }}}
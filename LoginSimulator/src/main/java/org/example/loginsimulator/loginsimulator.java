package org.example.loginsimulator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class loginsimulator extends Application {
    @Override
    public void start(Stage stage) {{

        AtomicInteger attempts = new AtomicInteger(3);
        String correctPassword = "1234";

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Text logintext = new Text("LOGIN");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(190);

        Button login = new Button("Login");
        Button reset = new Button("Reset");

        Label infoAttempts = new Label("Попыток осталось: " + attempts.get());
        login.setOnAction(event -> {
            if (attempts.get() == 0) {
                login.setDisable(true);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Not can login");
                alert.show();
            }
            String password = passwordField.getText();
            if (correctPassword.equals(password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Success login on account!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Login incorrect!");
                attempts.getAndDecrement();
                infoAttempts.setText("Попыток осталось: " + attempts.get());
                if (attempts.get() == 0) {
                    login.setDisable(true);

                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setContentText("Close logining");
                    alert2.show();
                }

                alert.show();

            }
        });

        reset.setOnAction(event -> {
            login.setDisable(false);
            passwordField.clear();
            attempts.set(3);
            infoAttempts.setText("Попыток осталось: " + attempts.get());

        });

        vbox.getChildren().addAll(logintext, passwordField, login, reset, infoAttempts);

        Scene scene = new Scene(vbox, 500, 500);

        stage.setTitle("Login simulator");
        stage.setScene(scene);
        stage.show();
    }
    }}

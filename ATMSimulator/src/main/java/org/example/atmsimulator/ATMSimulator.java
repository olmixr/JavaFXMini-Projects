package org.example.atmsimulator;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ATMSimulator extends Application {
    @Override
    public void start(Stage stage) {{

        double[] balance = {1000};
        AtomicInteger history = new AtomicInteger();


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        TextInputDialog depositInput = new TextInputDialog("");
        depositInput.setHeaderText("Deposit enter: ");
        depositInput.setContentText("Deposit amount: ");

        TextInputDialog WithDrawInput = new TextInputDialog("");
        WithDrawInput.setHeaderText("Withdraw enter: ");
        WithDrawInput.setContentText("Withdraw amount: ");


        Button CheckBalance = new Button("Check Balance");
        Button Deposit = new Button("Deposit");
        Button Withdraw = new Button("Withdraw");
        Button Exit = new Button("Exit");

        Text historyInfo = new Text("History info: ");
        Label InfoHistory = new Label("...");
        Label InfoHistory1 = new Label("..");
        Label InfoHistory2 = new Label(".");

        CheckBalance.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.CLOSE);
            alert.setContentText("Balance: " + balance[0]);
            alert.show();
        });
        Deposit.setOnAction(event -> {
            Optional<String> result = depositInput.showAndWait();
            if (result.isPresent() == true) {
                try {
                    double amountd = Double.parseDouble(result.get());

                    if (amountd > 0) {
                        balance[0] += amountd;
                        depositInput.getEditor().clear();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Success deposit amount: " + amountd);

                        if (history.get() == 0){
                            InfoHistory.setText("Deposited: " + result.get());
                            history.getAndIncrement();
                        }else if (history.get() == 1){
                            InfoHistory1.setText("Deposited: " + result.get());
                            history.getAndIncrement();
                        }else {
                            InfoHistory2.setText("Deposited: " + result.get());
                            history.set(0);
                        }

                        alert.show();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Error deposit!");
                        alert.show();
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Number is incorrect!");
                    alert.show();
                }
            }
        });
        Withdraw.setOnAction(event -> {
            Optional<String> result = WithDrawInput.showAndWait();
            if (result.isPresent() == true) {
                try {
                    double amountd = Double.parseDouble(result.get());

                    if (amountd > 0 && balance[0] >= amountd) {
                        balance[0] -= amountd;
                        WithDrawInput.getEditor().clear();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Success withdraw : " + amountd);

                        if (history.get() == 0){
                            InfoHistory.setText("Withdrawing: " + result.get());
                            history.getAndIncrement();
                        }else if (history.get() == 1){
                            InfoHistory1.setText("Withdrawing: " + result.get());
                            history.getAndIncrement();
                        }else {
                            InfoHistory2.setText("Withdrawing: " + result.get());
                            history.set(0);
                        }

                        alert.show();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Error withdraw");
                        alert.show();
                    }


                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Number is incorrect!");
                    alert.show();
                }
            }
        });

        Exit.setOnAction(event -> {
            stage.close();
        });

        vbox.getChildren().addAll(CheckBalance, Deposit, Withdraw, Exit,historyInfo, InfoHistory,InfoHistory1,InfoHistory2);

        Scene scene = new Scene(vbox, 500, 500);

        stage.setTitle("Mini ATM Simulator");
        stage.setScene(scene);
        stage.show();
    }}}
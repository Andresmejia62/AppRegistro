package com.appregistros.appregistro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/com/appregistros/appregistro/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 320);
        stage.setTitle("Sistema de Registro");
        stage.setScene(scene);
        stage.setMinWidth(420);
        stage.setMinHeight(320);
        stage.show();
    }
}

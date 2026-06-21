package com.quiosque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//Aplicação JavaFX principal
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stageCliente) throws IOException {
        // Tela de autoatendimento do cliente
        Scene cenaCliente = new Scene(loadFXML("primary"), 900, 800);
        stageCliente.setScene(cenaCliente);
        stageCliente.setTitle("Brother's Burger - Autoatendimento");
        stageCliente.setResizable(false);
        stageCliente.setOnCloseRequest(e -> {
        javafx.application.Platform.exit();
        System.exit(0);
});
        stageCliente.show();

        // Tela de gerência
        Stage stageGerencia = new Stage();
        Scene cenaGerencia = new Scene(loadFXML("secondary"), 600, 500);
        stageGerencia.setScene(cenaGerencia);
        stageGerencia.setTitle("Brother's Burger - Painel da Gerência");
        stageGerencia.setOnCloseRequest(e -> {
            javafx.application.Platform.exit();
            System.exit(0);
        });
        stageGerencia.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
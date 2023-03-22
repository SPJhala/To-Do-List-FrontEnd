package com.mainframe.todolistfrontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AddActivityUI {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField textField;
    public void addActivity(ActionEvent actionEvent) throws IOException {
        URL url = new URL("http://localhost:8080/activities");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);


        Activity activity = new Activity(textField.getText());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(activity);

        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeBytes(json);

        System.out.println(connection.getResponseCode());
        System.out.println(json);
        System.out.println(activity);
        textField.setText("");
    }
    public void onClickHomeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("ui-design.fxml"));
        root = fxmlLoader.load();
        ToDoListUI toDoListUI = fxmlLoader.getController();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

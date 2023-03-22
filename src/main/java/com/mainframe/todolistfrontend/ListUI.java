package com.mainframe.todolistfrontend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListUI {

    @FXML
    ListView listContainer;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void displayList() throws IOException {
        URL url = new URL("http://localhost:8080/activities");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            String response = responseBuilder.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            List<ActivityToDisplay> activities = objectMapper.readValue(response, new TypeReference<List<ActivityToDisplay>>() {});
            for(ActivityToDisplay activity : activities){
                String item = activity.id + " - " + activity.activity_name;
                listContainer.getItems().add(item);
            }
        }
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

import af.albertsoft.elevator.system.AllAboveSystem;
import af.albertsoft.elevator.system.ElevatorSystem;
import af.albertsoft.elevator.system.SystemData;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class FXMLLoaderApp extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/elevatorui.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SystemData data = SystemPrototypeTest.getData("systemconfig/system1.sys.xml");
        ElevatorSystem system = new ElevatorSystem(data);
        AllAboveSystem.initSystem(system);
        launch();
    }    
}

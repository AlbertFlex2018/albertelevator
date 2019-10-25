 import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
public class ElevatorUI extends Application {

    @Override
    public void start(Stage stage) {

        VBox allbox = new VBox();
        MenuBar bar = createMenuBar();
        allbox.getChildren().add(bar);
        HBox hbox = new HBox();
        
        hbox.setAlignment(Pos.CENTER);
        VBox b1 = new VBox();
        b1.setMinWidth(150);
        hbox.getChildren().addAll(b1);
        b1.getChildren().add(new Text("Text1"));

        VBox b2 = new VBox();
        b2.setMinWidth(200);
        hbox.getChildren().addAll(b2);
        b2.getChildren().add(new Text("Text2"));

        VBox b3 = new VBox();
        b3.setMinWidth(150);
        hbox.getChildren().addAll(b3);
        b3.getChildren().add(new Text("Text3"));
        
        allbox.getChildren().add(hbox);
        Scene scene = new Scene(allbox, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Albert - Elevator");
        stage.show();
    }
    
    private MenuBar createMenuBar(){
        MenuBar bar = new MenuBar();

        Menu menuSystem=new Menu("System");
        MenuItem newsystem=new MenuItem("New");
        newsystem.setOnAction(e->{
            System.out.println("Click new System.");
        });
        
        MenuItem opensystem=new MenuItem("Open");
        opensystem.setOnAction(e->{
            System.out.println("Click open System.");
        });        
        MenuItem savesystem=new MenuItem("Save");
        savesystem.setOnAction(e->{
            System.out.println("Click save System.");
        });                
        menuSystem.getItems().addAll(newsystem,opensystem,savesystem);
        
        Menu menuEdit=new Menu("Edit");
        Menu addedit=new Menu("Add");
        MenuItem elevatoradd=new MenuItem("Elevator");
        elevatoradd.setOnAction(e->{
            System.out.println("Click Elevator Add.");
        });        
        MenuItem buildingadd=new MenuItem("Building");
        buildingadd.setOnAction(e->{
            System.out.println("Click Building Add.");
        });        
        MenuItem adminadd=new MenuItem("Admin");
        adminadd.setOnAction(e->{
            System.out.println("Click Admin Add.");
        });        
        
        addedit.getItems().addAll(elevatoradd,buildingadd,adminadd);

        Menu modifyedit=new Menu("Modify");
        MenuItem elevatormodify=new MenuItem("Elevator");
        elevatormodify.setOnAction(e->{
            System.out.println("Click Elevator Modify.");
        });        
        MenuItem buildingmodify=new MenuItem("Building");
        buildingmodify.setOnAction(e->{
            System.out.println("Click Building Modify.");
        });        

        MenuItem adminmodify=new MenuItem("Admin");
        adminmodify.setOnAction(e->{
            System.out.println("Click Admin Modify.");
        });        
        modifyedit.getItems().addAll(elevatormodify,buildingmodify,adminmodify);
        
        
        Menu removeedit=new Menu("Remove");
        MenuItem elevatorremove=new MenuItem("Elevator");
        elevatorremove.setOnAction(e->{
            System.out.println("Click Elevator remove.");
        });        
        MenuItem buildingremove=new MenuItem("Building");
        buildingremove.setOnAction(e->{
            System.out.println("Click Building Modify.");
        });        
        MenuItem adminremove=new MenuItem("Admin");
        removeedit.getItems().addAll(elevatorremove,buildingremove,adminremove);
        adminremove.setOnAction(e->{
            System.out.println("Click Admin Rmove.");
        });        
        
        
        menuEdit.getItems().addAll(addedit,modifyedit,removeedit);
        
        Menu menuHelp = new Menu("Help");
        menuHelp.setOnAction(e->{
            System.out.println("Click Help.");
        });

        Menu menuAbout =new Menu("About");
        MenuItem aboutauthor=new MenuItem("Author");
        aboutauthor.setOnAction(e->{
            System.out.println("Click About Author.");
        });
        MenuItem aboutotherwork=new MenuItem("Other works");
        aboutotherwork.setOnAction(e->{
            System.out.println("Click About Author.");
        });
        menuAbout.getItems().addAll(aboutauthor,aboutotherwork);
        
        bar.getMenus().addAll(menuSystem,menuEdit,menuHelp,menuAbout);
        return bar;
    }
    public static void main(String[] args) {
        launch();
    }
    
}

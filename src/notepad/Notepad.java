/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author WAGDY 8.1
 */
public class Notepad extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      
        TextArea txt=new TextArea();
        //file menue with its item
        Menu file=new Menu("file");
        MenuItem newitem=new MenuItem("new");
        newitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               txt.clear();
            }
        });
         MenuItem openitem=new MenuItem("open");
         openitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    FileChooser fil=new FileChooser();
                    fil.setTitle("open resource file");
                    File file= fil.showOpenDialog(primaryStage);
                    FileReader filereader = new FileReader(file);
                    
                    BufferedReader buff=new BufferedReader(filereader);
                    
                    while(buff.read()!=-1)
                    {
                       String s= buff.readLine();
                       txt.appendText(s);
                        
                    }
                    buff.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                } 
                   
                
            }
        });
          MenuItem saveitem=new MenuItem("save");
          
          saveitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileChooser fil=new FileChooser();
                    fil.setTitle("open resource file");
                    File f= fil.showOpenDialog(primaryStage);
                    //File f=new File("src/notepad/amir.txt");
                    FileWriter m=new FileWriter(f);
                       String s=txt.getText();
                BufferedWriter buff=new BufferedWriter(m);
                buff.write(s);
                buff.close();
                } catch (IOException ex) {
                    Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              
              
            }
        });
        
         
           MenuItem exititem=new MenuItem("exit");
           exititem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              primaryStage.close(); //To change body of generated methods, choose Tools | Templates.
            }
        });
           
           file.getItems().addAll(newitem,openitem,saveitem,exititem);
           //edit menue whith items
           Menu edit=new Menu("edit");
           //edit items
        MenuItem deleteitem=new MenuItem("delete");
        deleteitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              txt.replaceSelection("");
            }
        });
         MenuItem cutitem=new MenuItem("cut");
         cutitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               txt.cut();
            }
        });
          MenuItem copyitem=new MenuItem("copy");
          copyitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txt.copy();
            }
        });
           MenuItem pasteitem=new MenuItem("paste");
           pasteitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txt.paste();
            }
        });
           MenuItem undoitem=new MenuItem("undo");
           undoitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txt.undo();
            }
        });

           edit.getItems().addAll(deleteitem,cutitem,copyitem,pasteitem,undoitem);
           
        Menu help=new Menu("help");
        MenuBar bar=new MenuBar();
        bar.getMenus().addAll(file,edit,help);
        BorderPane pane=new BorderPane();
        pane.setTop(bar);
        pane.setCenter(txt);
        Scene scene=new Scene(pane, 300, 400);
                
                
        
        //root.getChildren().add();
        
        //Scene scene = new Scene(, 300, 250);
        
      //primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

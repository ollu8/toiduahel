// Proovin
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import java.util.*;
import javafx.animation.*;
import javafx.util.Duration;
public class Aken extends Application{
    int hunte=5;
    int janeseid=30;
    TextField tfHunte=new TextField(String.valueOf(hunte));
    TextField tfJaneseid=new TextField(String.valueOf(janeseid));
    Button nupp1=new Button("1 päev");
    Button salvestusnupp=new Button("Salvesta");
    Button lugemisnupp=new Button("Loe");
    Timeline ajatiksuja=new Timeline(new KeyFrame(Duration.seconds(2), (event)->arvutaPaev()));
    public void start(Stage stage){
       VBox vb=new VBox();
       HBox hb=new HBox();
       hb.getChildren().addAll(tfHunte, tfJaneseid);
       HBox nupud=new HBox();
       nupud.getChildren().addAll(nupp1, salvestusnupp, lugemisnupp);
       vb.getChildren().addAll(hb, nupud);
       Group juur=new Group(vb);
       stage.setScene(new Scene(juur));
       stage.show();
       salvestusnupp.setOnAction((event) -> salvesta());       
       lugemisnupp.setOnAction((event) -> loe());
       nupp1.setOnAction((event) -> arvutaPaev());    
       ajatiksuja.setCycleCount(Timeline.INDEFINITE);
       ajatiksuja.play();       
    }
    
    void arvutaPaev(){
       tekstistMuutujatesse();
       if(janeseid>=hunte){janeseid-=hunte;}
       muutujatestTekstivaljadesse();       
    }
    void tekstistMuutujatesse(){
       hunte=Integer.parseInt(tfHunte.getText());
       janeseid=Integer.parseInt(tfJaneseid.getText());
    }
    void muutujatestTekstivaljadesse(){
       tfHunte.setText(String.valueOf(hunte));
       tfJaneseid.setText(String.valueOf(janeseid));
    }
    void salvesta(){
       try{
         tekstistMuutujatesse();
         Properties prop=new Properties();
         prop.put("hunte", String.valueOf(hunte));
         prop.put("janeseid", String.valueOf(janeseid));
         FileOutputStream fos=new FileOutputStream("andmed.txt");
         prop.store(fos, "Metsaandmed");
         fos.close();
       }catch(IOException ex){
         ex.printStackTrace();
       }
    }
    void loe(){
       try{
         Properties prop=new Properties();
         prop.load(new FileInputStream("andmed.txt"));
         hunte=Integer.parseInt(prop.getProperty("hunte"));
         janeseid=Integer.parseInt(prop.getProperty("janeseid"));
         muutujatestTekstivaljadesse();
       }catch(IOException ex){ex.printStackTrace();}
    }
}
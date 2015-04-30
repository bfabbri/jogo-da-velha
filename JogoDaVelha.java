import javafx.application.*;

import javafx.event.*;

import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import javafx.stage.*;

import java.net.*;

import java.io.*;

import java.lang.*;
  
public class JogoDaVelha extends Application {
    public static void main(String[] args) throws UnknownHostException, IOException
    {
        launch();
    }

    public class Posicao
    {
        int lin;
        int col;

        public Posicao(double x, double y)
        {
            this.col = (int)Math.ceil(x/200);
            this.lin = (int)Math.ceil(y/200);
        }
    }

    public void styleIt(Scene scene)
    {
        File f = new File("test.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }
     
    public void start(Stage primaryStage) 
    {

        class Table implements EventHandler<ActionEvent>
        {
            public void handle(ActionEvent event)
            {
                Scene telaTable = new Scene(
                    new VBox(
                        new ImageView(
                            new Image("table.png")
                        )
                    ),
                600, 600);

                telaTable.setOnMousePressed(eventMousePress -> {
                    Posicao p = new Posicao(eventMousePress.getX(), eventMousePress.getY());
                    //StackPane stackPane = new StackPane(new ImageView(new Image("x.png")));
                    System.out.println("X: " + p.lin + " Y: " + p.col);
                });

                primaryStage.setScene(telaTable);
                primaryStage.show();
            }
        }

        primaryStage.setTitle("Jogo da Velha");

        // Botao para ser host
        Button host = new Button("Create");
		host.setOnAction(eventCreate ->
		{
            Button iniciar = new Button("Begin Game!");
            iniciar.setOnAction(new Table());       // Tela jogo

            Scene telaInicioHost = new Scene(
                new VBox(
                    new Text("Digite a porta"), 
                    new TextField(), 
                    iniciar
                ), 
            600, 600);

            primaryStage.setScene(telaInicioHost);
            primaryStage.show();
            styleIt(telaInicioHost);
		});
		
        // Botao para ser client
		Button client = new Button("Join!");
		client.setOnAction(event ->
		{
            Button connect = new Button("Connect!");
            connect.setOnAction(new Table());       // Tela jogo

            Scene telaInicioClient = new Scene(
                new VBox(
                    new Text("Digite o IP do host:"), 
                    new TextField(), 
                    new Text("Digite a porta do host:"), 
                    new TextField(), 
                    connect
                ), 
            600, 600);

            primaryStage.setScene(telaInicioClient);
            primaryStage.show();
            styleIt(telaInicioClient);
		});

        // Tela inicial a
        Scene scene = new Scene(
            new VBox(
                new HBox(
                    new Text("Jogo da Velha")
                ), 
                new HBox(
                    host, 
                    client
                )
            ), 
        600, 600);
        

        primaryStage.setScene(scene);
        primaryStage.show();
        styleIt(scene);
    }
}
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private HBox hBox;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private VBox barraLateral;
    private TextField txtAncho, txtAlto;
    private Button btnGenerar, btnSolucionar;
    private Label lblInfo, lblTiempo;

    private Laberintos laberinto;
    private Resolvedor solucion;
    private Dibujo dibujo;

    @Override
    public void start(Stage primaryStage) throws Exception{
        CrearUI();
        primaryStage.setTitle("Laberinto");
        primaryStage.setScene(new Scene(hBox, 1000,600));
        primaryStage.show();
    }

    private void CrearUI() {
        hBox = new HBox();
        canvas = new Canvas(800, 800);
        graphicsContext = canvas.getGraphicsContext2D();
        barraLateral = new VBox();
        txtAncho = new TextField();
        txtAlto = new TextField();
        btnGenerar = new Button("Generar");
        btnSolucionar = new Button("Solucionar");
        lblInfo = new Label("Se tardo en resolver:");
        lblTiempo = new Label("00:00");

        barraLateral.getChildren().addAll(txtAncho, txtAlto, btnGenerar, btnSolucionar, lblInfo, lblTiempo);
        hBox.getChildren().addAll(canvas, barraLateral);
        //btnSolucionar.setOnAction(laberinto.resolverLaberinto());

        btnGenerar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!(txtAncho.getText().equals("") || txtAlto.getText().equals(""))){
                    int ancho, alto;
                    ancho = Integer.parseInt(txtAncho.getText());
                    alto = Integer.parseInt(txtAlto.getText());
                    laberinto = new Laberintos(ancho, alto);

                    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    dibujo = new Dibujo(graphicsContext, laberinto);
                    dibujo.dibujarLaberinto();
                }
            }
        });

        btnSolucionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                solucion = new Resolvedor(laberinto);

                dibujo.dibujarSolucion(solucion.camino);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package sample;

import javafx.application.Application;
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
    private Laberintos laberinto;
    private HBox hBox;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private VBox barraLateral;
    private TextField txtAncho, txtAlto;
    private Button btnGenerar, btnSolucionar;
    private Label lblInfo, lblTiempo;

    private Dibujo dibujo;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        CrearUI();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(hBox, 700, 500));
        primaryStage.show();

        laberinto = new Laberintos(30, 30);
        dibujo = new Dibujo(graphicsContext, laberinto);

        dibujo.dibujarLaberinto();
    }

    private void CrearUI() {
        hBox = new HBox();
        canvas = new Canvas(500, 500);
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

    }

    public static void main(String[] args) {
        launch(args);
    }
}

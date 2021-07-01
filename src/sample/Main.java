package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Label lblInfo, lblTiempoSol,lblTiempoGen,lblInfo2,lblAncho,lblAlto,lblEspacio;

    private Laberintos laberinto;
    private Resolvedor solucion;
    private Dibujo dibujo;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        CrearUI();
        primaryStage.setTitle("Laberinto");
        scene = new Scene(hBox, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void CrearUI() {
        hBox = new HBox();
        hBox.setStyle("-fx-background-color: #525252;");
        canvas = new Canvas(800, 800);
        graphicsContext = canvas.getGraphicsContext2D();
        barraLateral = new VBox();
        lblEspacio=new Label();
        lblAncho=new Label("Ancho: ");
        lblAncho.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        txtAncho = new TextField();
        lblAlto=new Label("Alto: ");
        lblAlto.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        txtAncho.setStyle("-fx-background-color: #444444;\n" +
        "    -fx-border-width: 1px;\n" +
                "    -fx-border-color: blue;\n" +
                "    -fx-border-radius: 50px;\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-padding: 0px;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-family: \"Open Sans\",\"Arial\",\"SansSerif\";\n" +
                "    -fx-text-fill: white;");
        barraLateral.setStyle("-fx-background-color: #444444;");
        txtAlto = new TextField();
        txtAlto.setStyle("-fx-background-color: #444444;\n" +
                "    -fx-border-width: 1px;\n" +
                "    -fx-border-color: blue;\n" +
                "    -fx-border-radius: 50px;\n" +
                "    -fx-font-size: 15px;\n" +
                "    -fx-padding: 0px;\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-font-family: \"Open Sans\",\"Arial\",\"SansSerif\";\n" +
                "    -fx-text-fill: white;");
        barraLateral.setStyle("-fx-background-color: #444444;");
        btnGenerar = new Button("Generar");
        btnGenerar.setStyle("-fx-padding: 5 22 5 22;\n" +
                "    -fx-border-color: blue;\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #444444;\n" +
                "    -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n" +
                "    -fx-font-size: 11pt;\n" +
                "    -fx-text-fill: #d8d8d8;\n" +
                "    -fx-background-insets: 0 0 0 0, 0, 1, 2;\n" +
                "    -fx-border-radius: 10px 10px 10px 10px;");
        btnSolucionar = new Button("Solucionar");
        btnSolucionar.setStyle("-fx-padding: 5 22 5 22;\n" +
                "    -fx-border-color: blue;\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: #444444;\n" +
                "    -fx-font-family: \"Segoe UI\", Helvetica, Arial, sans-serif;\n" +
                "    -fx-font-size: 11pt;\n" +
                "    -fx-text-fill: #d8d8d8;\n" +
                "    -fx-background-insets: 0 0 0 0, 0, 1, 2;\n" +
                "    -fx-border-radius: 10px 10px 10px 10px;");
        lblInfo = new Label("Se tardo en Generar:");
        lblInfo.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        lblTiempoGen=new Label("00:00");
        lblTiempoGen.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        lblInfo2=new Label("Se tardo en resolver");
        lblInfo2.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        lblTiempoSol = new Label("00:00");
        lblTiempoSol.setStyle(" -fx-font-size: 11pt;\n" +
                "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-opacity: 1;");
        txtAncho.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    txtAncho.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtAlto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    txtAlto.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });
        barraLateral.getChildren().addAll(lblAncho,txtAncho,lblAlto, txtAlto, btnGenerar,lblEspacio, btnSolucionar, lblInfo, lblTiempoGen,lblInfo2,lblTiempoSol);
        hBox.getChildren().addAll(canvas, barraLateral);
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
                    lblTiempoGen.setText((laberinto.tiempo+" milisegundos"));
                }
            }
        });

        btnSolucionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                solucion = new Resolvedor(laberinto);
                dibujo.dibujarSolucion(solucion.camino);
                lblTiempoSol.setText(solucion.tiempo+" milisegundos" );
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
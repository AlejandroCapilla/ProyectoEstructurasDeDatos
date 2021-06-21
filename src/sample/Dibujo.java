package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Dibujo {
    GraphicsContext gc;
    Laberintos laberintos;

    Dibujo(GraphicsContext gc, Laberintos laberintos) {
        this.gc = gc;
        this.laberintos = laberintos;
        gc.setFill(Color.DARKCYAN);
        gc.fill();

    }

    void dibujarLaberinto() {
        Byte[] celda = new Byte[2];
        celda[0] = 0;
        celda[1] = 0;
        dibujarDFS(celda);
    }

    private void dibujarDFS(Byte[] celdaActual) {
        String c = laberintos.laberinto[celdaActual[0]][celdaActual[1]].celdasConectadas;

        dibujarCelda(celdaActual, 8,8);

        for (int i = 0; i < c.length(); i++) {
            Byte[] celdaSiguiente = new Byte[2];
            char dir = c.charAt(i);

            switch (dir) {
                case '1':
                    celdaSiguiente[0] = (byte)(celdaActual[0] - 1);
                    celdaSiguiente[1] = celdaActual[1];

                    dibujarCelda(celdaActual, '1');
                    dibujarDFS(celdaSiguiente);
                    break;
                case '2':
                    celdaSiguiente[0] = celdaActual[0];
                    celdaSiguiente[1] = (byte)(celdaActual[1] + 1);

                    dibujarCelda(celdaActual, '2');
                    dibujarDFS(celdaSiguiente);
                    break;
                case '3':
                    celdaSiguiente[0] = (byte)(celdaActual[0] + 1);
                    celdaSiguiente[1] = celdaActual[1];

                    dibujarCelda(celdaActual, '3');
                    dibujarDFS(celdaSiguiente);
                    break;
                case  '4':
                    celdaSiguiente[0] = celdaActual[0];
                    celdaSiguiente[1] = (byte)(celdaActual[1] - 1);

                    dibujarCelda(celdaActual, '4');
                    dibujarDFS(celdaSiguiente);
                    break;
            }
        }

    }

    private void dibujarCelda(Byte[] celda, int ancho, int alto) {
        byte x, y;
        x = celda[0];
        y = celda[1];

        gc.fillRect((x*10)+1,(y*10)+1, ancho, alto);
    }

    private void dibujarCelda(Byte[] celda, char dir) {
        byte x, y;
        x = celda[0];
        y = celda[1];

        switch (dir) {
            case '1':
                gc.fillRect((x*10)-1,(y*10)+1, 2, 8);
                break;
            case '2':
                gc.fillRect((x*10)+1,(y*10)+9, 8, 2);
                break;
            case '3':
                gc.fillRect((x*10)+9,(y*10)+1, 2, 8);
                break;
            case '4':
                gc.fillRect((x*10)+1,(y*10)-1, 8, 2);
                break;
        }
    }

}
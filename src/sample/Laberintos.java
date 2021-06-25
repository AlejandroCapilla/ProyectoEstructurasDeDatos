package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Laberintos {
    celdaLaverinto[][] laberinto;
    private char[][] lab;
    private byte[] celdainicial = new byte[2];
    private boolean estadoEntrada;
    private boolean estadoSalida;
    private int entradaI;
    private int entradaJ;
    private int salidaI;
    private int salidaJ;
    GraphicsContext gc;


    Laberintos(int ancho, int alto) {
        laberinto = new celdaLaverinto[ancho][alto];
        celdainicial[0] = 0;
        celdainicial[1] = 0;
        instanciarLaberinto(ancho, alto);
        DFSRandomizado(celdainicial);
        lab= new char[ancho][alto];
        //InstaLab(ancho,alto);
    }
    private void instanciarLaberinto(int ancho, int alto) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                celdaLaverinto celda = new celdaLaverinto();
                laberinto[i][j] = celda;
            }
        }
    }
    /*private void InstaLab(int ancho,int alto){
        lab = new char[lab.length][lab[0].length];
        for (int i = 0; i < lab.length; i++)
        {
            for (int j = 0; j < lab.length; j++)
            {
                lab[i][j] = lab[i][j];
            }
        }
    }*/

    private void DFSRandomizado(byte[] celda) {
        laberinto[celda[0]][celda[1]].marcarVisitada();
        byte[] celdaSiguiente = celdaSiguienteAleatoria(celda);

        while (celdaSiguiente != null) {
            //laberinto[celda[0]][celda[1]].conectarCeldas(celdaSiguiente);
            DFSRandomizado(celdaSiguiente);
            celdaSiguiente = celdaSiguienteAleatoria(celda);
        }
        return;
    }

    private byte[] celdaSiguienteAleatoria(byte[] celdaActual) {
        byte[] celdaSiguiente = new byte[2];
        boolean bandera = false;
        String intentos = "";

        do {
            int direccionAleatoria = (int)(Math.random()*4)+1;// 1=izquierda, 2=arriba, 3=derecha, 4=abajo

            if(intentos.length() < 4) {
                if (!(intentos.contains(String.valueOf(direccionAleatoria)))) {
                    intentos = intentos + direccionAleatoria;

                    switch (direccionAleatoria){
                        case 1:
                            if(celdaActual[0] - 1 >= 0) {
                                if(!(laberinto[celdaActual[0] - 1][celdaActual[1]].visitada)){
                                    celdaSiguiente[0] = (byte)(celdaActual[0] - 1);
                                    celdaSiguiente[1] = celdaActual[1];
                                    laberinto[celdaActual[0]][celdaActual[1]].conectarCeldas("1");
                                    bandera = true;
                                }
                            }
                            break;

                        case 2:
                            if(celdaActual[1] + 1 < laberinto[0].length) {
                                if(!(laberinto[celdaActual[0]][celdaActual[1] + 1].visitada)){
                                    celdaSiguiente[0] = celdaActual[0];
                                    celdaSiguiente[1] = (byte)(celdaActual[1] + 1);
                                    laberinto[celdaActual[0]][celdaActual[1]].conectarCeldas("2");
                                    bandera = true;
                                }
                            }
                            break;

                        case 3:
                            if(celdaActual[0] + 1 < laberinto.length) {
                                if(!(laberinto[celdaActual[0] + 1][celdaActual[1]].visitada)){
                                    celdaSiguiente[0] = (byte)(celdaActual[0] + 1);
                                    celdaSiguiente[1] = celdaActual[1];
                                    laberinto[celdaActual[0]][celdaActual[1]].conectarCeldas("3");
                                    bandera = true;
                                }
                            }
                            break;

                        case 4:
                            if(celdaActual[1] - 1 >= 0) {
                                if(!(laberinto[celdaActual[0]][celdaActual[1] - 1].visitada)){
                                    celdaSiguiente[0] = celdaActual[0];
                                    celdaSiguiente[1] = (byte)(celdaActual[1] - 1);
                                    laberinto[celdaActual[0]][celdaActual[1]].conectarCeldas("4");
                                    bandera = true;
                                }
                            }
                    }
                }
            }else{
                return null;
            }
        }while(!bandera);

        return celdaSiguiente;
    }
    public char[][] resolver()
    {
        lab[salidaI][salidaJ] = 'X';
        if (paso(entradaI, entradaJ)) {
            lab[entradaI][entradaJ] = 'S';
        }
        return lab;
    }

    private boolean paso(int x, int y) {

        if (lab[x][y] == 'X') {
            return true;
        }

        if (lab[x][y] == '#' || lab[x][y] == '*') {
            return false;
        }


        lab[x][y] = '*';

        boolean result;

        result = paso(x - 1, y);
        if (result) {
            return true;
        }
        result = paso(x, y + 1);

        if (result) {
            return true;
        }
        result = paso(x, y - 1);

        if (result) {
            return true;
        }
        result = paso(x + 1, y);

        if (result) {
            return true;
        }
        lab[x][y] = '+';
        return true;

    }
    public EventHandler<ActionEvent> resolverLaberinto()
    {
        lab = resolver();
        pintarSolucion();
        return null;
    }

    public void pintarSolucion()
    {
        for (int i = 0; i < laberinto.length; i++)
        {
            for (int j = 0; j < laberinto[0].length; j++)
            {
                if (lab[i][j]=='*')
                {
                    gc.setFill(Color.GREEN);
                    gc.fill();
                }
            }
        }
    }


    public boolean isEstadoEntrada() {
        return estadoEntrada;
    }
    public void setEstadoEntrada(boolean estadoEntrada) {
        this.estadoEntrada = estadoEntrada;
    }
    public boolean isEstadoSalida() {
        return estadoSalida;
    }
    public void setEstadoSalida(boolean estadoSalida) {
        this.estadoSalida = estadoSalida;
    }
    public char[][] getLaberinto() {
        return lab;
    }
    public void setLaberinto(char[][] laberinto) {
        this.lab = laberinto;
    }
    public int getEntradaI() {
        return entradaI;
    }
    public void setEntradaI(int entradaI) {
        this.entradaI = entradaI;
    }
    public int getEntradaJ() {
        return entradaJ;
    }
    public void setEntradaJ(int entradaJ) {
        this.entradaJ = entradaJ;
    }
    public int getSalidaI() {
        return salidaI;
    }
    public void setSalidaI(int salidaI) {
        this.salidaI = salidaI;
    }
    public int getSalidaJ() {
        return salidaJ;
    }
    public void setSalidaJ(int salidaJ) {
        this.salidaJ = salidaJ;
    }

    public int obtenerlongitudI()
    {
        return laberinto.length;
    }

    public int obtenerLongitudJ()
    {
        return laberinto[0].length;
    }

    public class celdaLaverinto {
        boolean visitada;
        String celdasConectadas = "";

        void marcarVisitada() {
            visitada = true;
        }

        void conectarCeldas(String dirCeldaSiguiente) {
            celdasConectadas = celdasConectadas + dirCeldaSiguiente;
        }
    }
}
package sample;

public class Resolvedor {
    private boolean estadoEntrada;
    private boolean estadoSalida;
    private char[][] laberinto;
    private int entradaI;
    private int entradaJ;
    private int salidaI;
    private int salidaJ;


    public Resolvedor()
    {
        laberintoBase();
    }

    public void laberintoBase()
    {
        Laberintos lab=new Laberintos(30,30);
        char[][] laberintoBase=lab.getLaberinto();
        laberinto = new char[laberintoBase.length][laberintoBase[0].length];
        for (int i = 0; i < laberintoBase.length; i++)
        {
            for (int j = 0; j < laberintoBase.length; j++)
            {
                laberinto[i][j] = laberintoBase[i][j];
            }
        }
    }


    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public char[][] resolver()
    {
        laberinto[salidaI][salidaJ] = 'X';// permite introducir unas coordenadas (x, y)
        if (paso(entradaI, entradaJ)) { 								// intentará resolver el laberinto en estas coordenadas
            laberinto[entradaI][entradaJ] = 'S'; 						// introduce en las coordenadas (x, y) la entrada
        }
        return laberinto;
    }

    private boolean paso(int x, int y)
    {

        if (laberinto[x][y]=='X'){ // si hemos llegado a X quiere decir que hemos encontrado solución
            return true; // luego, el algoritmo termina
        }

        if (laberinto[x][y]=='#'||laberinto[x][y]=='*') { // si llegamos a una pared o al mismo punto,
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        // si no se cumple ninguna de estas dos situaciones, quiere decir que nos encontramos en un
        // caso intermedio, por lo tanto, que empezamos a recorrer o todavía no hemos llegado a nada
        laberinto[x][y]='*'; // marcamos la posición como visitada (si es la primera, en las coordenadas x e y

        boolean result; // se coloca S de START)

        result=paso(x-1, y); // intentamos ir hacia ARRIBA. Primera llamada recursiva

        if (result)
        {
            return true; // si el resultado es el final, entonces el algoritmo termina
        }
        result=paso(x, y+1); // intentamos ir hacia la DERECHA. Segunda llamada recursiva

        if (result)
        {
            return true; // si el resultado es el final, entonces el algoritmo termina
        }
        result=paso(x, y-1); // intentamos ir hacia la IZQUIERDA. Tercera llamada recursiva

        if (result)
        {
            return true; // si el resultado es el final, entonces el algoritmo termina
        }
        result=paso(x+1, y); // intentamos ir hacia ABAJO. Cuarta llamada recursiva

        if (result)
        {
            return true; // si el resultado es el final, entonces el algoritmo termina
        }
        // si no hemos encontrado la solución en estos cuatros movimientos, volvemos atrás, aunque hay que
        // considerar que en este punto, todas las llamadas recursivas han finalizado. Si en ninguna de ellas
        // se ha conseguido el éxito, entonces el algoritmo termina y devuelve false.
        laberinto[x][y]='+'; // en el caso de no ser el resultado, se marca con +. Ya fue pisado
        return true; // vuelta atrás si la solución no se encuentra aquí
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
        return laberinto;
    }
    public void setLaberinto(char[][] laberinto) {
        this.laberinto = laberinto;
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




}




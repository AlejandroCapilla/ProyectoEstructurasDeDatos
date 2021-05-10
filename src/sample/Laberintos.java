package sample;

public class Laberintos {
    celdaLaverinto[][] laberinto;
    byte[] celdainicial = new byte[2];

    Laberintos(int ancho, int alto) {
        laberinto = new celdaLaverinto[ancho][alto];
        celdainicial[0] = 0;
        celdainicial[1] = 0;
        instanciarLaberinto(ancho, alto);
        DFSRandomizado(celdainicial);
    }

    void instanciarLaberinto(int ancho, int alto) {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                celdaLaverinto celda = new celdaLaverinto();
                laberinto[i][j] = celda;
            }
        }
    }

    void DFSRandomizado(byte[] celda) {
        laberinto[celda[0]][celda[1]].marcarVisitada();
        byte[] celdaSiguiente = celdaSiguienteAleatoria(celda);

        while (celdaSiguiente != null) {
            //laberinto[celda[0]][celda[1]].conectarCeldas(celdaSiguiente);
            DFSRandomizado(celdaSiguiente);
            celdaSiguiente = celdaSiguienteAleatoria(celda);
        }
        return;
    }

    byte[] celdaSiguienteAleatoria(byte[] celdaActual) {
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
                                    laberinto[celdaActual[0] - 1][celdaActual[1]].conectarCeldas("1");
                                    bandera = true;
                                }
                            }
                            break;

                        case 2:
                            if(celdaActual[1] + 1 < laberinto[0].length) {
                                if(!(laberinto[celdaActual[0]][celdaActual[1] + 1].visitada)){
                                    celdaSiguiente[0] = celdaActual[0];
                                    celdaSiguiente[1] = (byte)(celdaActual[1] + 1);
                                    laberinto[celdaActual[0]][celdaActual[1] + 1].conectarCeldas("2");
                                    bandera = true;
                                }
                            }
                            break;

                        case 3:
                            if(celdaActual[0] + 1 < laberinto.length) {
                                if(!(laberinto[celdaActual[0] + 1][celdaActual[1]].visitada)){
                                    celdaSiguiente[0] = (byte)(celdaActual[0] + 1);
                                    celdaSiguiente[1] = celdaActual[1];
                                    laberinto[celdaActual[0] + 1][celdaActual[1]].conectarCeldas("3");
                                    bandera = true;
                                }
                            }
                            break;

                        case 4:
                            if(celdaActual[1] - 1 >= 0) {
                                if(!(laberinto[celdaActual[0]][celdaActual[1] - 1].visitada)){
                                    celdaSiguiente[0] = celdaActual[0];
                                    celdaSiguiente[1] = (byte)(celdaActual[1] - 1);
                                    laberinto[celdaActual[0]][celdaActual[1] - 1].conectarCeldas("4");
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

    public class celdaLaverinto {
        boolean visitada;
        String celdasConectadas = "";

        void marcarVisitada() {
            visitada = true;
        }

        void conectarCeldas(String dirCeldaSiguiente) {

        }
    }
}
package sample;

public class Resolvedor {
    private Laberintos laberinto;
    private byte[] celdainicial = new byte[2];
    String camino;

    public Resolvedor(Laberintos laberintos)
    {
        this.laberinto = laberintos;
        celdainicial[0] = 0;
        celdainicial[1] = 0;

        String aux = "";
        camino = resolverLaberinto(celdainicial,aux);
    }

    private String resolverLaberinto(byte[] celda, String camino) {
        laberinto.laberinto[celda[0]][celda[1]].marcarBuscada();
        
        if((celda[0] == laberinto.laberinto.length-1) && (celda[1] == laberinto.laberinto[0].length-1)) {
            return camino;
        }else {
            byte[] celdaSiguiente = new byte[2];
            if(celda[0] - 1 >= 0) {
                if(!(laberinto.laberinto[celda[0] - 1][celda[1]].buscada)){
                    if (laberinto.laberinto[celda[0]][celda[1]].celdasConectadas.contains("1")){
                        celdaSiguiente[0] = (byte)(celda[0] - 1);
                        celdaSiguiente[1] = celda[1];
                        return resolverLaberinto(celdaSiguiente,camino+"1");
                    }
                }
            }

            if(celda[1] + 1 < laberinto.laberinto[0].length) {
                if(!(laberinto.laberinto[celda[0]][celda[1] + 1].buscada)){
                    if(laberinto.laberinto[celda[0]][celda[1]].celdasConectadas.contains("2")) {
                        celdaSiguiente[0] = celda[0];
                        celdaSiguiente[1] = (byte)(celda[1] + 1);
                        return resolverLaberinto(celdaSiguiente,camino+"2");
                    }
                }
            }

            if(celda[0] + 1 < laberinto.laberinto.length) {
                if(!(laberinto.laberinto[celda[0] + 1][celda[1]].buscada)){
                    if (laberinto.laberinto[celda[0]][celda[1]].celdasConectadas.contains("3")) {
                        celdaSiguiente[0] = (byte)(celda[0] + 1);
                        celdaSiguiente[1] = celda[1];
                        return resolverLaberinto(celdaSiguiente,camino+"3");
                    }
                }
            }

            if(celda[1] - 1 >= 0) {
                if(!(laberinto.laberinto[celda[0]][celda[1] - 1].buscada)){
                    if (laberinto.laberinto[celda[0]][celda[1]].celdasConectadas.contains("4")) {
                        celdaSiguiente[0] = celda[0];
                        celdaSiguiente[1] = (byte)(celda[1] - 1);
                        return resolverLaberinto(celdaSiguiente,camino+"4");
                    }
                }
            }
        }
        return camino;
    }
}




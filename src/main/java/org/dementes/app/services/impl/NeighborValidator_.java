package org.dementes.app.services.impl;

public class NeighborValidator_ {

    public static boolean obtenerVecinos(String numeros[][], String valor, int i, int j, int posicionMatch, int contadorMatch, int minimoMatch ,int mitadArray, int size){

        //posicionMatch : representa la ubicacion (horizontal, vertical, diagonal) del match encontrado
        // 0: sin match, 1: horizontal, 2: vertical, 3: diagonal, 4: diagonal invertida
        //int valor = numeros[i][j];

        //secuencia Diagonal
        if((posicionMatch == 0 || posicionMatch == 3)){
            if((i<size && j<size)){
                if(numeros[i+1][j+1].equalsIgnoreCase(valor)) {
					contadorMatch++;
					System.out.println("Match Diagonal:" + contadorMatch);

					if (contadorMatch >= minimoMatch) {
						numeros[i][j] = "X";
						numeros[i + 1][j + 1] = "X";
						return true;
					}

					if (minimoMatch <= (mitadArray) || (j < mitadArray)) {
						valor = numeros[i + 1][j + 1];
						if (obtenerVecinos(numeros, valor, i + 1, j + 1, 3, contadorMatch, minimoMatch, mitadArray, size)) {
							numeros[i][j] = "X";
							return true;
						}
					}
				}/**/
            }
            contadorMatch = 1;
        }/**/

        //horizontal
        if((posicionMatch == 0 || posicionMatch == 1)){
            if(j<size && numeros[i][j+1].equalsIgnoreCase(valor)){

				contadorMatch++;
				System.out.println("Match Horizontal:" + contadorMatch);

				if (contadorMatch >= minimoMatch) {
					numeros[i][j] = "X";
					numeros[i][j+1] = "X";
					return true;
				}

				if(minimoMatch <= (mitadArray) || (j < mitadArray)) {
					valor = numeros[i][j + 1];
					if (obtenerVecinos(numeros, valor, i, j + 1, 1, contadorMatch, minimoMatch,mitadArray, size)) {
						numeros[i][j] ="X";
						return true;
					}
				}/**/

            }
            contadorMatch = 1;
        }/**/

        //vertical
        if((posicionMatch == 0 || posicionMatch == 2)){
            if(i<size && numeros[i+1][j].equalsIgnoreCase(valor)){

				contadorMatch++;
				System.out.println("Match Horizontal:" + contadorMatch);

				if (contadorMatch >= minimoMatch) {
					numeros[i][j] = "X";
					numeros[i+1][j] = "X";
					return true;
				}

				if(minimoMatch <= (mitadArray) || (i < mitadArray)) {
					valor = numeros[i+1][j];
					if (obtenerVecinos(numeros, valor, i+1, j, 2, contadorMatch, minimoMatch,mitadArray, size)) {
						numeros[i][j] = "X";
						return true;
					}
				}

            }
            contadorMatch = 1;
        }/**/

        //secuencia Diagonal invertida
        if((posicionMatch == 0 || posicionMatch == 4)){
            if((j<=size && i<size) && (j>0)){
                if(numeros[i+1][j-1].equalsIgnoreCase(valor)) {
                    contadorMatch++;
                    System.out.println("Match Diagonal:" + contadorMatch);

                    if (contadorMatch >= minimoMatch) {
                        numeros[i][j] = "X";
                        numeros[i + 1][j - 1] = "X";
                        return true;
                    }

                    if ((minimoMatch <= mitadArray) || (j >= mitadArray)) {
                        valor = numeros[i + 1][j - 1];
                        if (obtenerVecinos(numeros, valor, i + 1, j - 1, 4, contadorMatch, minimoMatch, mitadArray, size)) {
                            numeros[i][j] = "X";
                            return true;
                        }
                    }
                }
            }
            //contadorMatch = 1;
        }/**/
        return false;
    }
}

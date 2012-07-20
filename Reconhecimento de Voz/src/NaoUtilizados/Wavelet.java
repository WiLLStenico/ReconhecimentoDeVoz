
package NaoUtilizados;

import java.io.*;


public class Wavelet {

protected boolean running;
ByteArrayOutputStream out;


public void transformada_wavelet_nivel_k(float[] ArrayAmostra, int nivel, char ordem) {

    int TamanhoAmostra = ArrayAmostra.length;

    //----------Declaração do Array de Filtros----------------
    double[] dbFiltro = {3.32670552950e-01, 8.06891509311e-01, 4.59877502118e-01, -1.35011020010e-01, -8.54412738820e-02, 3.52262918857e-02};
    //--------------------------------------------------------

    double[] g = new double[dbFiltro.length];

    for(int i=0; i < dbFiltro.length; i++) {
       g[i] = dbFiltro[dbFiltro.length-i-1];
       if(i % 2 != 0) {
          g[i]= g[i] * (-1);
       }
    }


    int j = 0;
    double[] t = new double[TamanhoAmostra];

    //tendencia
    for(int i=0; i < TamanhoAmostra; i= i + 2) {
       t[j]=0;
       for(int k=0; k < dbFiltro.length; k++) {
          t[j] = t[j] + ArrayAmostra[(i+k) % TamanhoAmostra] * dbFiltro[k];
       }
       j++;
    }
       
    //Oscilação
    for(int i=0; i < TamanhoAmostra; i= i + 2)
    {
       t[j] = 0;
       for(int k=0; k < dbFiltro.length; k++) {
           t[j] = t[j] + ArrayAmostra[(i+k) % TamanhoAmostra] * g[k];
       }
       j++;
    }
       
    for(int i=0; i < TamanhoAmostra; i++) {
        ArrayAmostra[i] = (float) t[i];
    }

    nivel--;
    TamanhoAmostra = TamanhoAmostra / 2;

    if(nivel > 0) {
        System.out.println("chama de novo");
       transformada_wavelet_nivel_k(ArrayAmostra, nivel, ordem);
    }
 }
}



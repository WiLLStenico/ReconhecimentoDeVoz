/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.*;

public class Daubechies {
    //Conta nivel
    public int nivel = 1;
    //Fim conta nivel

    public Daubechies(){
        nivel = 1;
    }

    protected boolean running;
    ByteArrayOutputStream out;
    protected final double sqrt_3 = Math.sqrt(3);
    protected final double denom = 4 * Math.sqrt(2);
    //
    // forward transform scaling (smoothing) coefficients
    //
    protected final double h0 = (1 + sqrt_3) / denom;
    protected final double h1 = (3 + sqrt_3) / denom;
    protected final double h2 = (3 - sqrt_3) / denom;
    protected final double h3 = (1 - sqrt_3) / denom;
    //
    // forward transform wavelet coefficients
    //
    protected final double g0 = h3;
    protected final double g1 = -h2;
    protected final double g2 = h1;
    protected final double g3 = -h0;
    //
    // Inverse transform coefficients for smoothed values
    //
    protected final double Ih0 = h2;
    protected final double Ih1 = g2;  // h1
    protected final double Ih2 = h0;
    protected final double Ih3 = g0;  // h3
    //
    // Inverse transform for wavelet values
    //
    protected final double Ig0 = h3;
    protected final double Ig1 = g3;  // -h0
    protected final double Ig2 = h1;
    protected final double Ig3 = g1;  // -h2

    //Fim declarações de parametros

    public void transformada_daubechies(float[] ArrayAmostra, int n) {

        if (n >= 4) {
            int i, j;
            int half = n >> 1;

            double tmp[] = new double[n];

            i = 0;
            for (j = 0; j < n - 3; j = j + 2) {
                tmp[i] = ArrayAmostra[j] * h0 + ArrayAmostra[j + 1] * h1 + ArrayAmostra[j + 2] * h2 + ArrayAmostra[j + 3] * h3;
                tmp[i + half] = ArrayAmostra[j] * g0 + ArrayAmostra[j + 1] * g1 + ArrayAmostra[j + 2] * g2 + ArrayAmostra[j + 3] * g3;
                i++;
            }

            tmp[i] = ArrayAmostra[n - 2] * h0 + ArrayAmostra[n - 1] * h1 + ArrayAmostra[0] * h2 + ArrayAmostra[1] * h3;
            tmp[i + half] = ArrayAmostra[n - 2] * g0 + ArrayAmostra[n - 1] * g1 + ArrayAmostra[0] * g2 + ArrayAmostra[1] * g3;

            for (i = 0; i < n; i++) {
                ArrayAmostra[i] = (float) tmp[i];
            }
        }


    }


    public void transformada_daubechies_1( float[] ArrayAmostra )
   {
      final int N = ArrayAmostra.length;
      int n;
      nivel = 1;
      for (n = N; n >= 4; n >>= 1) {
         if(nivel <= 10){
           transformada_daubechies( ArrayAmostra, n );
          }
         nivel++;
      }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }



}

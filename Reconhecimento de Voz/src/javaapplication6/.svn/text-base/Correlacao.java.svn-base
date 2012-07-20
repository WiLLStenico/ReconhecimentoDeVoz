/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication6;

/**
 *
 * @author WiLL
 */
public class Correlacao {

    public float resultadoConvolucao;

    public float getResultadoConvolucao() {
        return resultadoConvolucao;
    }

    public Correlacao() {
        resultadoConvolucao = 0;
    }

    public  float calculaCorrelacao(float[] amostra1, float[] amostra2){
        float multElementosAmostras = 0;
        float somaAmostra1 = 0;
        float somaAmostra2 = 0;
        float somaElemQuadAmostra1 = 0;
        float somaElemQuadAmostra2 = 0;

        for(int i=0;i<amostra1.length;i++){
            multElementosAmostras = multElementosAmostras + (amostra1[i] * amostra2[i]);
            somaAmostra1 = somaAmostra1 + amostra1[i];
            somaAmostra2 = somaAmostra2 + amostra2[i];
            somaElemQuadAmostra1 = somaElemQuadAmostra1 + (amostra1[i] * amostra1[i]);
            somaElemQuadAmostra2 = somaElemQuadAmostra2 + (amostra2[i] * amostra2[i]);
        }

        resultadoConvolucao = (float) ((amostra1.length * multElementosAmostras - somaAmostra1 * somaAmostra2) /
                               (Math.sqrt((amostra1.length * somaElemQuadAmostra1 - (somaAmostra1 * somaAmostra1)) *
                               (amostra2.length * somaElemQuadAmostra2 - (somaAmostra2 * somaAmostra2)))));

        return resultadoConvolucao;
    }

}

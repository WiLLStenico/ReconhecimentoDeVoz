/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author WiLL
 */
public class Interpolacao {

    public Interpolacao() {
    }

    public void calcInterpolacao(float[] v1, float[] v2) {
        int nroAmostras;
        float[] aux;

        if (v1.length > v2.length) {
            //nroAmostras = v1.length - v2.length;
            aux = v1;
            v1 = new float[v2.length];
            for (int i = 0; i < v2.length; i++) {
                v1[i] = aux[i];
                System.out.println("i1: " + i);
            }
            
        } else if (v1.length < v2.length) {
            aux = v2;
            v2 = new float[v1.length];
            for (int i = 0; i < v1.length; i++) {
                v2[i] = aux[i];
                System.out.println("i2: " + i);
            }
            
        }
    }
}

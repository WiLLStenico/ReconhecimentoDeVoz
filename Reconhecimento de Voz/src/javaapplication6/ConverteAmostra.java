/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author WiLL
 */
public class ConverteAmostra {

    private float[] AmostraByteToFloat = null;
    private float[] AmostraAux = null;

    public ConverteAmostra() { //Construtor
    }

    public float[] ConverteAmostraParaFloat(byte ByteArray[]) {

        int offset = 0; //ponto inicial da amostra
        int tam = ByteArray.length / 2; // tamanho da amostra

        //float[] AmostraByteToFloat;
//=================== Converte de byte para float ==============================
        AmostraByteToFloat = new float[tam];
        for (int i = offset; i < tam + offset; i++) {
            float amostra;
            if (ByteArray[2 * i] < 0) {
                amostra = 256 - ByteArray[2 * i + 1] + 256 * ByteArray[2 * i];

            } else {
                amostra = ByteArray[2 * i + 1] + 256 * ByteArray[2 * i];

            }
            AmostraByteToFloat[i - offset] = amostra;
        }
//==============================================================================
//========================== Normaliza Amostra =================================

        NormalizaMedia(AmostraByteToFloat);

        NormalizaAmostra(AmostraByteToFloat);

//        //"Zera periodos de Silencio"
//        for (int i = 0; i < AmostraByteToFloat.length; i++) {
//            if (Math.abs(AmostraByteToFloat[i]) <= 0.1) { //Se for menor que 10% é cortado
//                AmostraByteToFloat[i] = 0;
//            }
//        }
        
        Smooth(AmostraByteToFloat, 10);

//==============================================================================
//==========Remove Frquencias com baixa amplitude próximo ao Eixo X==============
        for (int i = 0; i < AmostraByteToFloat.length; i++) {
            //if (AmostraByteToFloat[i] != 0 && offset == 0) {
            if (AmostraByteToFloat[i] > 0.1 && offset == 0) {
                for (int j = 0; j <= i; j++) {
                    //if (AmostraByteToFloat[i - j] == 0) {
                    if (AmostraByteToFloat[i - j] <= 0.1) {
                        offset = i - 1;
                    } else {
                        offset = 0;
                    }
                }
            }
        }

        AmostraAux = new float[AmostraByteToFloat.length - offset];

        for (int i = 0; i < AmostraAux.length; i++) {
            AmostraAux[i] = AmostraByteToFloat[i + offset];
        }

        //Apaga
        offset = 0;
        //Offset Final
        for (int i = 0; i < AmostraAux.length; i++) {
            //if (AmostraAux[i] == 0 && offset == 0) {
            if (AmostraAux[i] <= 0.1 && offset == 0) {
                for (int j = i; j < AmostraAux.length; j++) {
                    if (AmostraAux[j] <= 0.1) {
                        if (offset == 0) {
                            offset = i + 1;
                        }
                    } else {
                        offset = 0;
                        break;
                    }
                }
            }
        }

        if (offset != 0) {
            AmostraByteToFloat = new float[offset];
        } else {
            AmostraByteToFloat = new float[AmostraAux.length];
        }

        System.arraycopy(AmostraAux, 0, AmostraByteToFloat, 0, offset);
//==============================================================================

        return AmostraByteToFloat;
    }

    public void Smooth(float[] flArray, int qtdeAmostras) { //Remove Ruidos
        float media = 0.0f;

        for (int i = 0; i < flArray.length - qtdeAmostras; i++) {
            media = 0;
            for (int j = 0; j < qtdeAmostras; j++) {
                media = media + flArray[i + j];
            }
            flArray[i] = media / qtdeAmostras;
        }
        for (int i = 0; i < qtdeAmostras; i++) {
            flArray[flArray.length - i - 1] = 0;
        }
    }

    public void NormalizaMedia(float[] flArray) {
        float media = 0.000000f, soma = 0.000000f;

        for (int i = 0; i < flArray.length; i++) { //Soma valores
            soma = soma + flArray[i];
        }

        media = soma / flArray.length;

        for (int i = 0; i < flArray.length; i++) { //Zera Media do Array
            flArray[i] = flArray[i] - media;
        }
//        //APAGAR
//        soma = 0;
//        for (int i = 0; i < flArray.length; i++) { //Soma valores
//            soma = soma + flArray[i];
//        }
//
//        media = soma / flArray.length;
//        System.out.println("Media:: " + media);
//        //APAGAR
    }

    public void NormalizaAmostra(float[] flArray) {
        //========================== Normaliza Amostra =================================
        float max = 0.0f;

        for (int i = 0; i < flArray.length; i++) { //Encontra maior valor da Amostra
            if (Math.abs(flArray[i]) > max) {
                max = Math.abs(flArray[i]);
            }
        }
        for (int i = 0; i < flArray.length; i++) { //Normaliza Amostra
            flArray[i] = flArray[i] / max;
        }

//==============================================================================
    }
}

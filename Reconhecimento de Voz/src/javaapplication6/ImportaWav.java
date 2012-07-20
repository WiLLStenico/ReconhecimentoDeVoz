/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication6;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author WiLL
 */
public class ImportaWav {

        private AudioFormat format;
        public byte[] samples;
        public float[] samplesw;

        public ImportaWav(String filename) {
            try {
                AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
                format = stream.getFormat();
                samples = getSamples(stream);

                int offset = 0; //Integer.parseInt(jTextField1.getText()); //ponto inicial da amostra
                int tam = samples.length/2; //(int)stream.getFrameLength(); // tamanho da amostra
                //jTextField3.setText(String.valueOf(samples.length / 2));
                samplesw = new float[tam]; // tamanho da amostra
                AudioFormat af = stream.getFormat();

                for (int i = offset; i < tam + offset; i++) {
                    float amostra;
                    if (samples[2 * i] < 0) {
                        amostra = 256 - samples[2 * i] + 256 * samples[2 * i + 1];//sendo o segundo byte o mais significativo
                    } else {
                        amostra = samples[2 * i] + 256 * samples[2 * i + 1];//sendo o segundo byte o mais significativo
                    }
                    samplesw[i - offset] = amostra;

                }
                float soma = 0;
                float media;
                float max = 0;
                for (int i = 0; i < samplesw.length; i++) { //imprime as amostras
                    soma = soma + samplesw[i];
                }
                media = soma / tam;
                for (int i = 0; i < samplesw.length; i++) { //imprime as amostras
                    samplesw[i] = samplesw[i] - media;
                }
                for (int i = 0; i < samplesw.length; i++) { //imprime as amostras
                    if (Math.abs(samplesw[i]) > max) {
                        max = Math.abs(samplesw[i]);
                    }
                }
                for (int i = 0; i < samplesw.length; i++) { //imprime as amostras
                    samplesw[i] = samplesw[i] / max;
                //System.out.println(i+" : "+samplesw[i]);
                }
                System.out.println("Media: " + media + " Maximo: " + max);

            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public byte[] getSamples() {
            return samples;
        }

        public byte[] getSamples(AudioInputStream stream) {
            int length = (int) (stream.getFrameLength() * format.getFrameSize());
            byte[] samples = new byte[length];
            DataInputStream in = new DataInputStream(stream);

            try {
                in.readFully(samples);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return samples;

        }
    }
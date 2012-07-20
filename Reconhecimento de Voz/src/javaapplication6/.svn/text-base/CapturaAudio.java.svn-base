/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication6;

/**
 *
 * @author WiLL
 */
import java.io.*;
import javax.sound.sampled.*;

public class CapturaAudio /*extends JFrame*/ {

  protected boolean running;
  ByteArrayOutputStream out;

  
  public byte[] stopCapture(){
    running = false;
    byte audio[] = out.toByteArray();
    return audio;
  }

  public void captureAudio() {
    try {
      final AudioFormat format = getFormat();
      DataLine.Info info = new DataLine.Info(
        TargetDataLine.class, format);
      final TargetDataLine line = (TargetDataLine)
        AudioSystem.getLine(info);

      line.open(format);
      line.start();
      Runnable runner = new Runnable() {
        int bufferSize = (int)format.getSampleRate()
          * format.getFrameSize();
        byte buffer[] = new byte[bufferSize];

        public void run() {
          out = new ByteArrayOutputStream();
          running = true;
          try {
            while (running) {
              int count =
                line.read(buffer, 0, buffer.length);
              if (count > 0) {
                out.write(buffer, 0, count);
              }
            }
            out.close();
            line.close(); //Sacchi
          } catch (IOException e) {
            System.err.println("I/O problems: " + e);
            System.exit(-1);
          }
        }
      };
      Thread captureThread = new Thread(runner);
      captureThread.start();
    } catch (LineUnavailableException e) {
      System.err.println("Line unavailable: " + e);
      System.exit(-2);
    }
  }

  public void playAudio(byte audio[]) {
      try {
      
      InputStream input =
        new ByteArrayInputStream(audio);
      final AudioFormat format = getFormat();
      final AudioInputStream ais =
        new AudioInputStream(input, format,
        audio.length / format.getFrameSize());
      DataLine.Info info = new DataLine.Info(
        SourceDataLine.class, format);
      final SourceDataLine line = (SourceDataLine)
        AudioSystem.getLine(info);
      line.open(format);
      line.start();

      Runnable runner = new Runnable() {
        int bufferSize = (int) format.getSampleRate()
          * format.getFrameSize();
        byte buffer[] = new byte[bufferSize];

        public void run() {
          try {
            int count;
            while ((count = ais.read(
                buffer, 0, buffer.length)) != -1) {
              if (count > 0) {
                line.write(buffer, 0, count);
              }
            }
            line.drain();
            line.close();

          } catch (IOException e) {
            System.err.println("I/O problems: " + e);
            System.exit(-3);
          }
        }
      };
      Thread playThread = new Thread(runner);
      playThread.start();
    } catch (LineUnavailableException e) {
      System.err.println("Line unavailable: " + e);
      System.exit(-4);
    }
  }

  private AudioFormat getFormat() {
      
    //float sampleRate = 44100;
    float sampleRate = 22050;
    int sampleSizeInBits = 16;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = true;
    return new AudioFormat(sampleRate,
    sampleSizeInBits, channels, signed, bigEndian);
    }

}


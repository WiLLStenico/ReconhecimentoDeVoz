package javaapplication6;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WiLL
 */
import java.awt.Color;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CriaGrafico {

    //Criação de amostra
    //private static float[] AmostraByteToFloat;
    //private javax.swing.JProgressBar jProgress;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private XYDataset dataset;
    private String strTitulo ;
    private String sLegX;
    private String sLegY;


    public CriaGrafico(  ) {
        chart = null;
        chartPanel = null;
        dataset = null;
        strTitulo = "Grafico";
        sLegX = "Tempo";
        sLegY = "Amplitude";
    }

    public ChartPanel getGrafico(float[] Amostra) throws IOException {

        //Converte Amostra de Byte para Inteiro
        
        //Define Tipo de Grafico
        chart = ChartFactory.createXYLineChart(strTitulo, // titulo do grafico
                sLegX, // eixo X
                sLegY, // eixo Y
                createDataset(Amostra), // dados para o gráfico
                PlotOrientation.VERTICAL,
                true,
                true,
                true);

//        System.out.println("Criei o Chart");
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, true);

        //Alterar cor da Linha
        XYItemRenderer RenderGrafico = chart.getXYPlot().getRenderer();
        //          Color(int red, int green, int blue)
        RenderGrafico.setSeriesPaint(0, new Color(35, 158, 38));
        //RenderGrafico.setSeriesPaint(0, new Color((int) Math.random()*255));
        
        return chartPanel;
    }

    private XYDataset createDataset(float[] Amostra) {

        int i;
        XYSeries series = new XYSeries(strTitulo);
       
        for (i = 0; i < Amostra.length; i++) {

            //Adiciona dados ao gráfico
            series.add(i, Amostra[i]);
            //System.out.println("\n Amostra: " + AmostraByteToFloat[i]);
        }

    //    System.out.println("TERMINEI DE JOGAR");
        dataset = new XYSeriesCollection(series);

  //      System.out.println("FIM DATASET");
        return dataset;
    }

    public String getStrTitulo() {
        return strTitulo;
    }

    public void setStrTitulo(String strTitulo) {
        this.strTitulo = strTitulo;
    }

    public String getsLegX() {
        return sLegX;
    }

    public void setsLegX(String sLegX) {
        this.sLegX = sLegX;
    }

    public String getsLegY() {
        return sLegY;
    }

    public void setsLegY(String sLegY) {
        this.sLegY = sLegY;
    }

    

} //Fim Classe


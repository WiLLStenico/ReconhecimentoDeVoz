/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication6;

/**
 *
 * @author WiLL
 */
/**
* Funções utilitárias para trabalhar com arquivos
* autor: Gregui Shigunov
* arquivo: UtilsArquivo.java
* 20/09/2007
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UtilsArquivos {

/**
* Salva o conteúdo de uma variável em um arquivo
* @param arquivo
* @param conteudo
* @param adicionar se true adicionar no final do arquivo
* @throws IOException
*/
public static void salvar(String arquivo, String conteudo, boolean adicionar)
throws IOException {

FileWriter fw = new FileWriter(arquivo, adicionar);

fw.write(conteudo);
fw.close();

}

/**
* Carrega o conteúdo de um arquivo em uma String, se o aquivo
* não existir, retornará null.
* @param arquivo
* @return conteúdo
* @throws Exception
*/

public static String abrir(String arquivo)
throws FileNotFoundException, IOException {

File file = new File(arquivo);
if (! file.exists()) {
return null;
}

BufferedReader br = new BufferedReader(new FileReader(arquivo));
StringBuilder bufSaida = new StringBuilder();

String linha;
while( (linha = br.readLine()) != null ){
    bufSaida.append(linha).append("\n");
}
br.close();
return bufSaida.toString();
}

//Deleta arquivos de inicialização
public static void delete(String arquivo)
throws FileNotFoundException, IOException {

    File file = new File(arquivo);
    if ( file.exists()) {
        file.delete();
    }
}

public static String carregar(String arquivo)
throws FileNotFoundException, IOException {

File file = new File(arquivo);
if (! file.exists()) {
return null;
}

BufferedReader br = new BufferedReader(new FileReader(arquivo));
StringBuilder bufSaida = new StringBuilder();

String linha;
while( (linha = br.readLine()) != null ){
    bufSaida.append(linha).append("\n");
}
br.close();
return bufSaida.toString();
}

}

/*
 * String linhaExame;
       try {
          FileReader reader = new FileReader("Exame.txt");
          BufferedReader leitor = new BufferedReader(reader);
          StringTokenizer st = null;

          while ((linhaExame = leitor.readLine()) != null) {

             st = new StringTokenizer(linhaExame, "\t");
             String dados = null;

             while (st.hasMoreTokens()) {

                // Código
                dados = st.nextToken();
                System.out.println(dados);
                aCodExame[nExame] = dados;

                // Descrição
                dados = st.nextToken();
                System.out.println(dados);
                aNomeExame[nExame] = dados;

                // Requisitos
                dados = st.nextToken();
                System.out.println(dados);
                aRequisitosExame[nExame] = dados;

                nExame ++;

             }
          }

          leitor.close();
          reader.close();

       } catch (Exception e) {
          e.printStackTrace();
       }
 *
 */

/*
try {
String texto = "Texto";

UtilsArquivos.salvar("Nome_arquivo.txt", texto, false);

texto = UtilsArquivo.carregar("arquivo.txt");

System.out.println(texto);

} catch (Exception e) {
e.printStackTrace();
}

}
*/

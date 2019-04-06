package com.example.quemtocahoje.Utility;

import com.example.quemtocahoje.POJO.Arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConversaoArquivo {

    public static byte[] converterArquivoParaBlob(String caminhoArquivo) throws IOException {

        File arquivo = new File(caminhoArquivo);
        //Inicializa um array de byte do tamanho do arquivo
        byte[] conteudoArquivo = new byte[(int) arquivo.length()];
        FileInputStream inputStream = null;

        try{
            //cria um input stream apontando para o arquivo
            inputStream = new FileInputStream(arquivo);
            //lê o conteúdo do arquivo para um array de byte
            inputStream.read(conteudoArquivo);
        }catch (IOException e){
            throw new IOException("Não foi possível converter o arquivo. "+ e.getMessage());
        }finally{
            //fecha a input stream
            if(inputStream != null)
                inputStream.close();
        }
        return conteudoArquivo;
    }

}

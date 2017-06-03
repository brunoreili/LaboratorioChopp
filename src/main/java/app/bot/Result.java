package app;

import java.io.Serializable;

//Classe para retorno (Utilizado para o teste)
public class Result implements Serializable{
    private String texto;

    Result(String mensagemResposta) {
        texto = mensagemResposta;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
  
}
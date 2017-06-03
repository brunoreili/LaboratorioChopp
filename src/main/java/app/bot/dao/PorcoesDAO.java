package app.bot.dao;

import java.util.ArrayList;
import java.util.List;

public class PorcoesDAO {
    public List<String> recuperaOpcoesPorcoes() {
        List<String> resultado = new ArrayList<>();
        
        resultado.add("Batata Frita");
        resultado.add("Carne de sol c/ Mandioca");
        resultado.add("Camarão c/ Queijo");
        
        return resultado;
    }        
}
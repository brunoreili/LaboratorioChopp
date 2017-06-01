package bot.dao;

import java.util.ArrayList;
import java.util.List;

public class EspetinhoDAO {
    public List<String> recuperaOpcoesEspetinhos() {
        List<String> resultado = new ArrayList<>();
        
        resultado.add("Espetinho de Picanha");
        resultado.add("Espetinho de Carne de Sol");
        resultado.add("Espetinho de Frango c/ Bacon");
        resultado.add("Espetinho de Linguiça");
        
        return resultado;
    }    
}
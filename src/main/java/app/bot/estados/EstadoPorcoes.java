package app.bot.estados;

import app.bot.dao.PorcoesDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoPorcoes extends Estado {

    public EstadoPorcoes(ApplicationContext context) {
        super(context);
    }

    @Override
    public void processaTexto(String texto) {
        
        List<String> opcoes = new PorcoesDAO().recuperaOpcoesPorcoes();
        
        try{
            int opcao = Integer.parseInt(texto.trim()) - 1;
            mensagemResposta = "legal, voce escolheu porção de " + opcoes.get(opcao) + "." + System.lineSeparator() +
                                "Quantas deseja?";
            //proximoEstado = new EstadoQuantidade(opcoes.get(opcao), context);
        } catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }
    
}
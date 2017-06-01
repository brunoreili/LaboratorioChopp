package bot.estados;

import bot.cliente.Cliente;
import bot.comanda.Comanda;
import bot.dao.EspetinhoDAO;
import bot.dao.PorcoesDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoEscolhendo extends Estado {

    private final Cliente cliente;
    private final Comanda comanda;

    public EstadoEscolhendo(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }    
    
    @Override
    public void processaTexto(String texto) {
        
        switch (texto.trim()) {
            case "1":
                mensagemResposta = "BEBIDAS" + System.lineSeparator() +
                        "Certo, " + cliente.getFirst_name() + ", temos no cardápio:" + System.lineSeparator() +
                        "1 - CERVEJAS" + System.lineSeparator() +
                        "2 - DRINKS" + System.lineSeparator() +
                        "3 - REFRIGERANTES" + System.lineSeparator() +
                        "4 - SUCOS" + System.lineSeparator() +
                        "5 - AGUA" + System.lineSeparator() +
                        "O que deseja?";
                proximoEstado = new EstadoBebidas(context, cliente, comanda);
                break;
            case "2":
                {
                    List<String> opcoes = new EspetinhoDAO().recuperaOpcoesEspetinhos();
                    mensagemResposta = "Legal, temos:";
                    for(int i=0; i < opcoes.size() ; i++){
                        mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i);
                    }       proximoEstado = new EstadoEspetinho(context);
                    break;
                }
            case "3":
                {
                    List<String> opcoes = new PorcoesDAO().recuperaOpcoesPorcoes();
                    mensagemResposta = "Legal, temos as porções de:";
                    for(int i=0; i < opcoes.size() ; i++){
                        mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i);
                    }       proximoEstado = new EstadoPorcoes(context);
                    break;
                }
            default:
                mensagemResposta = "Por favor, escolha as opções entre 1, 2 ou 3!";
                proximoEstado = this;
                break;
        }
        
    }
    
}
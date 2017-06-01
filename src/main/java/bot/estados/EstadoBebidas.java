package bot.estados;

import bot.cliente.Cliente;
import bot.comanda.Comanda;
import bot.dao.CervejaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoBebidas extends Estado{

    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    String escolha;
    
    public EstadoBebidas(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }
    
    @Override
    public void processaTexto(String texto) {
       
        switch (texto.trim()) {
            case "1":
                escolha = "Cerveja";
                List<String> opcoes = cervejaDAO.recuperaOpcoesCervejas();
                List<Double> precos = cervejaDAO.recuperaValoresCervejas();
                
                mensagemResposta = "Legal, temos as cervejas:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                }                
                proximoEstado = new EstadoCervejas(context, cliente, comanda, escolha); 
                
                break;
            case "2":
                mensagemResposta = "Desculpe, não temos drinks no momento!" + System.lineSeparator() +
                        "Escolha outra coisa!";
                proximoEstado = this;
                break;
            case "3":
                mensagemResposta = "Desculpe, não temos refrigerantes no momento!" + System.lineSeparator() +
                        "Escolha outra coisa!";
                proximoEstado = this;
                break;
            case "4":
                mensagemResposta = "Desculpe, não temos sucos no momento!" + System.lineSeparator() +
                        "Escolha outra coisa!";
                proximoEstado = this;
                break;
            case "5":
                mensagemResposta = "Desculpe, não temos agua no momento!" + System.lineSeparator() +
                        "Escolha outra coisa!";
                proximoEstado = this;
                break;
            default:
                mensagemResposta = "Por favor, escolha uma opção válida!";
                proximoEstado = this;
                break;    
        }
    }

}
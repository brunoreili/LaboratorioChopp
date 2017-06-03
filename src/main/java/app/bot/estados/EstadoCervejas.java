package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.CervejaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoCervejas extends Estado {

    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoCervejas(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }
    
    @Override
    public void processaTexto(String texto) {
        
        List<String> cerveja = cervejaDAO.recuperaOpcoesCervejas();
        
        try{
            item = Integer.parseInt(texto) - 1;
            mensagemResposta = "Você escolheu a cerveja " + cerveja.get(item) + System.lineSeparator() +
                                "Quantas deseja?";
            proximoEstado = new EstadoQuantidade(context, cliente, comanda, escolha, item);
        }
        catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }
    
}
package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.CervejaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoQuantidade extends Estado{
    
    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    private final int item;    
    String produto;
    int quantidade;
    double valor;
    
    public EstadoQuantidade(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha, int item){
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
        this.item = item;
    }   
    
    @Override
    public void processaTexto(String texto) {
                       
        try{            
            if(escolha.equals("Cerveja")){                
                List<String> cerveja = cervejaDAO.recuperaOpcoesCervejas();
                List<String> preco = cervejaDAO.recuperaValoresCervejas();
                
                produto = cerveja.get(item);
                quantidade = Integer.parseInt(texto.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            }            
            if(quantidade == 1){                
                mensagemResposta = "Beleza, anotei! " + quantidade + " unidade de " + produto + System.lineSeparator() +
                                   "O valor total ficou em: R$ " + valor + System.lineSeparator() +
                                   "Posso confirmar ou deseja pedir algo a mais?" + System.lineSeparator() +
                                   "1 - Quero pedir algo a mais!" + System.lineSeparator() +
                                   "2 - Pode confirmar o pedido!";
                proximoEstado = new EstadoConfirma(context, cliente, comanda, produto, quantidade, valor);    
            }
            else if(quantidade > 1 && quantidade <= 20){                
                mensagemResposta = "Beleza, Anotei! São " + quantidade + " unidades de " + produto + System.lineSeparator() +
                                   "O valor total ficou em: R$ " + valor + System.lineSeparator() +
                                   "Posso confirmar ou deseja pedir algo a mais?" + System.lineSeparator() +
                                   "1 - Quero pedir algo a mais!" + System.lineSeparator() +
                                   "2 - Pode confirmar o pedido!";
                proximoEstado = new EstadoConfirma(context, cliente, comanda, produto, quantidade, valor);                
            }
            else if(quantidade > 20){
                mensagemResposta = "Desculpe, só posso trazer no máximo 20 unidades deste pedido!" + System.lineSeparator() +
                                   "Por favor, tente uma quantidade menor.";
                proximoEstado = this;
            }
            else{
                mensagemResposta = "Vamos lá, preciso de um número." + System.lineSeparator() + 
                                   "Por favor, tente de novo.";
                proximoEstado = this;
            }            
        }
        catch (Exception e){
            mensagemResposta = "Vamos lá, cara, preciso de um número." + System.lineSeparator() + 
                               "Por favor, tente de novo.";
            proximoEstado = this;
        }
     
    }
    
}
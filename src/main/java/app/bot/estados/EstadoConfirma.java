package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
import app.bot.comanda.ItemComanda;
import app.bot.comanda.ItemComandaRepository;
import app.bot.dao.ComandaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoConfirma extends Estado{
    
    private final ComandaDAO comandaDAO = new ComandaDAO(context);
    private final ComandaRepository comandaRepository; 
    private final ItemComandaRepository itemComandaRepository;
    private final Cliente cliente;
    private final Comanda comanda;
    private final String produto;
    private final int quantidade;
    private final double valor;
    

    public EstadoConfirma(ApplicationContext context, Cliente cliente, Comanda comanda, String produto, int quantidade, double valor) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.comandaRepository = context.getBean(ComandaRepository.class);
        this.itemComandaRepository = context.getBean(ItemComandaRepository.class);
    }

    @Override
    public void processaTexto(String texto) {
                                                //Falta gravar e buscar do banco de verdade
        try{          
            switch (texto.trim()) {
                case "1":
                    salvaItem();                                      
                    mensagemResposta = "Boa, " + cliente.getFirst_name() + "!" + System.lineSeparator() +
                                       "O que mais você gostaria de pedir?" + System.lineSeparator() +
                                       "1 - Para BEBIDAS," + System.lineSeparator() +
                                       "2 - Para ESPETINHOS," + System.lineSeparator() +
                                       "3 - Para PORÇÕES";
                    proximoEstado = new EstadoEscolhendo(context, cliente, comanda);
                    break;
                case "2":
                    salvaItem();                    
                    List<String> itens = comandaDAO.recuperaItensComanda();
                    List<Integer> quantidade = comandaDAO.recuperaQuantidadeItem();
                    List<Double> valor = comandaDAO.recuperaValorItem();
                    
                    mensagemResposta = "Pedido confirmado, " + cliente.getFirst_name() + "!" + System.lineSeparator() +
                                       "Agora é só aguardar que logo entregaremos na sua mesa." + System.lineSeparator() +
                                       "Sua comanda até o momento está assim:" + System.lineSeparator() + System.lineSeparator() +
                                       "QUANTIDADE - PRODUTO - VALOR" + System.lineSeparator();      
                
                    for(int i=0; i < itens.size() ; i++){
                        mensagemResposta += System.lineSeparator() + 
                                            quantidade.get(i) + " - " + 
                                            itens.get(i) + "........R$ " +
                                            valor.get(i);
                    }
                    mensagemResposta += System.lineSeparator() + 
                                        "TOTAL...................R$ " + comanda.getTotal() +
                                        System.lineSeparator() + System.lineSeparator() + 
                                        "Se precisar de alguma coisa é só me chamar!";
                    proximoEstado = null;
                    
                    break;
                default:
                    mensagemResposta = "Por favor, escolha as opções entre 1, 2 ou 3!";
                    proximoEstado = this;
                    break;
            }
        }
        catch(Exception e){
            mensagemResposta = "Por favor, escolha as opções entre 1, 2 ou 3!";
            proximoEstado = this;
        }
        
    }
            
            public void salvaItem(){
                ItemComanda item = new ItemComanda();
                item.setNome(produto);
                item.setQuantidade(quantidade);
                item.setValor(valor);
                itemComandaRepository.save(item);
                
                comanda.setTotal(comanda.getTotal() + valor);
                comandaRepository.save(comanda);
            }
    
}
package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import org.springframework.context.ApplicationContext;

public class EstadoInicial extends Estado{
    
    private final Cliente cliente;
    private final Comanda comanda;

    public EstadoInicial(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        
    }
    
    @Override
    public void processaTexto(String texto) {
               
        if(cliente.getStatus().equals("Novo")){
            
            mensagemResposta = "Olá, " + cliente.getFirst_name() + " " + cliente.getLast_name() + "!" + System.lineSeparator() +
                               "Seja bem vindo ao Laboratório do Chopp!" + System.lineSeparator() +
                               "É muito bom ter você como novo cliente do nosso Bar!" + System.lineSeparator() +
                               "O total da sua comanda no momento é de R$ " + comanda.getTotal() + System.lineSeparator() +
                               "Sou o seu garçom virtual e estou aqui para ajuda-lo a escolher seu pedido." + System.lineSeparator() +
                               "Compareça mais vezes para se tornar um cliente Bronze, Prata ou Ouro e terá descontos especiais!" + System.lineSeparator() + 
                               "Vamos lá, escolha:" + System.lineSeparator() + 
                               "1 - Para BEBIDAS," + System.lineSeparator() +
                               "2 - Para ESPETINHOS," + System.lineSeparator() +
                               "3 - Para PORÇÕES";
        }else{
            mensagemResposta = "Olá, " + cliente.getFirst_name() + " " + cliente.getLast_name() + "!" + System.lineSeparator() +
                               "Seja bem vindo ao Laboratório do Chopp!" + System.lineSeparator() +
                               "É muito bom tê-lo de volta!" + System.lineSeparator() +
                               "Você é um cliente " + cliente.getStatus() + " e terá descontos especiais ao fechar a comanda!" + System.lineSeparator() + 
                               "Vamos lá, escolha:" + System.lineSeparator() + 
                               "1 - Para BEBIDAS," + System.lineSeparator() +
                               "2 - Para ESPETINHOS," + System.lineSeparator() +
                               "3 - Para PORÇÕES";
        }
        proximoEstado = new EstadoEscolhendo(context, cliente, comanda);
        
    }
    
}
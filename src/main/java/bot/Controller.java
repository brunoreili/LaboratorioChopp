package bot;

import bot.item.ItemCervejaRepository;
import bot.item.ItemCerveja;
import bot.cliente.Cliente;
import bot.cliente.ClienteRepository;
import bot.comanda.Comanda;
import bot.comanda.ComandaRepository;
import bot.estados.Estado;
import bot.estados.EstadoInicial;
import bot.model.Update;
import bot.sender.Sender;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller{
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ComandaRepository comandaRepository;
    
    //Teste de Banco    
    @Autowired
    private ItemCervejaRepository itemCervejaRepository;
    
    public Map<Integer, Estado> estados = new HashMap<Integer, Estado>();
    private static final String BOT_ID = "374481790:AAHgscpBDG2zs4VsDbeg140VmSVZZeItPEw";
    
    @RequestMapping(method=RequestMethod.POST, value="/update")
    public Result ReceberUpdate(@RequestBody Update update){
        
        //Teste de Banco
        deletaBanco();
        preencheBanco();
        
        String mensagem = update.getMessage().getText();
        int user_id = update.getMessage().getFrom().getId();
        
        Cliente cliente = buscarCliente(update);
        Comanda comanda = buscarComanda(cliente);
        
        Estado estado = estados.get(user_id);
        if (estado == null){
            estado = new EstadoInicial(context, cliente, comanda);
        }
        estado.processaTexto(mensagem);        
        estados.put(user_id, estado.getProximoEstado());


        if(user_id > 100) // significa que estamos fazendo testes. O userID do telegram tem varios digitos. Nesse caso nao enviaremos a resposta via telegram
        {
            try {
                new Sender(BOT_ID).sendResponse(user_id, estado.getMensagemResposta());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new Result(estado.getMensagemResposta());
    }
    
    //Teste de Banco
    private void deletaBanco(){
       
       itemCervejaRepository.deleteAll();
        
    }    
    private void preencheBanco() {
        
        ItemCerveja c1 = new ItemCerveja();
        c1.setNome("Antartica");
        c1.setValor(7.00);        
        itemCervejaRepository.save(c1);
                
        ItemCerveja c2 = new ItemCerveja();
        c2.setNome("Skol         ");
        c2.setValor(6.00);        
        itemCervejaRepository.save(c2);
                
        ItemCerveja c3 = new ItemCerveja();
        c3.setNome("Bohemia ");
        c3.setValor(11.00);        
        itemCervejaRepository.save(c3);
                
    }

    private Cliente buscarCliente(Update update) {
        
        Integer idCliente = update.getMessage().getFrom().getId();
        Cliente cliente = clienteRepository.findOne(idCliente);
        
        if(cliente == null){
            Cliente novo = new Cliente();
            novo.setFirst_name(update.getMessage().getFrom().getFirst_name());
            novo.setLast_name(update.getMessage().getFrom().getLast_name());
            novo.setId(idCliente);
            novo.setStatus("Novo");
            novo.setConsumo(0.00);
            cliente = clienteRepository.save(novo);
        }
        if(cliente.getConsumo() > 0 && cliente.getConsumo() <= 10){
            cliente.setStatus("Bronze");
            cliente = clienteRepository.save(cliente);
        }
                
        return cliente;
        
    }
    
    private Comanda buscarComanda(Cliente cliente){
        
        Comanda comanda = comandaRepository.findOne(cliente.getId());
        
        if(comanda == null){
            Comanda nova = new Comanda();
            nova.setId(cliente.getId());
            nova.setCliente(cliente);
            nova.setItem(null);
            nova.setTotal(0);
            comanda = comandaRepository.save(nova);
        }
        
        return comanda;
    }
}
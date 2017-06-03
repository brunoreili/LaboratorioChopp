package app.bot.dao;

import app.bot.comanda.ItemComanda;
import app.bot.comanda.ItemComandaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class ComandaDAO {
 
    private final ApplicationContext context;
    private ItemComandaRepository itemRepository;
    
    public ComandaDAO(ApplicationContext context){
        this.context = context;
    }
    
    public List<String> recuperaItensComanda(){
        
        itemRepository = context.getBean(ItemComandaRepository.class);        
        Iterable<ItemComanda> itemComanda = itemRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemComanda item : itemComanda){
            result.add(item.getNome());
        }
    
        return result;
    }    
    public List<Integer> recuperaQuantidadeItem(){
        
        itemRepository = context.getBean(ItemComandaRepository.class);        
        Iterable<ItemComanda> itemComanda = itemRepository.findAll();    
        List<Integer> result = new ArrayList<>();
        
        for(ItemComanda item : itemComanda){
            result.add(item.getQuantidade());
        }
    
        return result;
    }    
    public List<Double> recuperaValorItem(){
        
        itemRepository = context.getBean(ItemComandaRepository.class);        
        Iterable<ItemComanda> itemComanda = itemRepository.findAll();    
        List<Double> result = new ArrayList<>();
        
        for(ItemComanda item : itemComanda){
            result.add(item.getValor());
        }
    
        return result;
    }
    
}
package bot.dao;

import bot.item.ItemCerveja;
import bot.item.ItemCervejaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class CervejaDAO {
     
    private final ApplicationContext context;    
    private ItemCervejaRepository itemRepository;
    
    public CervejaDAO(ApplicationContext context){
        this.context = context;
    }
    
    public List<String> recuperaOpcoesCervejas(){
        
        itemRepository = context.getBean(ItemCervejaRepository.class);        
        Iterable<ItemCerveja> cervejas = itemRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemCerveja cerva : cervejas){
            result.add(cerva.getNome());
        }
    
        return result;
    }    
    public List<Double> recuperaValoresCervejas(){
        
        itemRepository = context.getBean(ItemCervejaRepository.class);
        Iterable<ItemCerveja> cervejas = itemRepository.findAll();
        List<Double> result = new ArrayList<>();
        
        for(ItemCerveja cerva : cervejas){
            result.add(cerva.getValor());
        }
        
        return result;
    }
    
}
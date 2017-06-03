package app.bot.dao;

import app.bot.item.ItemCerveja;
import app.bot.item.ItemCervejaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class CervejaDAO {
     
    private final ApplicationContext context;    
    private ItemCervejaRepository itemCervejaRepository;
    
    public CervejaDAO(ApplicationContext context){
        this.context = context;
    }   
    
    public void cadastraCervejas(){
        
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);
        
        if("[]".equals(itemCervejaRepository.findAll().toString())){
            
            ItemCerveja c1 = new ItemCerveja();
            c1.setNome("Antartica");
            c1.setValor("7.0");        
            itemCervejaRepository.save(c1);
                
            ItemCerveja c2 = new ItemCerveja();
            c2.setNome("Devassa      ");
            c2.setValor("6.0");        
            itemCervejaRepository.save(c2);
        
            ItemCerveja c3 = new ItemCerveja();
            c3.setNome("Skol          ");
            c3.setValor("5.0");        
            itemCervejaRepository.save(c3);    
        }
    }
    
    public List<String> recuperaOpcoesCervejas(){
        
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);        
        Iterable<ItemCerveja> cervejas = itemCervejaRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemCerveja cerva : cervejas){
            result.add(cerva.getNome());
        }
    
        return result;
    }    
    
    public List<String> recuperaValoresCervejas(){
        
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);
        Iterable<ItemCerveja> cervejas = itemCervejaRepository.findAll();
        List<String> result = new ArrayList<>();
        
        for(ItemCerveja cerva : cervejas){
            result.add(cerva.getValor());
        }
        
        return result;
    }
    
}
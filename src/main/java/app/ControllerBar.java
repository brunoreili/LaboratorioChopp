package app;

import app.bot.dao.CervejaDAO;
import app.bot.item.ItemCerveja;
import app.bot.item.ItemCervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerBar {
    
    @Autowired
    private ApplicationContext context;
    
    private ItemCervejaRepository itemCervejaRepository;

    @RequestMapping(method=RequestMethod.POST, value="/salvar")
    public ItemCerveja preencheBanco(@RequestBody ItemCerveja cerveja) {

        CervejaDAO bancoCerveja = new CervejaDAO(context);
        bancoCerveja.cadastraCervejas();
        
        System.out.println("uebaaaa");
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);
        itemCervejaRepository.save(cerveja);

        return cerveja;

    }

    @RequestMapping(method=RequestMethod.GET, value="/listar")
    public List<ItemCerveja> buscarCervejas() {

        System.out.println("uebaaaa");
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);

        return (List<ItemCerveja>) itemCervejaRepository.findAll();

    }
    
}
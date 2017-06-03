package app.bot.cliente;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String status;
    private Double consumo;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Double getConsumo() {
        return consumo;
    }
    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

}
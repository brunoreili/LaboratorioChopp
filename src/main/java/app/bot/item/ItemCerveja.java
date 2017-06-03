package app.bot.item;

import javax.persistence.*;

@Entity //Classe de teste de banco
public class ItemCerveja {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;    
    private String valor;
    
    //métodos get e set de "codigo" apenas para utilização no metodo deletaBanco na classe controller!
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
}
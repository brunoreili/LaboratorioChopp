package app.bot.estados;

import org.springframework.context.ApplicationContext;

public abstract class Estado {
     protected String mensagemResposta;
     protected Estado proximoEstado;
     protected ApplicationContext context;
     
     
     public Estado(ApplicationContext context){
         this.context = context;
     }
     
    public abstract void processaTexto(String texto);
    
    public String getMensagemResposta() {
        return mensagemResposta;
    }

    public Estado getProximoEstado() {
        return proximoEstado;
    }   
}
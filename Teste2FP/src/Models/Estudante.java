package Models;

import java.io.Serializable;

public class Estudante extends Pessoa implements Serializable {
    
    
    public Estudante(){
        
    }
    
    private double nota1;
    private double nota2;
    private double media;
    private String situacao;

    public Estudante(double nota1, double nota2, String situacao, String nome, String genero, int codigo) {
        super(nome, genero, codigo);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    public double getMedia() {
        return (nota1+nota2)/2;
    }

    public void setMedia(double media) {
        this.media = media;
    }
   
    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    @Override
    public String toString() {
        return super.toString() + "nota1=" + nota1 +"\n"+ "nota2=" + nota2+"\n"+ "media=" + media+ "situacao=" + situacao;
    }
}

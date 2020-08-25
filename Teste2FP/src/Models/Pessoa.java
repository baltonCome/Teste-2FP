package Models;

import java.io.Serializable;

/**
 *
 * @author Mr. Savagery
 */
public abstract class Pessoa implements Serializable {
    
    public Pessoa(){
        
    }
    
    public Pessoa(String nome, String genero, int codigo) {
        this.nome = nome;
        this.genero = genero;
        this.codigo = codigo;
    }
    
    private String nome;
    private String genero;
    private int codigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "nome=" + nome + "\n"+" genero=" + genero +"\n"+"codigo=" + codigo+"\n";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Mr. Savagery
 */
public class Professor extends Pessoa implements Serializable {
    
    public Professor(){
        
    }

    public Professor(String grauAcademico, String nome, String genero, int codigo) {
        super(nome, genero, codigo);
        this.grauAcademico = grauAcademico;
    }
    
    private String grauAcademico;

    public String getGrauAcademico() {
        return grauAcademico;
    }

    public void setGrauAcademico(String grauAcademico) {
        this.grauAcademico = grauAcademico;
    }

    @Override
    public String toString() {
        return super.toString()+ "grauAcademico=" + grauAcademico;
    }
}

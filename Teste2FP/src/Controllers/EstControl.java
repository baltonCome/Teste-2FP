package Controllers;

import Models.Estudante;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mr. Savagery
 */
public class EstControl {
    
    public static void guardar(ArrayList<Estudante> estudante) throws IOException, ClassNotFoundException, FileNotFoundException{

        if (!estudante.isEmpty()){
            try (FileOutputStream fos = new FileOutputStream("src/Files/DadosDoEstudante.dat")){
                try (ObjectOutputStream oos =  new ObjectOutputStream(fos)){

                    oos.writeObject(estudante);oos.flush();oos.close();
                    fos.flush();fos.close();

                }catch (FileNotFoundException e){
                    JOptionPane.showMessageDialog(null, "Erro ao Gravar, "+e);
                }
            }catch (IOException e){
                JOptionPane.showMessageDialog(null, "Erro ao Gravar, "+e);
            }
        }
    }

    public static ArrayList <Estudante> exibe () throws  IOException, ClassNotFoundException{

        try (FileInputStream fis = new FileInputStream("src/Files/DadosDoEstudante.dat")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Estudante>) ois.readObject();
            }
        }
    }
    
    public static void mediaGeral (ArrayList<Estudante> estudante){
        
        double somatorio= 0;    
        int i = 0;
        if (Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))) {
            if (!estudante.isEmpty()) {
                try {
                    for (i = 0; i < estudante.size(); i++) {
                        somatorio += estudante.get(i).getMedia();
                    }
                } catch (Exception e) {
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Media Geral: "+ somatorio/i);
    }
    
    public static void estudantesDispensados(ArrayList<Estudante> estudante){
        
        String concat="";
        if (Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))) {
            if (!estudante.isEmpty()) {
                try {
                    for (int i = 0; i < estudante.size(); i++) {
                        if(estudante.get(i).getSituacao().equalsIgnoreCase("Dispensado")){
                            concat+= estudante.get(i).getNome()+"\n";
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (!concat.equals("")){
            JOptionPane.showMessageDialog(null, "Estudantes Dispensados: "+"\n"+concat);
        }else{
            JOptionPane.showMessageDialog(null, "Sem Estudantes Dispensados");
        }
    }
    
    public static void estudantesAdmitidos(ArrayList<Estudante> estudante){
        
        String concat="";
        if (Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))) {
            if (!estudante.isEmpty()) {
                try {
                    for (int i = 0; i < estudante.size(); i++) {
                        if(estudante.get(i).getSituacao().equalsIgnoreCase("Admitido")){
                            concat+= estudante.get(i).getNome()+"\n";
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (!concat.equals("")){
            JOptionPane.showMessageDialog(null, "Estudantes Admitidos: "+"\n"+concat);
        }else{
            JOptionPane.showMessageDialog(null, "Sem Estudantes Admitidos");
        }
    }
    
    public static void estudantesExcluidos(ArrayList<Estudante> estudante){
        
        String concat="";
        if (Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))) {
            if (!estudante.isEmpty()) {
                try {
                    for (int i = 0; i < estudante.size(); i++) {
                        if(estudante.get(i).getSituacao().equalsIgnoreCase("Excluido")){
                            concat+= estudante.get(i).getNome()+"\n";
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (!concat.equals("")){
            JOptionPane.showMessageDialog(null, "Estudantes Excluidos: "+"\n"+concat);
        }else{
            JOptionPane.showMessageDialog(null, "Sem Estudantes Excluidos");
        }
    }
    
    public static void modaMedias(ArrayList<Estudante> estudante){
        
        int contador = 1;
        int contadorTemporario;
        int temporario;
        int moda = (int) estudante.get(0).getMedia();
        if (Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))) {
            if (!estudante.isEmpty()) {
                try {
                    for (int i = 0; i < estudante.size()-1 ; i++) {
                        temporario  = (int) estudante.get(i).getMedia();
                        contadorTemporario = 0;
                        for (int j = 1; j<estudante.size(); j++){
                            if(temporario == (int) estudante.get(j).getMedia()){
                                contadorTemporario++;
                            }if(contadorTemporario> contador){
                                moda  = temporario;
                                contador = contadorTemporario;
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Moda: "+ moda);
    } 

}

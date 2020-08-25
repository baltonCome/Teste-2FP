package Controllers;

import Models.Professor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mr. Savagery
 */
public class ProfControl {
    
    public static void guardar(ArrayList<Professor> professor) throws IOException, ClassNotFoundException{

        if (!professor.isEmpty()){
            try (FileOutputStream fos = new FileOutputStream("src/Files/DadosDoProfessor.dat")){
                try (ObjectOutputStream oos =  new ObjectOutputStream(fos)){

                    oos.writeObject(professor);oos.flush();oos.close();
                    fos.flush();fos.close();
                }catch (FileNotFoundException e){
                    JOptionPane.showMessageDialog(null, "Erro ao Gravar, "+e);
                }
            }catch (IOException e){
                JOptionPane.showMessageDialog(null, "Erro ao Gravar, "+e);
            }
        }
    }

    public static ArrayList <Professor> exibe () throws  IOException, ClassNotFoundException{

        try (FileInputStream fis = new FileInputStream("src/Files/DadosDoProfessor.dat")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)){
                return (ArrayList<Professor>) ois.readObject();
            }
        }
    }
    
}

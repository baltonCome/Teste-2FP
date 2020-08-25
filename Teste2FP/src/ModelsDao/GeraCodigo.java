package ModelsDao;

import Models.Estudante;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GeraCodigo {
    
    
    public static int codigo() throws IOException{
        
        if(!Files.exists(Paths.get("src/Files/Counter.txt"))){
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/Counter.txt", true));
            bw.write("1");
            bw.flush();
            bw.close();
        }
        
        File actual = new File("src/Files/Counter.txt");
        File next = new File("src/Files/Next.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(actual));
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(next));
        
        String busca ="";
        String line = br.readLine();
        while (line != null){
            busca+=line;
            line = br.readLine();
        }
        int numero = Integer.parseInt(busca);
         
        numero+=1;
        br.close();
        
        bw1.write(numero+"");
        bw1.flush();
        bw1.close();
        
        actual.delete();
        next.renameTo(actual);
        return numero;
    }
    
    public static String situacao(double nota1, double nota2){
        String s ="";
        if (((nota1+nota2)/2)<9.5){
            s = "Excluido";
        }else if(((nota1+nota2)/2)>=9.5 && ((nota1+nota2)/2)<14){
            s = "Admitido";
        }else if(((nota1+nota2)/2)>=14){
            s = "Dispensado";
        }
        return s;
    }
}

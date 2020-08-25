package View;

import Controllers.ProfControl;
import Models.Professor;
import ModelsDao.GeraCodigo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ProfRegisterForm extends JFrame implements ActionListener{
    
    JLabel nome = new JLabel("Nome");
    JTextField nomeField = new JTextField();
    
    String [] generos = {"Masculino", "Feminino"};
    JLabel genero = new JLabel("Genero");
    JComboBox <String> generoBox = new JComboBox<>(generos);
    
    String [] grau = {"Licenciado", "Mestrado", "Doutorado", "PHD"};
    JLabel grauAcademico = new JLabel("Grau Academico");
    JComboBox <String> grauA = new JComboBox<>(grau);
    
    JPanel painel = new JPanel();
    JPanel painel2 = new JPanel();
    JLabel dados = new JLabel("DADOS PESSOAIS");
    JLabel formacao = new JLabel("FORMACAO");
    
    JButton registar = new JButton("Registar");
    JButton cancelar = new JButton("Cancelar");
    
    public ProfRegisterForm(){
        
        setTitle("Registo");
        setVisible(true);
        setPreferredSize(new Dimension(500,250));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        
        nome.setBounds(15,50,50,20);
        nomeField.setBounds(60,50,160,20);
        add(nome);
        add(nomeField);
        
        genero.setBounds(260,50,50,20);
        generoBox.setBounds(320,50,140,20);
        add(genero);
        add(generoBox);
        
        registar.setBackground(new Color(134,134,144 ));
        registar.setBounds(75,170,170,30);
        add(registar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(250,170,170,30);
        add(cancelar);
        
        dados.setBounds(190,10,130,30);
        formacao.setBounds(220,80,100,30);
        add(dados);
        add(formacao);
        
        grauAcademico.setBounds(40,125,120,20);
        add(grauAcademico);
        grauA.setBounds(150,125,120,20);
        add(grauA);
        
        painel2.setBackground(Color.gray);
        painel.setBackground(Color.gray);
        painel.setBounds(40,10,400,30);
        painel2.setBounds(40,80,400,30);
        add(painel);
        add(painel2);
        registar.addActionListener(this);
        cancelar.addActionListener(this);
        pack();
    }
    
    @Override
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== registar){
            ArrayList <Professor> professor = new ArrayList<>();
            int codigo = 0;
            try {
                codigo = GeraCodigo.codigo();
            } catch (IOException ex) {
                Logger.getLogger(ProfRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Files.exists(Paths.get("src/Files/DadosDoProfessor.dat"))){
                try{
                    if(!ProfControl.exibe().isEmpty()){
                        professor = ProfControl.exibe();
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ProfRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Professor profe = new Professor((String) grauA.getSelectedItem(), nomeField.getText(), (String) generoBox.getSelectedItem(), codigo);
                professor.add(profe);
                ProfControl.guardar(professor);
                dispose();
                JOptionPane.showMessageDialog(null, "Professor Salvo"+"\nCodigo: "+codigo);
                new Menu().professores();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== cancelar){
            dispose();
            try {
                new Menu().professores();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProfRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

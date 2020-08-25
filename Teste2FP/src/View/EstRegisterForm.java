package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controllers.EstControl;
import Models.Estudante;
import ModelsDao.GeraCodigo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;


public class EstRegisterForm extends JFrame implements ActionListener{
    
    JLabel nome = new JLabel("Nome");
    JTextField nomeField = new JTextField();
    
    String [] generos = {"Masculino", "Feminino"};
    JLabel genero = new JLabel("Genero");
    JComboBox <String> generoBox = new JComboBox<>(generos);
    
    JLabel nota1 = new JLabel("Nota 1");
    JTextField nota1Field = new JTextField();
    
    JLabel nota2 = new JLabel("Nota 2");
    JTextField nota2Field = new JTextField();
    
    JPanel painel = new JPanel();
    JPanel painel2 = new JPanel();
    JLabel dados = new JLabel("DADOS PESSOAIS");
    JLabel notas = new JLabel("NOTAS");
    
    JButton registar = new JButton("Registar");
    JButton cancelar = new JButton("Cancelar");
    
    
    public EstRegisterForm(){
        
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
        
        nota1.setBounds(15,125,50,20);
        add(nota1);
        nota1Field.setBounds(60,125,160,20);
        add(nota1Field);
        
        nota2.setBounds(260,125,50,20);
        add(nota2);
        nota2Field.setBounds(310,125,160,20);
        add(nota2Field);
        
        registar.setBackground(new Color(134,134,144 ));
        registar.setBounds(75,170,170,30);
        add(registar);
        
        cancelar.setBackground(new Color(134,134,144 ));
        cancelar.setBounds(250,170,170,30);
        add(cancelar);
        
        dados.setBounds(190,10,130,30);
        notas.setBounds(220,80,100,30);
        add(dados);
        add(notas);
        
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
            ArrayList <Estudante> estudante = new ArrayList<>();
            int codigo = 0;
            try {
                codigo = GeraCodigo.codigo();
            } catch (IOException ex) {
                Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                String situacao = GeraCodigo.situacao(Double.parseDouble(nota1Field.getText()), Double.parseDouble(nota2Field.getText()));
                Estudante estu = new Estudante(Double.parseDouble(nota1Field.getText()), Double.parseDouble(nota2Field.getText()),situacao, nomeField.getText(), (String) generoBox.getSelectedItem(), codigo);
                estudante.add(estu);
                EstControl.guardar(estudante);
                dispose();
                JOptionPane.showMessageDialog(null, "Estudante Salvo"+"\nCodigo: "+codigo);
                new Menu().estudantes();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== cancelar){
            dispose();
            try {
                new Menu().estudantes();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

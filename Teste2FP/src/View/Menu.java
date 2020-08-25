package View;

import Controllers.EstControl;
import Controllers.ProfControl;
import Models.Estudante;
import Models.Professor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Menu extends JFrame implements ActionListener {
    
    ArrayList <String> est = new ArrayList<>();
    ArrayList <String> prof = new ArrayList<>();
    
    JLabel menuGeral = new JLabel("MENU DO SISTEMA");
    JLabel userIcon = new JLabel(new ImageIcon("src/Files/Icons/user.png"));
    JButton estBtn = new JButton("Estudantes", new ImageIcon("src/Files/Icons/estudante.png"));
    JButton profBtn = new JButton("Professores", new ImageIcon("src/Files/Icons/professor.png"));
    JButton sairBtn = new JButton("Sair", new ImageIcon("src/Files/Icons/exit.png"));
    
    JButton regEstBtn = new JButton("Registar", new ImageIcon("src/Files/Icons/registar.png"));
    JButton mediaBtn = new JButton("Media Geral", new ImageIcon("src/Files/Icons/average.png"));
    JButton modaBtn = new JButton("Moda das Medias", new ImageIcon("src/Files/Icons/pattern.png"));
    JButton estDBtn = new JButton("Dispensados", new ImageIcon("src/Files/Icons/dismissed.png"));
    JButton estABtn = new JButton("Admitidos", new ImageIcon("src/Files/Icons/admited.png"));
    JButton estEBtn = new JButton("Excluidos", new ImageIcon("src/Files/Icons/excluded.png"));
    
    JPanel upColorPanel = new JPanel();
    JLabel estudantesLabel = new JLabel("Estudantes");
    JLabel estIconLabel = new JLabel(new ImageIcon("src/Files/Icons/estudante.png"));
    
    JPanel profUpColorPanel = new JPanel();
    JLabel professoresLabel = new JLabel("Professores");
    JLabel profIconLabel = new JLabel(new ImageIcon("src/Files/Icons/professor.png"));
    
    JButton regProfBtn = new JButton("Registar", new ImageIcon("src/Files/Icons/profRegistar.png"));
    
    JPanel listPane = new JPanel();
    JLabel lista = new JLabel("LISTA DE ESTUDANTES");
    
    public Menu(){
        
        setTitle("TESTE 2 FP. Balton Come");
        setVisible(true);
        setPreferredSize(new Dimension(800,380));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
        setBackground(new Color(27,33,33));
        
        userIcon.setBounds(20,5,150,70);
        add(userIcon);
        menuGeral.setBounds(40,70,120,20);
        add(menuGeral);
        
        estBtn.setBackground(new Color(134,134,144 ));
        estBtn.setBounds(5,120,200,50);
        add(estBtn);
        
        profBtn.setBackground(new Color(134,134,144 ));
        profBtn.setBounds(5,200,200,50);
        add(profBtn);
        
        sairBtn.setBackground(new Color(134,134,144 ));
        sairBtn.setBounds(5,280,200,50);
        add(sairBtn);
        
        estBtn.addActionListener(this);
        profBtn.addActionListener(this);
        sairBtn.addActionListener(this);
        
        regEstBtn.addActionListener(this);
        mediaBtn.addActionListener(this);
        modaBtn.addActionListener(this);
        estDBtn.addActionListener(this);
        estABtn.addActionListener(this);
        estEBtn.addActionListener(this);
        
        regProfBtn.addActionListener(this);
        
        pack();
    }
    
    boolean[] cellEditable = {false, false, false, false, false, false, false, false}; 
    DefaultTableModel colunas = new DefaultTableModel();
    JTable tabela;
    JScrollPane scroll;   
    
    public void estudantes() throws ClassNotFoundException{
        
        add(estudantesLabel);
        estudantesLabel.setBounds(300,5,100,50);
        add(estIconLabel);
        estIconLabel.setBounds(230,5,50,50);
        add(upColorPanel);
        upColorPanel.setBounds(230,5,540,50);
        upColorPanel.setBackground(Color.gray);
        add(lista);
        lista.setBounds(435,150,150,25);
        add(listPane);
        listPane.setBounds(230,150,540,25);
        listPane.setBackground(Color.gray);
       
        regEstBtn.setBackground(new Color(134,134,144 ));
        regEstBtn.setBounds(230,70,170,30);
        add(regEstBtn);
        
        mediaBtn.setBackground(new Color(134,134,144 )); 
        mediaBtn.setBounds(415,70,170,30);
        add(mediaBtn);
        
        modaBtn.setBackground(new Color(134,134,144 ));
        modaBtn.setBounds(600,70,170,30);
        add(modaBtn);
        
        estDBtn.setBackground(new Color(134,134,144 ));
        estDBtn.setBounds(230,110,170,30);
        add(estDBtn);
        
        estABtn.setBackground(new Color(134,134,144 ));
        estABtn.setBounds(415,110,170,30);
        add(estABtn);
        
        estEBtn.setBackground(new Color(134,134,144 ));
        estEBtn.setBounds(600,110,170,30);
        add(estEBtn);
        
        colunas.addColumn("Codigo");
        colunas.addColumn("Nome");
        colunas.addColumn("Genero");
        colunas.addColumn("Nota 1");
        colunas.addColumn("Nota 2");
        colunas.addColumn("Media");
        colunas.addColumn("Situacao");
        
        tabela = new JTable(colunas) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> est_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        est_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    est = est_;
                }
            }
        });
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn size = tabela.getColumnModel().getColumn(i);
            size.setPreferredWidth(150);
            size.setMaxWidth(200);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll = new JScrollPane(tabela);
        tabela.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scroll.setBounds(230,180, 540, 160);
        
        dadosTabela();
        add(scroll);
        
        pack();
    }
    
    private void dadosTabela() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Estudante> estudante = new ArrayList<>();
        try {           
            estudante = EstControl.exibe();
            if(!estudante.isEmpty()){
                for (int i = 0; i < estudante.size(); i++) {
                    model.addRow(new String[]{
                        String.valueOf(estudante.get(i).getCodigo()),
                        estudante.get(i).getNome(),
                        estudante.get(i).getGenero(),
                        String.valueOf(estudante.get(i).getNota1()),
                        String.valueOf(estudante.get(i).getNota2()), 
                        String.valueOf(estudante.get(i).getMedia()),
                        estudante.get(i).getSituacao()
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    }  
    
    public void professores() throws ClassNotFoundException{
        
        JLabel profLista = new JLabel("LISTA DE PROFESSORES");
        
        add(professoresLabel);
        professoresLabel.setBounds(300,5,100,50);
        add(profIconLabel);
        profIconLabel.setBounds(230,5,50,50);
        add(profUpColorPanel);
        profUpColorPanel.setBounds(230,5,540,50);
        profUpColorPanel.setBackground(Color.gray);
        
        add(profLista);
        profLista.setBounds(435,110,150,25);
        add(listPane);
        listPane.setBounds(230,110,540,25);
        listPane.setBackground(Color.gray);
        profLista.setBounds(435,110,150,25);
        
        regProfBtn.setBackground(new Color(134,134,144 ));
        regProfBtn.setBounds(230,70,170,30);
        add(regProfBtn);
        
        colunas.addColumn("Codigo");
        colunas.addColumn("Nome");
        colunas.addColumn("Genero");
        colunas.addColumn("Grau Academico");
        
        tabela = new JTable(colunas) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return cellEditable[columnIndex];
            }
        };
        
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(tabela.getSelectedRow() >= 0){
                    ArrayList<String> prof_ = new ArrayList<>();
                    for(int i = 0; i < tabela.getColumnCount(); i++){
                        prof_.add((String) colunas.getValueAt(tabela.getSelectedRow(), i));
                    }
                    prof = prof_;
                }
            }
        });
        
        for(int i = 0; i < tabela.getColumnCount(); i++){
            TableColumn size = tabela.getColumnModel().getColumn(i);
            size.setPreferredWidth(150);
            size.setMaxWidth(200);
        }
        tabela.setAutoResizeMode(AUTO_RESIZE_OFF);
        scroll = new JScrollPane(tabela);
        tabela.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scroll.setBounds(230,140, 540, 200);
        
        dadosTabelaProf();
        add(scroll);
        
        pack();
    }
    
     private void dadosTabelaProf() throws ClassNotFoundException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        ArrayList <Professor> professor = new ArrayList<>();
        try {           
            professor = ProfControl.exibe();
            if(!professor.isEmpty()){
                for (int i = 0; i < professor.size(); i++) {
                    model.addRow(new String[]{
                        String.valueOf(professor.get(i).getCodigo()),
                        professor.get(i).getNome(),
                        professor.get(i).getGenero(),
                        professor.get(i).getGrauAcademico()
                    }); 
                }
            }            
        } catch (IOException ex) {
        }
    } 
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== estBtn){
            try {
                dispose();
                new Menu().estudantes();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== profBtn){
            try {
                dispose();
                new Menu().professores();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ae.getSource()== sairBtn){
            dispose();
        }else if(ae.getSource()== regEstBtn){
            dispose();
            new EstRegisterForm();
        }else if(ae.getSource()== mediaBtn){
            ArrayList <Estudante> estudante = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                        EstControl.mediaGeral(estudante);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem dados registados");
            }
        }else if(ae.getSource()== estDBtn){
            ArrayList <Estudante> estudante = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                        EstControl.estudantesDispensados(estudante);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem dados registados");
            }
        }else if(ae.getSource()== estABtn){
            ArrayList <Estudante> estudante = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                        EstControl.estudantesAdmitidos(estudante);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem dados registados");
            }
        }else if(ae.getSource()== estEBtn){
            ArrayList <Estudante> estudante = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                        EstControl.estudantesExcluidos(estudante);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem dados registados");
            }
        }else if(ae.getSource()== regProfBtn){
            dispose();
            new ProfRegisterForm();
        }else if (ae.getSource()== modaBtn){
            ArrayList <Estudante> estudante = new ArrayList<>();
            if(Files.exists(Paths.get("src/Files/DadosDoEstudante.dat"))){
                try{
                    if(!EstControl.exibe().isEmpty()){
                        estudante = EstControl.exibe();
                        EstControl.modaMedias(estudante);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(EstRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Sem dados registados");
            }
        }
    }
    
    public static void main(String[]args){
        new Menu();
    }
}

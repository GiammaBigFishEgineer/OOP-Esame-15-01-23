package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final List<JButton> cells = new ArrayList<>();
    private List<Integer> cellsSetted = new ArrayList<>();

    protected Boolean checkConsecutiveButtons(List<Integer> list){
        for(int i = 0; i < list.size() - 2; i++){
            if(list.size() >= 3){
                if(list.get(i) + 6 == list.get(i + 1) && list.get(i) + 12 == list.get(i + 2)){
                    return true;
                }
                if(list.get(i) + 4 == list.get(i + 1) && list.get(i) + 8 == list.get(i + 2)){
                    return true;
                }
            }
            
        }
        System.out.println(list);
        return false;
    }
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.indexOf(button);
                Boolean check = false;
                if(button.getText().equals("") || button.getText().isBlank()){
                    button.setText("*");
                    cellsSetted.add(position);
                    if(checkConsecutiveButtons(cellsSetted)){
                        System.exit(0);
                    }
                    System.out.println(check);
                }else{
                    button.setText("");
                }
                
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
}

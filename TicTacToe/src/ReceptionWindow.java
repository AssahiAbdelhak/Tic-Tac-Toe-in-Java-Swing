import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionWindow implements ActionListener {
    JFrame reception;
    JButton onePlayer,twoPlayer;

    boolean doesComputerPlay;
    ReceptionWindow(){
        reception = new JFrame();


        reception.setSize(500,500);
        reception.setLayout(null);
        reception.setLocationRelativeTo(null);
        reception.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        onePlayer = new JButton("1 Player");
        onePlayer.setBounds(100,100,300,100);
        onePlayer.setFocusable(false);
        onePlayer.setBackground(Color.decode("#CFCAA8"));
        onePlayer.setForeground(Color.decode("#635E87"));
        onePlayer.addActionListener(this);
        twoPlayer = new JButton("2 Players");
        twoPlayer.setBounds(100,300,300,100);
        twoPlayer.setFocusable(false);
        twoPlayer.setBackground(Color.decode("#FFD662"));
        twoPlayer.setForeground(Color.decode("#00539C"));
        twoPlayer.addActionListener(this);

        reception.add(onePlayer);
        reception.add(twoPlayer);
        reception.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==onePlayer){
            doesComputerPlay = true;
            String name1 = JOptionPane.showInputDialog("Type the name of the first player ?" );
            reception.dispose();
            new TicTacToe(doesComputerPlay,name1,"computer");
        }
        else if(e.getSource()==twoPlayer){
            String name1 = JOptionPane.showInputDialog("Type the name of the first player ?" );
            String name2 = JOptionPane.showInputDialog("Type the name of the second player ?" );
            reception.dispose();
            new TicTacToe(doesComputerPlay,name1,name2);
        }
    }
}

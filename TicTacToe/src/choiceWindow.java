import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choiceWindow implements ActionListener {
    JFrame window;
    JButton tryAgain,quit;
    final boolean doesComputerPlays;
    String name1;
    String name2;
    choiceWindow(Class c,final boolean computerStatus,String nam1,String nam2){
        doesComputerPlays = computerStatus;
        name1 = nam1;
        name2 = nam2;
        window = new JFrame();
        window.setSize(400,300);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setTitle("Your choice");
        window.setLayout(null);

        tryAgain = new JButton("Try Again");
        tryAgain.setBounds(50,100,150,50);
        tryAgain.setFocusable(false);
        tryAgain.addActionListener(this);
        quit = new JButton("Quit the game");
        quit.setBounds(200,100,150,50);
        quit.setFocusable(false);
        quit.addActionListener(this);
        window.add(tryAgain);
        window.add(quit);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==tryAgain){
            for(Frame frame:JFrame.getFrames()){
                frame.dispose();
            }
                new TicTacToe(doesComputerPlays,name1,name2);
        }
        if(e.getSource()==quit){
            int response = JOptionPane.showConfirmDialog(null,"Ate you sure about that ? ","confirm",JOptionPane.YES_NO_OPTION,JOptionPane.YES_NO_OPTION);

            for(Frame frame:JFrame.getFrames()){
                frame.dispose();
            }
            if(response==1)
                new TicTacToe(doesComputerPlays,name1,name2);
        }
    }
}

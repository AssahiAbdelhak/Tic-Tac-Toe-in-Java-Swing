import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe implements ActionListener {

    JFrame frame = new JFrame();
    JLabel board = new JLabel();
    JPanel scoreBoard;
    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();

    JPanel container = new JPanel();
    JButton buttons[] = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    int tour = 0; // set it to random
    static int player1Score = 0;
    static int player2Score = 0;

    ArrayList<Integer> winsButtons = new ArrayList<>();
    ReceptionWindow receptionWindow;
    String name1;
    String name2;
    final boolean doesComputerPlays;
    TicTacToe(final boolean computerStatus,String nam1,String nam2){
        doesComputerPlays = computerStatus;
        name1 = nam1;
        name2 = nam2;
        frame.setTitle("Tic Tac Toe");
        frame.setSize(600,700);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board.setText("Tic-Tac-Toe");
        board.setFont(new Font("MV Boli",Font.PLAIN,50));
        board.setBackground(Color.BLACK);
        board.setOpaque(true);
        board.setForeground(Color.GREEN);
        board.setBounds(0,0,600,100);
        board.setHorizontalAlignment(JLabel.CENTER);

        scoreBoard = new JPanel();
        scoreBoard.setBounds(0,100,600,100);
        scoreBoard.setLayout(null);

        score1.setBounds(0,0,300,100);
        score1.setText(name1+" : "+player1Score);
        score1.setFont(new Font("MV Boli",Font.BOLD,40));
        score1.setBackground(new Color(0, 184, 148));
        score1.setForeground(new Color(85, 239, 196));
        score1.setHorizontalAlignment(JLabel.CENTER);

        score2.setBounds(300,0,300,100);
        score2.setText(name2+" : "+player2Score);
        score2.setFont(new Font("MV Boli",Font.BOLD,40));
        score2.setBackground(new Color(0, 184, 148));
        score2.setForeground(new Color(85, 239, 196));
        score2.setHorizontalAlignment(JLabel.CENTER);

        scoreBoard.add(score1);
        scoreBoard.add(score2);

        container.setBounds(0,200,600,450);
        container.setLayout(new GridLayout(3,3));

        for(int i = 0 ; i < buttons.length ; i++){
            container.add(buttons[i]);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }


        frame.add(container);
        frame.add(board);
        frame.add(scoreBoard);
        frame.setVisible(true);

    }
    /*
    // 3la hssab choice

    dir while(){player 1 , player 2}
    sinone while(){player computer}
     */
    public void playerTurn(int tour, int buttonNumber){
        buttons[buttonNumber].setFont(new Font("MV Boli",Font.BOLD,50));
        if(tour%2==0){
            buttons[buttonNumber].setText("X");
            buttons[buttonNumber].setForeground(Color.RED);
        }
        else{
            buttons[buttonNumber].setText("O");
            buttons[buttonNumber].setForeground(Color.BLUE);
        }
        this.tour += 1;
    }
    public void computerTurn(){
        ArrayList<Integer> availableButtons = new ArrayList<Integer>();
        for(int i = 0 ; i < buttons.length ; i++){
            if(buttons[i].getText() == ""){
                availableButtons.add(i);
            }
        }
        if(availableButtons.size()==0)
            return;
        int random = (int) (Math.random() * availableButtons.size());
        buttons[availableButtons.get(random)].setFont(new Font("MV Boli",Font.BOLD,50));
        buttons[availableButtons.get(random)].setText("O");
        buttons[availableButtons.get(random)].setForeground(Color.BLUE);
        this.tour += 1;
    }
    public void write(int tour, int buttonNumber, boolean doesComputerPlays){
        boolean doesHeWin;
        if(doesComputerPlays){
            playerTurn(tour,buttonNumber);
            doesHeWin = validate();
            printText(doesHeWin,this.tour,doesComputerPlays);
            if(doesHeWin){
                markTheWin(winsButtons);
                new choiceWindow(this.getClass(),doesComputerPlays,name1,name2);
                return;
            }
            computerTurn();
            doesHeWin = validate();
            printText(doesHeWin,this.tour,doesComputerPlays);
            if(doesHeWin){
                markTheWin(winsButtons);
                new choiceWindow(this.getClass(),doesComputerPlays,name1,name2);
                return;
            }
        }
        else{

            playerTurn(this.tour,buttonNumber);
            doesHeWin = validate();
            printText(doesHeWin,this.tour,doesComputerPlays);
            if(doesHeWin){
                markTheWin(winsButtons);
                new choiceWindow(this.getClass(),doesComputerPlays,this.name1,this.name2);
            }
        }
//        buttons[buttonNumber].setEnabled(false);
    }

    private void markTheWin(ArrayList<Integer> winsButtons) {
        for(int i = 0 ; i < buttons.length ; i++)
            buttons[i].setEnabled(false);
        for(int i : winsButtons) {
            buttons[i].setBackground(Color.GREEN);
            buttons[i].setOpaque(true);
        }
    }

    private boolean isDraw() {
        boolean draw = true;
        for (int i = 0; i <buttons.length ; i++){
            if(buttons[i].getText().isEmpty()){
                draw = false;
            }
        }
        return draw;
    }

    public boolean validate(){
        for(int i = 0 ; i < 3 ; i++){
            String c = buttons[i*3].getText();
            boolean isItEqual = true;
            winsButtons.add(i*3);
            for(int j = 0 ; j < 3 ; j++){
                if(!buttons[i*3 + j].getText().equals(c)||c.isEmpty()){
                    winsButtons.clear();
                    isItEqual = false;
                    break;
                }
                winsButtons.add(i*3 + j);
            }
            if(isItEqual)
                return true;

            isItEqual = true;
            winsButtons.clear();
            c = buttons[i].getText();
            for(int j = 0 ; j < 3 ; j++){
                if(!buttons[i + j*3].getText().equals(c)||c.isEmpty()){
                    isItEqual = false;
                    break;
                }
                winsButtons.add(i+j*3);
            }
            if(isItEqual)
                return true;

            winsButtons.clear();
            isItEqual = true;
            c = buttons[0].getText();
            for(int j = 0 ; j < 9 ; j+=4){
                if(!buttons[j].getText().equals(c)||c.isEmpty()){
                    isItEqual = false;
                    break;
                }
                winsButtons.add(j);
            }
            if(isItEqual)
                return true;

            winsButtons.clear();
            isItEqual = true;
            c = buttons[2].getText();
            for(int j = 2 ; j < 8 ; j+=2){
                if(!buttons[j].getText().equals(c)||c.isEmpty()){
                    isItEqual = false;
                    break;
                }
                winsButtons.add(j);
            }
            if(isItEqual)
                return true;
            winsButtons.clear();
        }return false;
    }

    void printText(boolean doesHeWin,int tour,boolean doesComputerPlays){
        if (doesHeWin) {
            String text = (doesComputerPlays&&tour%2==0) ? "I won" : (doesComputerPlays&&tour%2==1)? "You won bravo!!!" : "" ;
            text = (!doesComputerPlays && tour%2==1) ? "Player 1 won" : (!doesComputerPlays && tour%2==0)? "Player 2 won" : text;
            System.out.println("one time");
            if(tour%2==1)
                player1Score++;
            else
                player2Score++;
            board.setText(text);
            markTheWin(winsButtons);

        }
        else {
            if (isDraw()) {
                board.setText("Draw");
            } else{
                if(doesComputerPlays)
                    board.setText("Your turn");
                else{
                    if(tour%2==0)
                        board.setText("Player1's turn");
                    else if(tour%2==1)
                        board.setText("Player2's turn");
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0 ; i < buttons.length ; i++){
            if(e.getSource()==buttons[i]){
                if(buttons[i].getText()!=""){
                }
                else{
                    write(tour,i,doesComputerPlays);
                }
            }
        }
    }
}

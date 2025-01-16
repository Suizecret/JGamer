import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {

    JFrame frame = new JFrame("JGamer");
    JPanel panel = new JPanel();
    JPanel menu_Panel = new JPanel();
    JPanel NG_Panel = new JPanel();
    JPanel NG_Guessing_Panel = new JPanel();
    JPanel NG_Win_panel = new JPanel();
    JPanel RPS_Panel = new JPanel();
    JPanel RPS_Play_Panel = new JPanel();
    JPanel RPS_End_Panel = new JPanel();
    JButton NG_Button = new JButton("Number Guesser");
    JButton RPS_Button = new JButton("Rock Paper Scissors");
    JPanel cards = new JPanel(new CardLayout());
    CardLayout c1 ;

    State state = State.MENU;
    Font myFont = new Font("DialogInput", Font.BOLD, 15);

    NGGame ngGame = new NGGame(myFont);
    RPSGame rpsGame = new RPSGame(myFont);

    Gui(){

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500,200);
    frame.setLayout(null);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    NG_Button.addActionListener(this);
    NG_Button.setFont(myFont);
    NG_Button.setFocusable(false);
    NG_Button.setActionCommand("NG");

    RPS_Button.addActionListener(this);
    RPS_Button.setFont(myFont);
    RPS_Button.setFocusable(false);
    RPS_Button.setActionCommand("RPS");

    menu_Panel.setLayout(new GridLayout(2,1,10,10));
    menu_Panel.add(NG_Button);
    menu_Panel.add(RPS_Button);

    cards.add(menu_Panel, "Menu");
    cards.add(NG_Panel,"Number Guesser");
    cards.add(NG_Guessing_Panel,"NG guessing");
    cards.add(NG_Win_panel,"NG Win");
    cards.add(RPS_Panel, "Rock Paper Scissors");
    cards.add(RPS_Play_Panel,"RPS Play");
    cards.add(RPS_End_Panel,"RPS End");
    c1 = (CardLayout)(cards.getLayout());

    ngGame.winPanel(NG_Win_panel,this);
    rpsGame.endPanel(RPS_End_Panel,this);
    drawFrame();
    }

    void drawFrame() {
        panel.setBounds(0,0,500,200);
        switch (state){
            case MENU:{
                c1.show(cards,"Menu");
                break;
            }
            case NG:{
                c1.show(cards,"Number Guesser");
                break;
            }
            case NGGUESSING:{
                c1.show(cards,"NG guessing");
                break;
            }
            case NG_WIN:{
                c1.show(cards, "NG Win");
                break;
            }
            case RPS:{
                c1.show(cards,"Rock Paper Scissors");
                break;
            }
            case RPS_PLAY:{
                rpsGame.chooseWeaponPanel(RPS_Play_Panel,this);
                rpsGame.draw = false;
                c1.show(cards, "RPS Play");
                break;
            }
            case RPS_END:{
                rpsGame.endPanel(RPS_End_Panel,this);
                c1.show(cards,"RPS End");
                break;
            }
            default:
                throw new IllegalArgumentException("There is no State named: " + state);
        }
        panel.add(cards, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "NG":{
                state = State.NG;
                ngGame.minmaxPanel(NG_Panel,this);
                drawFrame();
                return;
            }
            case "NG_Start":{
                state = State.NGGUESSING;
                ngGame.setup_guessing();
                ngGame.guessPanel(NG_Guessing_Panel,this);
                ngGame.draw = false;
                drawFrame();
                return;
            }
            case "RPS":{
                state = State.RPS;
                rpsGame.chooseAmountPanel(RPS_Panel,this);
                drawFrame();
                return;
            }
            default: throw new RuntimeException("BUTTON ACTION NOT FOUND");
        }
    }
}


class Main {
    public static void main(String[] args) {
        new Gui();
    }
}
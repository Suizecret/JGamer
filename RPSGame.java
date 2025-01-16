import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;

public class RPSGame {
    Boolean draw = true;
    Font myFont;
    int player_amount;
    int count = 0;
    RPS player1;
    int winner;

    JLabel win_Label = new JLabel();
    JLabel start_weapon_Label = new JLabel();

    RPSGame(Font myFont){
        this.myFont = myFont;
    }

    public void chooseAmountPanel(JPanel panel, Gui gui){
        if (draw){
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;

            JLabel RPS_start_label = new JLabel("What mode would you like to play:");
            c.gridx = 0;
            c.gridwidth = 2;
            c.gridy = 0;
            RPS_start_label.setFont(myFont);
            panel.add(RPS_start_label,c);

            JButton RPS_PVP_Button = new JButton();
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 2;
            c.anchor = GridBagConstraints.CENTER;
            RPS_PVP_Button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player_amount = 2;
                    count = 0;
                    gui.state = State.RPS_PLAY;
                    gui.drawFrame();
                }
            });
            RPS_PVP_Button.setText("PVP");
            //RPS_PVP_Button.addActionListener(gui);
            RPS_PVP_Button.setFont(myFont);
            RPS_PVP_Button.setFocusable(false);
            //RPS_PVP_Button.setActionCommand("PVP");
            panel.add(RPS_PVP_Button,c);

            JButton RPS_PVE_Button = new JButton();
            c.gridx = 1;
            c.gridy = 2;
            c.anchor = GridBagConstraints.CENTER;
            RPS_PVE_Button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player_amount = 1;
                    gui.state = State.RPS_PLAY;
                    gui.drawFrame();
                }
            });
            RPS_PVE_Button.setText("PVE");
            //RPS_PVE_Button.addActionListener(gui);
            RPS_PVE_Button.setFont(myFont);
            RPS_PVE_Button.setFocusable(false);
            //RPS_PVE_Button.setActionCommand("PVP");
            panel.add(RPS_PVE_Button,c);
        }
    }

    public void chooseWeaponPanel(JPanel panel,Gui gui){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        String player = "";
        if (draw){
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            start_weapon_Label.setFont(myFont);
            panel.add(start_weapon_Label,c);

            JButton Rock_Button = new JButton();
            c.gridwidth = 1;
            c.gridx = 2;
            Rock_Button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player_amount == 2 && count == 1){
                        winner = RPS.ROCK.winner(player1);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }
                    else if (player_amount == 2 && count == 0 ){
                        player1 = RPS.ROCK;
                        count++;
                        chooseWeaponPanel(panel,gui);
                    }
                    else {
                        RPS cpu;
                        int random = ThreadLocalRandom.current().nextInt(0,3);
                        switch (random){
                            case 0 -> cpu = RPS.ROCK;
                            case 1 -> cpu = RPS.PAPER;
                            case 2 -> cpu = RPS.SCISSORS;
                            default -> throw new IllegalArgumentException("WRONG RANDOM OUTPUT!");
                        }
                        winner = RPS.ROCK.winner(cpu);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }
                }
            });
            Rock_Button.setText("Rock");
            Rock_Button.setFont(myFont);
            Rock_Button.setFocusable(false);
            panel.add(Rock_Button,c);

            JButton Paper_Button = new JButton();
            c.gridy = 1;
            Paper_Button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player_amount == 2 && count == 1){
                        winner = RPS.PAPER.winner(player1);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }
                    else if (player_amount == 2 && count == 0 ){
                        player1 = RPS.PAPER;
                        count++;
                        chooseWeaponPanel(panel,gui);
                    }
                    else {
                        RPS cpu;
                        int random = ThreadLocalRandom.current().nextInt(0,3);
                        switch (random){
                            case 0 -> cpu = RPS.ROCK;
                            case 1 -> cpu = RPS.PAPER;
                            case 2 -> cpu = RPS.SCISSORS;
                            default -> throw new IllegalArgumentException("WRONG RANDOM OUTPUT!");
                        }
                        winner = RPS.PAPER.winner(cpu);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }

                }
            });
            Paper_Button.setText("Paper");
            Paper_Button.setFont(myFont);
            Paper_Button.setFocusable(false);
            panel.add(Paper_Button,c);

            JButton Scissors_Button = new JButton();
            c.gridy = 2;
            Scissors_Button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player_amount == 2 && count == 1){
                        winner = RPS.SCISSORS.winner(player1);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }
                    else if (player_amount == 2 && count == 0 ){
                        player1 = RPS.SCISSORS;
                        count++;
                        chooseWeaponPanel(panel,gui);
                    }
                    else {
                        RPS cpu;
                        int random = ThreadLocalRandom.current().nextInt(0,3);
                        switch (random){
                            case 0 -> cpu = RPS.ROCK;
                            case 1 -> cpu = RPS.PAPER;
                            case 2 -> cpu = RPS.SCISSORS;
                            default -> throw new IllegalArgumentException("WRONG RANDOM OUTPUT!");
                        }
                        winner = RPS.SCISSORS.winner(cpu);
                        gui.state = State.RPS_END;
                        gui.drawFrame();
                    }

                }
            });
            Scissors_Button.setText("Scissors");
            Scissors_Button.setFont(myFont);
            Scissors_Button.setFocusable(false);
            panel.add(Scissors_Button,c);
        }

        if (player_amount == 2){
            switch (count){
                case 0 -> player = "Player One";
                case 1 -> player = "Player Two";
                default -> throw new IllegalArgumentException("There Can't be more than One weapon in this State");
            }

        }
        start_weapon_Label.setText(player +" Choose Your weapon");
    }

    public void endPanel(JPanel panel, Gui gui){
        if (draw) {
            panel.setLayout(new GridLayout());
            win_Label.setFont(myFont);
            panel.add(win_Label);

            JButton menu = new JButton();
            menu.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.state = State.MENU;
                    gui.drawFrame();
                }
            });
            menu.setText("Back To Menu");
            menu.setFocusable(false);
            menu.setFont(myFont);
            panel.add(menu);
        }

        String p2;
        if (player_amount == 2){
            p2 = "player 2";
        }
        else {
            p2 = "CPU";
        }
        switch (winner){
            case 0 -> win_Label.setText("That's a draw");
            case 1 -> win_Label.setText("Player 1 Won");
            case 2 -> win_Label.setText(p2 + " Won");
        }
    }

}

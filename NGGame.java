import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;

public class NGGame {
    Font myFont;
    int random;
    int userValue;
    int score;
    String hint;
    JTextField NG_min_TextField = new JTextField();
    JTextField NG_max_TextField = new JTextField();
    JLabel score_label= new JLabel("Possible Score: " + score);
    JLabel last_guess_label = new JLabel("Last Guess: " + userValue);
    JLabel hint_label = new JLabel("hint: " + hint);
    Boolean draw = true;

    public NGGame(Font myFont) {
        this.myFont = myFont;
    }

    public boolean check_guess(int userValue){
        this.userValue = userValue;
        if (userValue == random){
            return true;
        } else if (random > userValue) {
            hint = "to Small";
        }
        else {
            hint = "to High";
        }
        score -=10;
        return false;
    }

    public void setup_guessing(){
        int min = Integer.parseInt(NG_min_TextField.getText());
        int max = Integer.parseInt(NG_max_TextField.getText());
        if (min > max){
            max += min;
            min = max - min;
            max -= min;
            JFrame info = new JFrame("Your Values where switched");
            Label label = new Label("Your Values where switched");
            info.add(label);
            info.setSize(190,70);
            info.setLocationRelativeTo(null);
            info.setVisible(true);
        }
        random = ThreadLocalRandom.current().nextInt(min,max+1);
        score = 10*(max - min);
        NG_max_TextField.setText("");
        NG_min_TextField.setText("");
        hint = "try Guessing First!";
        userValue = 0;
    }

    public void minmaxPanel(JPanel panel, Gui gui){
        if (draw){
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;

            JLabel NG_start_label = new JLabel("Please enter the Range you want to play in.");
            c.gridx = 0;
            c.gridwidth = 2;
            c.gridy = 0;
            NG_start_label.setFont(myFont);
            panel.add(NG_start_label,c);

            JLabel NG_min_label = new JLabel("Enter Min: ");
            c.gridx = 0;
            c.gridwidth = 1;
            c.gridy = 1;
            NG_min_label.setFont(myFont);
            panel.add(NG_min_label,c);

            JLabel NG_max_label = new JLabel("Enter Max: ");
            c.gridx = 0;
            c.gridy = 2;
            NG_max_label.setFont(myFont);
            panel.add(NG_max_label,c);

            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 1;
            NG_min_TextField.setBounds(0,0,20,30);
            panel.add(NG_min_TextField,c);

            c.gridx = 1;
            c.gridy = 2;
            NG_max_TextField.setBounds(0,0,50,30);
            panel.add(NG_max_TextField,c);

            JButton NG_start_Button = new JButton("Start");
            c.gridx = 1;
            c.gridy = 4;
            c.anchor = GridBagConstraints.CENTER;
            NG_start_Button.addActionListener(gui);
            NG_start_Button.setFont(myFont);
            NG_start_Button.setFocusable(false);
            NG_start_Button.setActionCommand("NG_Start");
            panel.add(NG_start_Button,c);
        }
    }

    public void guessPanel(JPanel panel, Gui gui) {
        if (draw) {
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;

            c.gridx = 1;
            c.gridy = 0;
            panel.add(score_label, c);

            c.gridy = 1;
            panel.add(last_guess_label, c);

            c.gridy = 2;
            panel.add(hint_label, c);

            TextField user_TextField = new TextField();
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 2;
            panel.add(user_TextField, c);

            JButton take_guess_button = new JButton();
            c.gridwidth = 1;
            c.gridx = 3;
            take_guess_button.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (check_guess(Integer.parseInt(user_TextField.getText()))) {
                        user_TextField.setText("");
                        gui.state = State.NG_WIN;
                        gui.drawFrame();

                    }
                    score_label.setText("Possible Score: " + score);
                    last_guess_label.setText("Last Guess: " + userValue);
                    hint_label.setText("hint: " + hint);
                }
            });
            take_guess_button.setText("Take a Guess");
            take_guess_button.setFont(myFont);
            take_guess_button.setFocusable(false);
            panel.add(take_guess_button, c);
        }
        score_label.setText("Possible Score: " + score);
        last_guess_label.setText("Last Guess: " + userValue);
        hint_label.setText("hint: " + hint);
    }

    public void winPanel(JPanel panel, Gui gui) {
        panel.setLayout(new GridLayout());
        JLabel win_Label = new JLabel("You Won!");
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
}

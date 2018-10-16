package sliding_puzzle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Game extends JPanel implements MouseListener, ActionListener
{
    ArrayList<JPanel> button = new ArrayList<JPanel>();
    ArrayList<JPanel> defaultb;
    static int moves=0;
    public Game()
    {
        setLayout(new BorderLayout());
        JLabel num;
        Font f1 = new Font("Helvetica", Font.BOLD, 60);
        for (int i=0; i<8; i++)
        {
            button.add( new JPanel(new BorderLayout()) );
            button.get(i).setBackground(Color.GRAY);
            num = new JLabel("" + (i+1),SwingConstants.CENTER);
            num.setFont(f1);
            num.setForeground(Color.DARK_GRAY);
            button.get(i).add(num, BorderLayout.CENTER);
        }
        button.add( new JPanel() );
        button.get(8).setBackground(Color.BLACK);
        defaultb = new ArrayList<JPanel> (button);

        addPanels();
        JPanel north = new JPanel();
        JButton new_game = new JButton ("New Game");
        north.add(new_game);

        new_game.addActionListener(this);
        add(north, BorderLayout.NORTH);

        JLabel mov = new JLabel("Moves: 0");
        mov.setFont(new Font("SansSerif", Font.BOLD, 14));
        mov.setHorizontalAlignment( SwingConstants.CENTER );
        add(mov, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
        Collections.shuffle(button);
        reset();
        for (int i=0; i<9; i++)
            button.get(i).addMouseListener(this);
        JLabel lab = (JLabel) ((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        lab.setText("Moves: " + (moves=0));
        addPanels();
    }

    public void addPanels()
    {
        JPanel game = new JPanel (new GridLayout(3, 3, 12, 12));
        game.setBackground(Color.BLACK);
        Component but = ((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.CENTER);
        if (but != null)
            remove(but);
        for (int i=0; i<9; i++)
            game.add(button.get(i));
        add(game, BorderLayout.CENTER);
        validate();
        repaint();
    }

    public void reset()
    {
        for (int i=0; i<9; i++)
            button.get(i).removeMouseListener(this);
    }

    public void checkIfFinished()
    {
        if (button.equals(defaultb))
        {
            JOptionPane.showMessageDialog(null,"Well Done!!!","Message",JOptionPane.INFORMATION_MESSAGE);
            reset();
        }
    }

    public void mousePressed(MouseEvent e)
    {
        int j;
        JPanel but = (JPanel) e.getComponent();
        int i = button.indexOf(but);
        if ((j=i-3) >= 0 && button.get(j).getBackground() == Color.BLACK)
            Collections.swap(button, i, j);
        else if ((j=i-1) >= 0 && i%3!=0 && button.get(j).getBackground() == Color.BLACK)
            Collections.swap(button, i, j);
        else if ((j=i+1) <= 8 && j%3!=0 && button.get(j).getBackground() == Color.BLACK)
            Collections.swap(button, i, j);
        else if ((j=i+3) <= 8 && button.get(j).getBackground() == Color.BLACK)
            Collections.swap(button, i, j);
        else
        {
            Toolkit.getDefaultToolkit().beep();
            --moves;
        }
        JLabel lab = (JLabel) ((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        lab.setText("Moves: " + ++moves);
        addPanels();
        checkIfFinished();
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    public static void main(String[] args)
    {
        Game g = new Game();
        g.setSize(450, 450);
        g.setVisible(true);
    }
}
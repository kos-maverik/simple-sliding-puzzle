package sliding_puzzle;
import javax.swing.*;

public class MyPuzzle extends JApplet
{
    public static int count=0;

    public void start()
    {
        getContentPane().add(new Game());
    }

    public void stop()
    {
        getContentPane().removeAll();
    }

}
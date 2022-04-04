import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private int HEIGHT = 31;
    private int WIDTH = 80;
    private Tile[][] pixels = new Tile[HEIGHT][WIDTH];
    Animator animator;
    Clip c;

    {
        try {
            c = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    ;

    public void init() {
        this.setTitle("Bad Apple Java");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new GridLayout(HEIGHT, WIDTH));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        for(int i = 0; i < HEIGHT; i++) {
            for(int j = 0; j < WIDTH; j++) {
                pixels[i][j] = new Tile("0");
                this.add(pixels[i][j]);
                pixels[i][j].repaint();
            }
        }
        animator = new Animator(pixels, this, HEIGHT, WIDTH, c);
        this.setVisible(true);
    }
    public void begin() {
        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File("res\\BA.wav"));
            c.open(a);
            c.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        animator.start();
       
    }
}

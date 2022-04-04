import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Animator extends Thread{
    private Tile[][] screen;
    private String prefix = "res\\BA";
    private String suffix = ".txt";
    private int currentFrameNum = 1;
    private Tile[][] currentFrame = null;
    private JFrame context;
    Clip clip;

    public Animator(Tile[][] screenRef, JFrame context, int height, int width, Clip c) {
        screen = screenRef; // We'll use this to reference the tile array in the Window object.
        this.context = context;
        currentFrame = new Tile[height][width];
        for(int i = 0; i < height; i ++) {
            for(int j = 0; j < width; j++) {
                currentFrame[i][j] = new Tile("0");
            }
        }
        clip = c;
    }

    @Override
    public void run() {
        clip.start();
        while(currentFrameNum <= 6569) {
            getFrameFromFile();
            animate();
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void animate() {
        for(int i = 0; i < currentFrame.length; i++) {
            for (int j = 0; j < currentFrame[0].length; j++) {
                screen[i][j].pix = currentFrame[i][j].pix;
            }
        }
        context.repaint(); // Repaint the entire screen at once.

    }

    private void getFrameFromFile() {
        try {
            Scanner reader = new Scanner(new FileInputStream(prefix + Integer.toString(currentFrameNum) + suffix));
            int a = 0;
            reader.useDelimiter("");

            while(reader.hasNext()) {
                 for(int i = 0; i < screen[a].length; i++) { // Filling out rows with ASCII pixels.
                    currentFrame[a][i].pix = reader.next();
                }
                a++;
            }
            currentFrameNum++;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

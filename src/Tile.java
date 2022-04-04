import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    public String pix;
    public Tile(String iPix) {
        this.pix = iPix;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(pix, (int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.5));
    }
}

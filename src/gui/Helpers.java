package gui;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Helpers {
    public ImageIcon getRemoteImage(String path) {
        ImageIcon image = null;
        
        try {
            URL url = new URL(path);
            BufferedImage img = ImageIO.read(url);
            image = new ImageIcon(img);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return image;
    }

    public void setFontSize(JLabel label, int size) {
        Font font = label.getFont();
        label.setFont(new Font(font.getFontName(), font.getStyle(), size));
    }
}

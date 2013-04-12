package hillgiantkiller.other;

import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/28/13
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Paint {
    private static final Color color1 = new Color(255, 255, 255);
    private static final Color color2 = new Color(102, 102, 102);
    private static final Color color3 = new Color(0, 0, 0);
    private static final Color color4 = new Color(255, 0, 51);
    private static final BasicStroke stroke1 = new BasicStroke(2);
    private static final Font font1 = new Font("Arial", 0, 15);
    private static final Font font2 = new Font("Arial", 0, 18);
    private static final Image img1 = getImage("http://i.imgur.com/0Y6cxJI.jpg");

    private static SkillData sd = new SkillData();
    public static int gKilled = 0;
    public static int profit = 0;
    private static Timer runTime = new Timer(0);


    public static void drawPaint(Graphics g1, boolean hide) {
        Graphics2D g = (Graphics2D)g1;
        g.translate(0,50);
        if (!hide) {
            g.drawImage(img1, 2, 344, null);
            g.setFont(font1);
            g.setColor(color1);
            g.drawString(runTime.toElapsedString(), 106, 403);
            g.drawString("exp per hour", 98, 449);
            g.drawString("total exp", 105, 427);
            g.drawString("total profit", 308, 406);
            g.drawString("profit per hour", 310, 427);
            g.setColor(color2);
            g.fillRect(578, 1, 98, 33);
            g.setColor(color3);
            g.setStroke(stroke1);
            g.drawRect(578, 1, 98, 33);
            g.setColor(color1);
            g.drawString("status", 266, 451);
            g.setFont(font2);
            g.setColor(color4);
            g.drawString("Hide", 609, 25);
        } else {
            g.setColor(color2);
            g.fillRect(578, 1, 98, 33);
            g.setColor(color3);
            g.setStroke(stroke1);
            g.drawRect(578, 1, 98, 33);
            g.setFont(font2);
            g.setColor(color4);
            g.drawString("Show", 609, 25);
        }
    }


    private static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }




}

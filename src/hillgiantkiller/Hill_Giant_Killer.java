package hillgiantkiller;

import hillgiantkiller.nodes.*;
import hillgiantkiller.other.HillGiantGUI;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import hillgiantkiller.tasks.CheckForDying;
import hillgiantkiller.tasks.MomentumTask;
import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.client.Client;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Manifest(authors = {"Kirinsoul"}, description = "Hill Giants v2", name = "Hill Giants v2")
public class Hill_Giant_Killer extends ActiveScript implements PaintListener, MouseListener {
    private Client client = Bot.client();
    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    public static boolean guiWait = true;
    private Point p;
    private boolean hide;
    Rectangle hideBtn = new Rectangle(578,51,100,32);

    public synchronized final void provide(final Node... jobs) {
        for (final Node job : jobs) {
            if (!jobsCollection.contains(job)) {
                jobsCollection.add(job);
            }
        }
    }

    public void onStart() {

        Camera.setPitch(true);
        HillGiantGUI gui = new HillGiantGUI();
        gui.setVisible(true);
        while(guiWait){
            Task.sleep(100);
        }
        gui.dispose();
        getContainer().submit(new CheckForDying());
        if(Variables.useMomentum) getContainer().submit(new MomentumTask());
        provide(new Banking(), new ToHillGiants(), new Eat(), new Loot(), new Fight(),new UseAbilities());

    }

    public void onStop() {

        System.out.println("Goodbye!");

    }


    @Override
    public int loop() {

        if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
            return 1000;
        }

        if (client != Bot.client()) {
            WidgetCache.purge();
            Bot.context().getEventManager().addListener(this);
            client = Bot.client();
        }

        if (Game.isLoggedIn()) {
            for (Node node : jobsCollection) {
                if (node.activate()) {
                    node.execute();
                    return Random.nextInt(50, 100);
                }

            }
        }


        return Random.nextInt(50, 100);
    }

    @Override
    public void onRepaint(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(Mouse.getX() - 5, Mouse.getY() - 5, Mouse.getX() + 5, Mouse.getY() + 5);
        g.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5, Mouse.getY() - 5);
        Paint.drawPaint(g,hide);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        p = e.getPoint();
        if(hideBtn.contains(p) && !hide){
            hide = true;
        }else if(hideBtn.contains(p) && hide){
            hide = false;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}//end of class

package hillgiantkiller;

import hillgiantkiller.nodes.*;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import hillgiantkiller.other.gui2;
import hillgiantkiller.tasks.CheckForDying;
import hillgiantkiller.tasks.MomentumTask;
import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.randoms.SpinTickets;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Environment;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.client.Client;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Manifest(authors = {"__Meat"}, description = "Kills hill giants in the Edgeville dungeon",
        name = "__Meat's Hill Giant Killer", version = 1.65, topic = 982254)
public class Hill_Giant_Killer extends ActiveScript implements PaintListener, MouseListener {
    private Client client = Bot.client();
    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private static final ClaimSpinTickets ticketRandom = new ClaimSpinTickets();
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
        Paint.status = "Starting";
        getContainer().submit(new Task() {
            @Override
            public void execute() {
                sleep(5000);
                Environment.enableRandom(SpinTickets.class, false);
            }
        });
        Looting.arrowId = Equipment.getItem(Equipment.Slot.AMMO).getId();
        Camera.setPitch(true);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gui2 frame = new gui2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        while(guiWait){
            Task.sleep(100);
        }
        if(Variables.useMomentum) getContainer().submit(new MomentumTask());
        provide(new EmergancyEscape(), new Banking(), new ToHillGiants(), new Eat(), new Looting(), new Fight(), new NotMyGiant(), new UseAbilities());
        getContainer().submit(new CheckForDying());
    }

    public void onStop() {

        System.out.println("Goodbye!");

    }

    @Override
    public int loop() {

        try {
            if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
                return 1000;
            }

            if (ticketRandom.activate()) {
                ticketRandom.execute();
                return 100;
            }

            if (client != Bot.client()) {
                WidgetCache.purge();
                Bot.context().getEventManager().addListener(this);
                client = Bot.client();
            }

            if (Game.isLoggedIn()) {
                for (Node node : jobsCollection) {
                    if (node!= null && node.activate()) {
                        node.execute();
                        return Random.nextInt(50, 100);
                    }

                }
            }
            return Random.nextInt(50, 100);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return Random.nextInt(50, 100);
    }

    @Override
    public void onRepaint(Graphics g) {
        Paint.drawMouse(g);
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

    private static class ClaimSpinTickets extends SpinTickets {
        @Override
        public void execute() {
            if (Bank.isOpen()) {
                if(Bank.close()) {
                    sleep(1000);
                }
            } else if (((Settings.get(1448) & 0xFF00) >>> 8) < 10) {
                final Item item = Inventory.getItem(SpinTickets.ITEM_ID_SPIN_TICKET);
                if (item != null && item.getWidgetChild().interact("Claim spin")) {
                    sleep(1000);
                }
            } else {
                super.execute();
            }
        }
    }

}//end of class

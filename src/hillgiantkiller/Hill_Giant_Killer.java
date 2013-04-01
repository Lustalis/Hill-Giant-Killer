package hillgiantkiller;

import hillgiantkiller.looptasks.EatFood;
import hillgiantkiller.looptasks.UseAbilities;
import hillgiantkiller.nodes.FindTargetNode;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.client.Client;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:26 AM
 * To change this template use File | Settings | File Templates.
 */

@Manifest(authors = { "Kirinsoul" }, description = "Fuck wit dem hill gIants", name = "Giant fucker", version = 1.0)

public class Hill_Giant_Killer extends ActiveScript implements PaintListener {
    public static ArrayList<Node> nodeCollection = new ArrayList<>();
    private Client client = Bot.client();

    public void provide(Node node){
        if(node != null){
            nodeCollection.add(node);
        }
    }

    public void onStart(){

        System.out.println("Script start");

        //provide(new hillgiantkiller.nodes.WalkToCave());
        //provide(new hillgiantkiller.nodes.WalkToBank());
        provide(new FindTargetNode());
        getContainer().submit(new EatFood());
        getContainer().submit(new UseAbilities());
        //provide(new hillgiantkiller.nodes.LootNode());

    }

    public void onStop(){

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

        if(Game.isLoggedIn()){

            for (Node node: nodeCollection){
                if (node != null && node.activate()){
                    node.execute();
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

    }
}

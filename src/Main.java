import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.Game;
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
public class Main extends ActiveScript implements PaintListener {
    public static ArrayList<Node> nodeCollection = new ArrayList<>();
    private Client client = Bot.client();

    public void provide(Node node){
        if(node != null){
            nodeCollection.add(node);
        }
    }

    public void onStart(){


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
    public void onRepaint(Graphics graphics) {

    }
}

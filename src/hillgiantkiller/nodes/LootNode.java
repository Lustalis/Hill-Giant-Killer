package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class LootNode extends Node {
    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Var.NPC_IDS);
        return Methods.needToLoot() && !Methods.fullInv() && Methods.haveFood(Var.foodIds) && Players.getLocal().getInteracting() == null
                && x!=null && Players.getLocal().getAnimation() == -1 && Players.getLocal().getAnimation() != Var.EATING_ID
                && !Var.isAdding;
    }

    @Override
    public void execute() {
        System.out.println("In the loot node");
        Var.isLooting = true;
        System.out.println("Tiles in list: "+Var.lootLocations.size());
        for(Iterator<Tile> t = Var.lootLocations.iterator(); t.hasNext();){
            //currentIndex = Var.lootLocations.indexOf(t);
            Tile tile = t.next();
            GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());
            for(GroundItem i: item){
                for(int x: Var.lootIds){
                    if(x == i.getId()){
                        //Making sure item is on screen
                        if(!Methods.isOnScreen(i)){
                            Camera.turnTo(i);
                            Task.sleep(500);
                            if(!Methods.isOnScreen(i)){
                                Walking.walk(i);
                                Methods.waitForOnScreen(i);
                            }
                        }
                        i.interact("Take", i.getGroundItem().getName());
                        Methods.waitForInvChange(x);
                        System.out.println("Picked up: " +i.getGroundItem().getName());
                    }
                }
            }

            t.remove();
        }
        //Var.lootLocations.iterator().remove(currentIndex);
    }
}

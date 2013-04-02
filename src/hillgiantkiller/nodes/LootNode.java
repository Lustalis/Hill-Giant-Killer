package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

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
        return Methods.needToLoot() && !Methods.fullInv() && Players.getLocal().getInteracting() == null
                && x!=null && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        int currentIndex = 0;
        System.out.println("In the loot node");
        System.out.println("Tiles in list: "+Var.lootLocations.size());
        for(Tile t: Var.lootLocations){
            currentIndex = Var.lootLocations.indexOf(t);
            GroundItem[] item = GroundItems.getLoadedAt(t.getX(),t.getY());
            for(GroundItem i: item){
                for(int x: Var.lootIds){
                    if(x == i.getId()){
                        i.interact("Take", i.getGroundItem().getName());
                        System.out.println("Picked up: " +i.getGroundItem().getName());
                    }
                }
            }

        }
        Var.lootLocations.remove(currentIndex);
    }
}

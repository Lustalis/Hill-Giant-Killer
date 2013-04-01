package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
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
        return Methods.needToLoot(Var.lootFilter) && !Methods.fullInv();
    }

    @Override
    public void execute() {
        System.out.println("Picking up loot");
        GroundItem x = GroundItems.getNearest(Var.lootFilter);
        if(x != null){
            if(!x.isOnScreen()){
                Camera.turnTo(x);
                Task.sleep(1000);
                if(!x.isOnScreen()){
                    System.out.println("walking to loot");
                    Walking.walk(x);
                }

            }else{
                System.out.println("Now picking up " +x.getGroundItem().getName());
                x.interact("Take");
                Methods.waitForInvChange();
                Paint.bonesLooted++;
                Task.sleep(1500, 2000);
            }

        }else{
            System.out.println("Bad loot");
        }

    }
}

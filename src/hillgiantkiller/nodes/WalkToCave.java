package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkToCave extends Node {

    final TilePath pathToCave = Walking.newTilePath(Var.path1);
    SceneObject door = null;
    SceneObject stairs = null;

    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Var.npcIds);
        return ((Methods.haveFood(Var.foodIds) && x == null));
    }

    @Override
    public void execute() {

        if(!Var.dungArea.contains(Players.getLocal())){
            //walking to dungeon
            pathToCave.traverse();
        }else{
            //going though door
            door = SceneEntities.getNearest(Var.doorId);
            if(door != null){
                door.interact("Open");
                do{
                    Task.sleep(500,750);
                }while(!Players.getLocal().isIdle());
            }
            stairs = SceneEntities.getNearest(Var.stairsDown);
            //going down stairs
            if(stairs != null){
                stairs.interact("Climb-down");
                Methods.waitForArea(Var.insideDungArea);
            }

        }

    }
}

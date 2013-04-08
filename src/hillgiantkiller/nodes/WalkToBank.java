package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
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
 * Time: 8:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkToBank extends Node {

    private final TilePath pathToBank = Walking.newTilePath(Var.PATH_2);
    private SceneObject stairUp = null;
    public static boolean walkToBank = false;

    @Override
    public boolean activate() {
        return !Var.BANK_AREA.contains(Players.getLocal()) && (!Methods.haveFood(Var.foodId) || Methods.fullInv());
    }

    @Override
    public void execute() {
        NPC x = NPCs.getNearest(Var.NPC_IDS);

        if(!Var.DUNG_AREA.contains(Players.getLocal()) && !walkToBank){
            //takes the stairs out of of the dungeon
            System.out.println("In the dungeon; going up");
            stairUp = SceneEntities.getNearest(Var.STAIRS_UP);
            if(Calculations.distanceTo(stairUp) >=8 && stairUp != null){    //if far from stairs walk to them first
                Walking.walk(stairUp);
                Methods.waitForArea(Var.INSIDE_DUNG_AREA);
                System.out.println("Interacting with the ladder going up");
                stairUp.interact("Climb-up");
                Methods.waitForArea(Var.DUNG_AREA);
            }



        }else if(x == null && !walkToBank){
            SceneObject door = SceneEntities.getNearest(Var.DOOR_ID);
            if(door != null){
                System.out.println("Opening door");
                door.interact("Open");
                walkToBank = true;
            }

        }else{
            pathToBank.traverse();
        }

    }
}

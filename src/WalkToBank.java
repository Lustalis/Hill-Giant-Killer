import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
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

    final TilePath pathToBank = Walking.newTilePath(Var.path2);
    SceneObject stairUp = null;

    @Override
    public boolean activate() {
        return !Var.bankArea.contains(Players.getLocal()) && !Methods.haveFood(Var.foodIds);
    }

    @Override
    public void execute() {
        Var.theGiant = NPCs.getNearest(Var.npcIds);

        if(Var.theGiant != null){
            //takes the stairs out of of the dungeon
            System.out.println("In the dungeon; going up");
            stairUp = SceneEntities.getNearest(Var.stairsUp);
            if(Calculations.distanceTo(stairUp) >=8){    //if far from stairs walk to them first
                Walking.walk(stairUp);
                Methods.waitForArea(Var.insideDungArea);
            }
            System.out.println("Interacting with the stairs going up");
            stairUp.interact("Climb-up");
            Methods.waitForArea(Var.dungArea);

            SceneObject door = SceneEntities.getNearest(Var.doorId);
            door.interact("Open");
            do{
                Task.sleep(150,450);
            }while(!Players.getLocal().isIdle());

        }else{
            pathToBank.traverse();
        }

        //TODO: so far this part works!!

    }
}

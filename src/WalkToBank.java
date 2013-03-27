import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
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

    @Override
    public boolean activate() {
        return (!Var.bankArea.contains(Players.getLocal()));
    }

    @Override
    public void execute() {
        SceneObject stairUp = SceneEntities.getNearest(Var.stairsUp);

        if(stairUp != null){
            System.out.println("Stairs up is valid");
            stairUp.interact("Climb-up");
            while(!Var.dungArea.contains(Players.getLocal())){
                Task.sleep(500, 1250);
            }
            SceneObject door = SceneEntities.getNearest(Var.doorId);
            Task.sleep(500);
        }

        //TODO: make a new path cause this one fucks up
        if(pathToBank != null && pathToBank.validate()){
            pathToBank.traverse();
        }

    }
}

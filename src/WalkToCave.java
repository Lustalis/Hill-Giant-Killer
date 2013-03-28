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
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkToCave extends Node {

    final TilePath pathToCave = Walking.newTilePath(Var.path1);
    SceneObject door = null;
    SceneObject stairs = null;

    @Override
    public boolean activate() {
        return (!Var.insideDungArea.contains(Players.getLocal()));
    }

    @Override
    public void execute() {

        if(!Var.dungArea.contains(Players.getLocal())){
            pathToCave.traverse();
        }else{

            door = SceneEntities.getNearest(Var.doorId);
            if(door != null){
                //TODO: Left of here; interacting with door going into dungeon
            }

        }

        if(pathToCave != null && pathToCave.validate()){

            pathToCave.traverse();

        }

    }
}

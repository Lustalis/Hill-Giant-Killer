import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.map.TilePath;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkToCave extends Node {

    final TilePath pathToCave = Walking.newTilePath(Var.path1);

    @Override
    public boolean activate() {
        return (!Var.dungArea.contains(Players.getLocal()));
    }

    @Override
    public void execute() {

        if(pathToCave != null && pathToCave.validate()){

            pathToCave.traverse();

        }

    }
}

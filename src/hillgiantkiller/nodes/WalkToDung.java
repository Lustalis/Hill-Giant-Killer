package hillgiantkiller.nodes;

import Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class WalkToDung extends Node {


    @Override
    public boolean activate() {
        return (!Var.bankArea.contains(Players.getLocal()));
    }

    @Override
    public void execute() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 6/4/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class UseRejuv extends Node {

    @Override
    public boolean activate() {
        return Global.useRejuvenate && Methods.canUseReju();
    }

    @Override
    public void execute() {

        ActionBar.getNode(0).use();
        Task.sleep(1000);



    }
}

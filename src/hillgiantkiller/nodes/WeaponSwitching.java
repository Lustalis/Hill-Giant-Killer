package hillgiantkiller.nodes;


import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import org.powerbot.core.script.job.state.Node;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/24/13
 * Time: 8:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class WeaponSwitching extends Node {
    @Override
    public boolean activate() {
        return Global.useRejuvenate && Methods.getHpPercent() <= Eat.HEAL_PERCENT
                && Methods.getHpPercent() > Eat.HEAL_PERCENT - 5;
    }

    @Override
    public void execute() {

    }
}

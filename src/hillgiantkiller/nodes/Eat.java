package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;

public class Eat extends Node {


    public static final int HEAL_PERCENT = 50;

    @Override
    public boolean activate() {

        return Methods.needToHeal() && (Global.useRejuvenate && ActionBar.getNode(0).canUse() && ActionBar.getAdrenaline() == 1000) ||
                (Global.eatFood && Methods.getHpPercent() <= HEAL_PERCENT - 5);
    }

    @Override
    public void execute() {

        if(Global.useRejuvenate && ActionBar.getAdrenaline() == 1000 && ActionBar.getNode(0).canUse()){
            ActionBar.getNode(0).use();
            Task.sleep(1000);
        }else if(Global.eatFood && Methods.getHpPercent() <= HEAL_PERCENT - 5){
            Inventory.getItem(Global.foodId).getWidgetChild().interact("Eat");
        }

    }//End of Execute

} //End of Node
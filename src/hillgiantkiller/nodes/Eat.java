package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;

public class Eat extends Node {


    public static final int HEAL_PERCENT = 50;

    @Override
    public boolean activate() {

        return Methods.needToHeal() && (Variables.useRejuvenate && ActionBar.getNode(0).canUse() && ActionBar.getAdrenaline() == 1000) ||
                (Variables.eatFood && Methods.getHpPercent() <= HEAL_PERCENT - 5);
    }

    @Override
    public void execute() {

        if(Variables.useRejuvenate && ActionBar.getAdrenaline() == 1000 && ActionBar.getNode(0).canUse()){
            ActionBar.getNode(0).canUse();
            Task.sleep(1000);
        }else if(Variables.eatFood && Methods.getHpPercent() <= HEAL_PERCENT - 5){
            Inventory.getItem(Variables.foodId).getWidgetChild().interact("Eat");
        }

    }//End of Execute

} //End of Node
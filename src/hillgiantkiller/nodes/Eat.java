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

        return Methods.needToHeal() && Variables.eatFood;
    }

    @Override
    public void execute() {
        if(Variables.useRejuvenate && ActionBar.getAdrenaline() == 1000){
            ActionBar.useSlot(0);
            Task.sleep(1000);
        }else{
            Inventory.getItem(Variables.foodId).getWidgetChild().interact("Eat");
        }

    }//End of Execute

} //End of Node
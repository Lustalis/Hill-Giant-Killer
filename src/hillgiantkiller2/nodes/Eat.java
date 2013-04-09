package hillgiantkiller2.nodes;

import hillgiantkiller.other.Var;
import hillgiantkiller2.Methods;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;

public class Eat extends Node {


    @Override
    public boolean activate() {

        return Methods.needToHeal();
    }

    @Override
    public void execute() {
        if(Var.useRejuvenate){

        }
        Inventory.getItem(Variables.foodId).getWidgetChild().interact("Eat");

    }//End of Execute

} //End of Node
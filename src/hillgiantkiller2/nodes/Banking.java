package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Node {


    @Override
    public boolean activate() {

        return (Inventory.isFull() || Methods.haveFood(Variables.foodId)) || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!Variables.BANK_AREA.contains(Players.getLocal())){

        }

    }//End of Execute

} //End of Node
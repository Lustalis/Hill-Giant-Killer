package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class ToHillGiants extends Node {


    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Variables.NPC_IDS);
        return (!Inventory.isFull() || Methods.haveFood(Variables.foodId))|| x == null;
    }

    @Override
    public void execute() {

    }//End of Execute

} //End of Node
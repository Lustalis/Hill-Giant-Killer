package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Banking extends Node {
    private SceneObject stairsUp;
    private SceneObject door;

    @Override
    public boolean activate() {

        return (Inventory.isFull() || Methods.haveFood(Variables.foodId)) || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!Variables.BANK_AREA.contains(Players.getLocal())){
            if(!Variables.DUNG_AREA.contains(Players.getLocal())){
                NPC x = NPCs.getNearest(Variables.NPC_IDS);
                if(x != null){
                    System.out.println("In the dungeon; going up");
                    stairsUp = SceneEntities.getNearest(Variables.STAIRS_UP);
                    if(stairsUp == null || stairsUp.interact("Climb-up")){
                        Walking.walk(stairsUp);
                    }
                }
            }else{
                door = SceneEntities.getNearest(Variables.DOOR_ID);
                //TODO: Get nwe dungeon area manually
            }
        }

    }//End of Execute

} //End of Node
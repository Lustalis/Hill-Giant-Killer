package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Banking extends Node {
    private SceneObject ladderUp;
    public static final int LADDER_UP = 29355;
    private SceneObject door;
    private static final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3453,0), new Tile(3116,3450,0));
    private final TilePath TO_BANK = Walking.newTilePath(Variables.PATH_2);

    @Override
    public boolean activate() {

        return (Inventory.isFull() || !Methods.haveFood(Variables.foodId)) || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!Variables.BANK_AREA.contains(Players.getLocal())){
            if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
                NPC x = NPCs.getNearest(Variables.NPC_IDS);
                if(x != null){
                    //Walking up ladder
                    System.out.println("In the dungeon; going up");
                    ladderUp = SceneEntities.getNearest(LADDER_UP);
                    if(ladderUp != null && !Methods.isOnScreen(ladderUp)){
                        Walking.walk(ladderUp);
                    }else if(ladderUp.interact("Climb-up")){
                        Methods.waitForArea(AROUND_LADDER_DOWN);
                    }
                }
            }else{
                //Opening door
                door = SceneEntities.getNearest(Variables.DOOR_ID);
                System.out.println("Opening door");
                if(door != null && door.interact("Open")){
                    while(AROUND_LADDER_DOWN.contains(Players.getLocal())){
                        Task.sleep(500);
                    }

                }
            }
            TO_BANK.traverse();
        }else{
            //Banking
            if(!Bank.isOpen() && !Players.getLocal().isMoving()){
                Bank.open();
            }else{
                Bank.depositInventory();
                Bank.withdraw(Variables.foodId, Variables.withdrawFoodAmount);
                Bank.withdraw(983, 1);
                Bank.close();
            }

        }

    }//End of Execute

} //End of Node
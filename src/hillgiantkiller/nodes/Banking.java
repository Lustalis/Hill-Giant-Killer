package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Banking extends Node {
    public static final Area BANK_AREA = new Area(new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0));
    public static final int DOOR_ID = 1804;
    private SceneObject ladderUp;
    public static final int LADDER_UP = 29355;
    private SceneObject door;
    private static final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3453,0), new Tile(3116,3450,0));
    public static final Tile[] PATH_2 = new Tile[] { new Tile(3115, 3445, 0), new Tile(3124, 3451, 0), new Tile(3131, 3455, 0),
            new Tile(3139, 3456, 0), new Tile(3145, 3456, 0), new Tile(3152, 3456, 0),
            new Tile(3158, 3460, 0), new Tile(3150, 3474, 0) };
    private final TilePath TO_BANK = Walking.newTilePath(PATH_2);

    @Override
    public boolean activate() {

        return (Inventory.isFull() || (Variables.eatFood && !Methods.haveFood(Variables.foodId)) || !Inventory.contains(983)) || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!BANK_AREA.contains(Players.getLocal())){
            Paint.status = "Walking to bank";
            if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
                if(NPCs.getNearest(Variables.NPC_IDS) != null){
                    //Walking up ladder
                    System.out.println("In the dungeon; going up");
                    ladderUp = SceneEntities.getNearest(LADDER_UP);
                    if(ladderUp != null){
                        if(ladderUp.isOnScreen() && ladderUp.interact("Climb-up")){
                            Methods.waitForArea(AROUND_LADDER_DOWN);

                        } else{
                            Walking.walk(ladderUp);
                        }
                    }
                }
            }else{
                //Opening door
                door = SceneEntities.getNearest(DOOR_ID);
                System.out.println("Opening door");
                if(door != null){
                    Camera.turnTo(door);
                    if(door.interact("Open")){
                        Methods.waitForArea(AROUND_LADDER_DOWN);
                    }
                }

            }
            TO_BANK.traverse();
        }else{
            Paint.status = "Banking...";
            ActionBar.expand(false);
            if(!Players.getLocal().isMoving() && Bank.open()){

                Bank.depositInventory();
                Methods.waitForInvChange();
                if(Bank.getItemCount(983) == 0 || Bank.getItemCount(Variables.foodId) == 0){
                    Game.logout(true);
                }
                Bank.withdraw(Variables.foodId, Variables.withdrawFoodAmount);
                Bank.withdraw(983, 1);
                Bank.close();
            }
        }

    }//End of Execute

} //End of Node
package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

import java.util.ArrayList;
import java.util.List;

public class Banking extends Node {
    public static final Area BANK_AREA = new Area(new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0));
    public static final int DOOR_ID = 1804;
    public static final int LADDER_UP = 29355;
    public static final Tile[] PATH_2 = new Tile[] { new Tile(3115, 3445, 0), new Tile(3124, 3451, 0), new Tile(3131, 3455, 0),
            new Tile(3139, 3456, 0), new Tile(3145, 3456, 0), new Tile(3152, 3456, 0),
            new Tile(3158, 3460, 0), new Tile(3150, 3474, 0) };
    public static List<Integer> runesToKeep = new ArrayList<>();

    private SceneObject ladderUp;
    private SceneObject door;
    public static SceneObject resourceDungeon;
    private int insideResource = 52868;
    private final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3453,0), new Tile(3116,3450,0));
    private final TilePath TO_BANK = Walking.newTilePath(PATH_2);
    private final Area AROUND_MYSTERIOUS_ENTRANCE = new Area(new Tile(3107,9825,0), new Tile(3102,9827,0));

    @Override
    public boolean activate() {

        return (Inventory.isFull() || (Variables.eatFood && !Methods.haveFood(Variables.foodId)) || !Inventory.contains(983)) || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!BANK_AREA.contains(Players.getLocal())){
            Paint.status = "Walking to bank";
            if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
                resourceDungeon = SceneEntities.getNearest(insideResource);
                if(Variables.useResourceDungeon && resourceDungeon != null){
                    if(resourceDungeon.isOnScreen() && resourceDungeon.interact("Exit")){
                        Methods.waitForArea(AROUND_MYSTERIOUS_ENTRANCE);
                    }else {
                        new Fight.MoveCamera(resourceDungeon).start();
                        Walking.findPath(new Tile(1134,4589,0)).traverse();
                    }
                }else{
                    if(NPCs.getNearest(Variables.NPC_IDS) != null){
                        //Walking up ladder
                        ladderUp = SceneEntities.getNearest(LADDER_UP);
                        if(ladderUp != null){
                            if(ladderUp.isOnScreen() && ladderUp.interact("Climb-up")){
                                Methods.waitForArea(AROUND_LADDER_DOWN);

                            } else{
                                new Fight.MoveCamera(ladderUp).start();
                                Walking.findPath(new Tile(3115,9850,0)).traverse();
                            }
                        }
                    }
                }

            }else{
                //Opening door
                door = SceneEntities.getNearest(DOOR_ID);
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
                Task.sleep(500, 900);
                if(Variables.eatFood){
                    Bank.withdraw(Variables.foodId, Variables.withdrawFoodAmount);
                }
                if(Variables.isMage){
                    for (int i: runesToKeep){
                        Bank.withdraw(i, Bank.Amount.ALL);
                        Methods.waitForInvChange();
                    }
                }
                Bank.withdraw(983, 1);
                Bank.close();
                Task.sleep(250, 750);
            }
        }

    }//End of Execute

} //End of Node
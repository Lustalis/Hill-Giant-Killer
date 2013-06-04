package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
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
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

import java.util.ArrayList;
import java.util.List;

public class ToBank extends Node {
    public static final Area BANK_AREA = new Area(new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0));
    public static final int DOOR_ID = 1804;
    public static final int LADDER_UP = 29355;


    private SceneObject ladderUp;
    private SceneObject door;
    public static SceneObject resourceDungeon;
    private int insideResource = 52868;
    private final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3455,0), new Tile(3117,3450,0));

    private final Area AROUND_LADDER_UP = new Area(new Tile(3115,9853,0), new Tile(3117,9851,0));

    private final Tile[] TO_BANK ={ new Tile(3115, 3445, 0), new Tile(3124, 3451, 0), new Tile(3131, 3455, 0),
            new Tile(3139, 3456, 0), new Tile(3145, 3456, 0), new Tile(3152, 3456, 0),
            new Tile(3158, 3460, 0), new Tile(3150, 3474, 0) };

    private final Tile[] FIND_WAY_OUT_RESOURCE =  {new Tile(1107,4581,0), new Tile(112,4576,0),
            new Tile(1117,4574,0), new Tile(1123,4574,0), new Tile(1132,4579,0), new Tile(1134,4587,0)};

    private final Tile[] FIND_WAY_OUT_DUNGEON = {new Tile(3104,9830,0), new Tile(3104,9826,0), new Tile(3107,9834,0),
            new Tile(3116,9838,0), new Tile(3116,9844,0)};

    private final Area AROUND_MYSTERIOUS_ENTRANCE = new Area(new Tile(3107,9825,0), new Tile(3102,9827,0));

    @Override
    public boolean activate() {

        return (Inventory.isFull() || (Global.eatFood && !Methods.haveFood(Global.foodId)) || !Inventory.contains(983) || Methods.getHpPercent() <=25)
                || Bank.isOpen();
    }

    @Override
    public void execute() {
        if(!BANK_AREA.contains(Players.getLocal())){
            Paint.status = "Walking to bank";
            if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
                resourceDungeon = SceneEntities.getNearest(insideResource);
                if(Global.useResourceDungeon && resourceDungeon != null){
                    if(resourceDungeon.isOnScreen() && resourceDungeon.interact("Exit")){
                        Methods.waitForArea(AROUND_MYSTERIOUS_ENTRANCE);
                    }else {
                        Global.stuffToDo.execute(new Global.MoveCamera(resourceDungeon));
                        Walking.newTilePath(FIND_WAY_OUT_RESOURCE).traverse();
                    }
                }else{
                    if(NPCs.getNearest(Global.NPC_IDS) != null){
                        ladderUp = SceneEntities.getNearest(new Filter<SceneObject>() {
                            @Override
                            public boolean accept(SceneObject ladder) {
                                return ladder.getId() == LADDER_UP && AROUND_LADDER_UP.contains(ladder);
                            }
                        });
                        if(ladderUp != null){
                            if(ladderUp.isOnScreen() && ladderUp.interact("Climb-up")){
                                Methods.waitForArea(AROUND_LADDER_DOWN);
                            } else if(AROUND_MYSTERIOUS_ENTRANCE.contains(Players.getLocal()) && !Players.getLocal().isMoving()){
                                Walking.walk(new Tile(3106,9832,0));
                            }else{
                                Global.stuffToDo.execute(new Global.MoveCamera(ladderUp));
                                Walking.newTilePath(FIND_WAY_OUT_DUNGEON).traverse();
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
            Walking.newTilePath(TO_BANK).traverse();
        }else{

        }

    }//End of Execute

} //End of Node
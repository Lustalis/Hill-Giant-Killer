package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class EmergancyEscape extends Node {
    public static final int LADDER_UP = 29355;
    private SceneObject ladderUp;
    public static SceneObject resourceDungeon;
    private int insideResource = 52868;
    private final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3453,0), new Tile(3116,3450,0));
    private final Area AROUND_MYSTERIOUS_ENTRANCE = new Area(new Tile(3107,9825,0), new Tile(3102,9827,0));

    @Override
    public boolean activate() {

        return Methods.getHpPercent() <= 20;
    }

    @Override
    public void execute() {
        if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
            resourceDungeon = SceneEntities.getNearest(insideResource);
            if(Global.useResourceDungeon && resourceDungeon != null){
                if(resourceDungeon.isOnScreen() && resourceDungeon.interact("Exit")){
                    Methods.waitForArea(AROUND_MYSTERIOUS_ENTRANCE);
                }else {
                    Global.stuffToDo.execute(new Global.MoveCamera(resourceDungeon));
//                    new Fight.MoveCamera(resourceDungeon).start();
                    Walking.findPath(new Tile(1134, 4589, 0)).traverse();
                }
            }else{
                if(NPCs.getNearest(Global.NPC_IDS) != null){
                    //Walking up ladder
                    ladderUp = SceneEntities.getNearest(LADDER_UP);
                    if(ladderUp != null){
                        if(ladderUp.isOnScreen() && ladderUp.interact("Climb-up")){
                            Methods.waitForArea(AROUND_LADDER_DOWN);

                        } else{
                            Global.stuffToDo.execute(new Global.MoveCamera(ladderUp));
//                            new Fight.MoveCamera(ladderUp).start();
                            Walking.findPath(new Tile(3115,9850,0)).traverse();
                        }
                    }
                }
            }

        }else{
            Game.logout(true);

        }
    }//End of Execute

} //End of Node
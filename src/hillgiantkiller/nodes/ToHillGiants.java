package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ToHillGiants extends Node {
    private SceneObject door;
    private SceneObject ladderDown;
    private final int LADDER_DOWN = 12389;
    private final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3455,0), new Tile(3117,3450,0));
    private final Area INSIDE_DUNGEON = new Area(new Tile(3115,9854,0), new Tile(3117,9854,0), new Tile(3117,9850,0),
            new Tile(3115,9850,0));
    public static final Area DUNGEON_ENTRANCE = new Area(new Tile(3112,3448,0), new Tile(3118,3444,0));
    private final Area INSIDE_RESOURCE_DUNGEON = new Area(new Tile(1132,4589,0), new Tile(1136,4588,0));


    private Tile[] TO_DUNGEON = { new Tile(3150, 3474, 0), new Tile(3146, 3464, 0), new Tile(3136, 3456, 0),
            new Tile(3127, 3455, 0), new Tile(3114, 3446, 0) };
    private final Tile[] PATH_TO_RESOURCE = {new Tile(3104,9826,0), new Tile(3107,9834,0),
            new Tile(3116,9838,0), new Tile(3116,9844,0)};

    private int outsideResource = 52853;
    private int insideResource = 52868;
    private SceneObject resourceDoor;


    @Override
    public boolean activate() {
        return Global.useResourceDungeon ? SceneEntities.getNearest(insideResource) == null
                : NPCs.getNearest(Global.NPC_IDS) == null;
    }

    @Override
    public void execute() {
        Paint.status = "Walking back to dungeon";

        if (NPCs.getNearest(Global.NPC_IDS) == null) {
            if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
                if(!DUNGEON_ENTRANCE.getTileArray()[1].isOnScreen()){
                    Walking.newTilePath(TO_DUNGEON).traverse();
                }else {
                    door = SceneEntities.getNearest(Banking.DOOR_ID);
                    if(door != null && door.interact("Open")){
                        Methods.waitForArea(AROUND_LADDER_DOWN);
                    }
                }

            }else{
                ladderDown = SceneEntities.getNearest(LADDER_DOWN);
                if(ladderDown != null && ladderDown.interact("Climb-down")){
                    Methods.waitForArea(INSIDE_DUNGEON);
                }
            }
        } else{
            resourceDoor = SceneEntities.getNearest(outsideResource);
            if(resourceDoor != null){
                if(resourceDoor.isOnScreen() && resourceDoor.interact("Enter")){
                    Methods.waitForArea(INSIDE_RESOURCE_DUNGEON);
                }else{
                    Global.stuffToDo.execute(new Global.MoveCamera(resourceDoor));
                    Walking.newTilePath(PATH_TO_RESOURCE).reverse().traverse();
                }
            }


        }

    }//End of Execute

} //End of Node
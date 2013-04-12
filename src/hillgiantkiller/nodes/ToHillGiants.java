package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ToHillGiants extends Node {
    private SceneObject door;
    private SceneObject ladderDown;
    private final int LADDER_DOWN = 12389;
    private final Area AROUND_LADDER_DOWN = new Area(new Tile(3113,3453,0), new Tile(3116,3450,0));
    private final Area INSIDE_DUNGEON = new Area(new Tile(3115,9854,0), new Tile(3117,9854,0), new Tile(3117,9850,0),
            new Tile(3115,9850,0));
    private final Area DUNGEON_ENTRANCE = new Area(new Tile(3112,3448,0), new Tile(3118,3444,0));
    private final Tile[] PATH_1 = new Tile[] { new Tile(3150, 3474, 0), new Tile(3146, 3464, 0), new Tile(3136, 3456, 0),
            new Tile(3127, 3455, 0), new Tile(3114, 3446, 0) };
    private TilePath TO_DUNGEON = Walking.newTilePath(PATH_1);

    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Variables.NPC_IDS);
        return (!Inventory.isFull() || Methods.haveFood(Variables.foodId)) && x == null;
    }

    @Override
    public void execute() {
        if(!AROUND_LADDER_DOWN.contains(Players.getLocal())){
            if(!DUNGEON_ENTRANCE.contains(Players.getLocal())){
                TO_DUNGEON.traverse();
            }else {
                door = SceneEntities.getNearest(Variables.DOOR_ID);
                if(door != null && door.interact("Open")){
                    Methods.waitForArea(AROUND_LADDER_DOWN);
                }
            }

        }else{
            ladderDown = SceneEntities.getNearest(LADDER_DOWN);
            if(ladderDown.interact("Climb-down")){
                Methods.waitForArea(INSIDE_DUNGEON);
            }
        }

    }//End of Execute

} //End of Node
package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * User: Stefano Tabone
 * Date: 5/6/13
 * Time: 3:34 PM
 */
public class LeaveDungeon extends Node {
    private SceneObject resourceDungeon;
    private int insideResource = 52868;
    private int outsideResource = 52853;
    private final Area AROUND_MYSTERIOUS_ENTRANCE = new Area(new Tile(3107,9825,0), new Tile(3102,9827,0));

    @Override
    public boolean activate() {
        return !Variables.useResourceDungeon && NPCs.getLoaded(Fight.NPC_IDS) != null && SceneEntities.getLoaded(outsideResource) == null;
    }

    @Override
    public void execute() {
        resourceDungeon = SceneEntities.getNearest(insideResource);
        if(resourceDungeon.isOnScreen() && resourceDungeon.interact("Exit")){
            Methods.waitForArea(AROUND_MYSTERIOUS_ENTRANCE);
        }else {
            new Fight.MoveCamera(resourceDungeon).start();
            Walking.findPath(new Tile(1134, 4589, 0)).traverse();
        }
    }
}
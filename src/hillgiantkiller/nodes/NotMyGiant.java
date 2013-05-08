package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * User: Stefano Tabone
 * Date: 5/2/13
 * Time: 9:28 PM
 */
public class NotMyGiant extends Node {
    @Override
    public boolean activate() {
        return Fight.theGiant != null && Players.getLocal().getInteracting() != null && !Players.getLocal().getInteracting().equals(Fight.theGiant);
    }

    @Override
    public void execute() {
        System.out.println("Retaliated against different giant");
        Fight.theGiant = (NPC) Players.getLocal().getInteracting();
        Global.deathLocation = Fight.theGiant.getLocation();


    }
}

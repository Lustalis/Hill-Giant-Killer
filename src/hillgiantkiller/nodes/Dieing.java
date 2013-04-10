package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/9/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

public class Dieing extends Node {
    private int npcHash = 0;
    @Override
    public boolean activate() {
        return Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().getAnimation() == Variables.DEATH_ID;
    }

    @Override
    public void execute() {
        if(npcHash == 0 || npcHash != Players.getLocal().getInteracting().hashCode()){
            System.out.println("Giant is deeeead");
            Paint.giantsKilled++;
            npcHash = Players.getLocal().getInteracting().hashCode();
            if (Variables.shouldLoot) {
                Variables.deathLocation = Players.getLocal().getInteracting().getLocation();

                if (Variables.shouldWaitForLoot || Variables.lootAfter == 1) {
                    Methods.waitForDrop();
                }
            }
            npcHash = Players.getLocal().getInteracting().hashCode();
        }
    }
}

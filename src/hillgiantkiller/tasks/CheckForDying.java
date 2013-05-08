package hillgiantkiller.tasks;

import hillgiantkiller.nodes.Fight;
import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.Character;

/**
 * User: Stefano Tabone
 * Date: 4/10/13
 * Time: 9:33 PM
 */
public class CheckForDying extends LoopTask {
    private Character npc;
    @Override
    public int loop() {
        try {
            npc = Players.getLocal().getInteracting();
            if((npc != null && npc.getAnimation() == Global.DEATH_ID)
                    || (npc == null && Fight.theGiant != null && Fight.theGiant.getHealthPercent() == 0 && Fight.theGiant.isOnScreen())
                    && !getContainer().isPaused()){
                Global.gKilled++;
                System.out.println("starting task and pausing container");
                getContainer().setPaused(true);

                System.out.println("Giant is deeeead");
                if(Fight.theGiant != null){
                    Global.deathLocation = Fight.theGiant.getLocation();
                }

                if (Global.shouldLoot) {
                    if (Global.lootAfter == 1) {
                        System.out.println("Waiting for loot");
                        Paint.status = "Waiting for loot...";
                        Methods.waitForDrop();
                    }else{
                        Methods.droppedLoot();
                        Task.sleep(750,950);
                    }
                    System.out.println("Done waiting for loot");
                    getContainer().setPaused(false);
                    Fight.theGiant = null;
                }

                if(getContainer().isPaused()){
                    getContainer().setPaused(false);
                    Fight.theGiant = null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Random.nextInt(10, 20);
    }
}

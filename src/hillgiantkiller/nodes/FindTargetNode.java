package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/28/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindTargetNode extends Node {
    public static int counter = 0;
    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Var.npcIds);
        return  Methods.haveFood(Var.foodIds) && (Var.theGiant == null || !Var.theGiant.validate()) ||
        (Players.getLocal().getInteracting() == null || !Players.getLocal().getInteracting().validate())
                && (x != null) && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        Var.theGiant = Methods.getMonster();

        if(Var.theGiant != null){
            System.out.println("Killed this many nigguhs: "+counter);
            System.out.println("Found npc!!!");
            if(!Methods.isOnScreen(Var.theGiant)){
                Camera.turnTo(Var.theGiant);
                if(!Methods.isOnScreen(Var.theGiant)){
                    Walking.walk(Var.theGiant);
                }
                Methods.waitForOnScreen(Var.theGiant);
            }
            System.out.println("Attacking");
            Var.theGiant.interact("Attack");
            Methods.waitForCombat();

            Var.lootLocation = Var.theGiant.getLocation();

        }

        //TODO: This works

    }
}

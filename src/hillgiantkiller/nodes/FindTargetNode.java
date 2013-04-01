package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/28/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindTargetNode extends Node {
    @Override
    public boolean activate() {

        return  Methods.haveFood(Var.foodIds) && (Players.getLocal().getInteracting() == null || !Players.getLocal().getInteracting().validate()
                || Var.theGiant == null || !Var.theGiant.validate()) && !Var.bankArea.contains(Players.getLocal())
                && !Var.dungArea.contains(Players.getLocal());
    }

    @Override
    public void execute() {

        System.out.println("Finding npc.....");
        Var.theGiant = Methods.getMonster();

        if(Var.theGiant != null){
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

            //hillgiantkiller.other.Var.lootLocation = hillgiantkiller.other.Var.theGiant.getLocation();
        }

        //TODO: This works

    }
}

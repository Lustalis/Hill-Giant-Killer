import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class AttackNode extends Node {
    @Override
    public boolean activate() {
        return Methods.haveFood(Var.foodIds) && (Var.theGiant != null
                && Var.theGiant.validate()) && Players.getLocal().getInteracting() == null;
    }

    @Override
    public void execute() {
        System.out.println("In attack node");

        Var.theGiant.interact("Attack");
        Methods.waitForCombat();
        Var.lootLocation = Var.theGiant.getLocation();
        //TODO: This works


    }
}

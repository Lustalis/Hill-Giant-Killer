import org.powerbot.core.script.job.state.Node;
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
        return (Var.theGiant == null || !Var.theGiant.validate()
                || Players.getLocal().getInteracting() == null) && !Players.getLocal().isMoving();
    }

    @Override
    public void execute() {

        System.out.println("Finding npc.....");
        Var.theGiant = Methods.getMonster(Var.npcIds);
        if(Var.theGiant != null){
            if(!Methods.isOnScreen(Var.theGiant)){
                Camera.turnTo(Var.theGiant);
            }
        }

        //TODO: This works

    }
}

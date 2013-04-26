package hillgiantkiller.tasks;

import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;

/**
 * User: Stefano Tabone
 * Date: 4/9/13
 * Time: 10:32 PM
 */
public class MomentumTask extends Task {
    @Override
    public void execute() {
        if(Settings.get(682) == 0x240026){
            System.out.println("Activating Momentum");
            ActionBar.getNode(0).interact("Activate");
        }else{
            System.out.println("Momentum already active");
        }
    }
}

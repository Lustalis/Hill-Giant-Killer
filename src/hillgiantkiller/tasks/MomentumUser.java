package hillgiantkiller.tasks;

import hillgiantkiller2.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;

/**
 * User: Stefano Tabone
 * Date: 4/7/13
 * Time: 6:39 PM
 */
public class MomentumUser extends Task {
    @Override
    public void execute() {
        System.out.println("In momentum node");
        Task.sleep(1000);
        if(Settings.get(682) == 0x240026){
            System.out.println("Activating");
            ActionBar.interactSlot(0,"Activate");
        }
    }
}

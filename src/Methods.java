import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Methods{

    //waits for player to enter an area
    public static void waitForArea(Area a){
        Timer t = new Timer(5000);
        while(t.isRunning() && !a.contains(Players.getLocal())){
            Task.sleep(150, 350);
        }

    }

}

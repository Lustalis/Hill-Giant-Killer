import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Methods{

    public interface Condition{
        public boolean accept();
    }

    //waits for player to enter an area
    public static void waitForArea(Area a){
        Timer t = new Timer(5000);
        while(t.isRunning() && !a.contains(Players.getLocal())){
            Task.sleep(150, 350);
        }

    }

    public static void waitForCombat(){
        Timer t = new Timer(3000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return Players.getLocal().getInteracting() instanceof NPC;
            }
        };

        while(t.isRunning() && !x.accept()){
            Task.sleep(150, 450);
        }
    }

    public static boolean isOnScreen(Entity e) {
        Rectangle screen = new Rectangle(new Point(0, 0), Game.getDimensions());
        WidgetChild actionbarWidget = Widgets.get(640, 6);
        Rectangle actionbar = actionbarWidget == null || !actionbarWidget.isOnScreen() ? null : actionbarWidget.getBoundingRectangle();
        for (Polygon p : e.getBounds()) {
            for (int i = 0; i < p.npoints; i++) {
                int x = p.xpoints[i], y = p.ypoints[i];
                if (screen.contains(x, y) && (actionbar == null || !actionbar.contains(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean fullInv(){
        return Inventory.isFull();
    }

    public static boolean haveFood(final int... foods){

        return Inventory.getCount(foods) >= 1;

    }

    public static int getHpPercent() {
        return Math.abs(100 - 100 * Widgets.get(748, 5).getHeight() / 28);
    }

    public static boolean needToHeal(final int eatPercent){
        return getHpPercent() <= eatPercent;
    }


    public static NPC getMonster(final int... id){

        return NPCs.getNearest(new Filter<NPC>() {
            @Override
            public boolean accept(NPC npc) {
                for(int i: id){
                    if(npc.getId() == i){
                        if(npc.getInteracting().equals(Players.getLocal())){
                            return npc != null && !npc.equals(Var.theGiant);
                        }else{
                            return npc != null && !npc.equals(Var.theGiant) && npc.getInteracting() == null;
                        }

                    }  else{
                        System.out.println("npc not found");
                    }
                }
                return false;
            }
        });

    }

    //use if getting loot from gui
    public static boolean needToLoot(ArrayList<Integer> customLoot){
        for (Integer aCustomLoot : customLoot) {
            GroundItem x = GroundItems.getNearest(aCustomLoot);
            if (x != null) {
                return true;
            }
        }
        return false;
    }

    //uses a filter
    //will loot when certain amount of npc's killed
    public static boolean needToLoot(Filter<GroundItem> itemFilter){
        int remainder = Paint.giantsKilled % 5;
        if(remainder == 0){
            GroundItem x = GroundItems.getNearest(itemFilter);
            return x != null;
        }else{
            return false;
        }

    }

    //opens inventory
    public static boolean openInv(){

        if (Tabs.getCurrent() != Tabs.INVENTORY){
            Tabs.INVENTORY.open();
        }
        return true;

    }

    public static void openBar(){ //opens action bar
        Widgets.get(640, 28).click(true);
    }

    public static void closeBar(){
        Widgets.get(640,30).click(true);
    }



}//end

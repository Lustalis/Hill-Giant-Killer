package hillgiantkiller.other;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
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
import org.powerbot.game.api.wrappers.Tile;
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
    public static void waitForArea(final Area a){
        Timer t = new Timer(5000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return a.contains(Players.getLocal());
            }
        };
        while(t.isRunning() && !x.accept()){
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

    public static void waitForInvChange(){
        Timer t = new Timer(3000);
        final int a = Inventory.getCount();
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return a != Inventory.getCount();
            }
        };
        while(t.isRunning() && !x.accept()){
            Task.sleep(100, 399);
        }
    }

    public static void waitForOnScreen(final NPC n){
        Timer t = new Timer(3000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return Methods.isOnScreen(n) && Calculations.distanceTo(n) <= 3;
            }
        };

        while(t.isRunning() && !x.accept()){
            Task.sleep(150, 300);
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


    public static NPC getMonster(){

        return NPCs.getNearest(Var.npcFilter);

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
        int remainder = Paint.giantsKilled % 1;
        if(remainder == 0){
            GroundItem x = GroundItems.getNearest(itemFilter);
            return x != null;
        }else{
            return false;
        }

    }

    //gets all the loot at a selected tile
    //then loops through all the loot to see if its
    //anything you want
    public static boolean tileContainsLoot(Tile t){
        GroundItem[] x = GroundItems.getLoadedAt(t.getX(), t.getY());
        for(GroundItem i: x){
            for(int s: Var.lootIds){
                if(i.getId()==s){
                    return true;
                }
            }
        }
        return false;
    }




}//end

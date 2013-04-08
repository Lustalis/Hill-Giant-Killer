package hillgiantkiller.other;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.graphics.CapturedModel;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.powerbot.game.client.RSCharacter;

import java.awt.*;

/**
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:37 AM
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

    public static void waitForInvChange(final int item){
        Timer t = new Timer(3000);
        final int a = Inventory.getCount(item);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return a != Inventory.getCount(item);
            }
        };
        while(t.isRunning() && !x.accept()){
            Task.sleep(50, 150);
        }
    }

    public static void waitForOnScreen(final NPC n){
        Timer t = new Timer(3000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return isOnScreen(n) && Calculations.distanceTo(n) <= 3;
            }
        };

        while(t.isRunning() && !x.accept()){
            Task.sleep(150, 300);
        }
    }

    public static void waitForOnScreen(final GroundItem n){
        Timer t = new Timer(3000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return isOnScreen(n) && Calculations.distanceTo(n) <= 3;
            }
        };

        while(t.isRunning() && !x.accept()){
            Task.sleep(150, 300);
        }
    }

    public static void waitForDrop(){
        Timer t = new Timer(3000);
        Condition x = new Condition() {
            @Override
            public boolean accept() {
                return  droppedLoot();
            }
        };

        while(t.isRunning() && !x.accept()){
            Task.sleep(150,350);
        }
    }


    public static boolean isOnScreen(Entity e){
        final WidgetChild actionBar = Widgets.get(640, 6);
        return e.isOnScreen() && (actionBar == null || !actionBar.isOnScreen() || !actionBar.contains(e.getCentralPoint()));
    }

    public static boolean fullInv(){
        return Inventory.isFull();
    }

    public static boolean haveFood(final int... foods){

        return Inventory.getCount(foods) >= 1;

    }

    private static int getHpPercent() {
        return Math.abs(100 - 100 * Widgets.get(748, 5).getHeight() / 28);
    }

    public static boolean needToHeal(){
        return getHpPercent() <= Var.HEAL_PERCENT;
    }


    public static NPC getMonster(){
        if(NPCs.getNearest(Var.priorityNPCFilter) != null){
            return  NPCs.getNearest(Var.priorityNPCFilter);
        }else{
            return NPCs.getNearest(Var.npcFilter);
        }



    }


    /*
    returns true when 1 npc has been killed
    and Var.lootLocations has a Tile in it
     */
    public static boolean needToLoot(){
        return Var.lootLocations.size() >= Var.lootAfter;

    }


    public static boolean droppedLoot(){
        //Tile x = Var.deathLocation;
        Tile x = Players.getLocal().getLocation();
        if(x!= null){
            Area lootZone = new Area(new Tile(x.getX() + 3, x.getY() + 3, Game.getPlane())
                    ,new Tile(x.getX() - 3, x.getY() - 3, Game.getPlane()) );
            for(Tile t: lootZone.getTileArray()){
                GroundItem[] potential = GroundItems.getLoadedAt(t.getX(),t.getY());
                for(GroundItem p: potential){
                    for(int i: Var.lootIds){
                        if(i == p.getId()){
                            if(Var.lootLocations.size() == 0){
                                System.out.println("Added tile(method)");
                                Var.lootLocations.add(t);
                            }else if(Var.lootLocations.size() >0 && !Var.lootLocations.contains(t)){
                                System.out.println("Added tile(method)");
                                Var.lootLocations.add(t);
                            }
                            System.out.println("Tiles in list(method): "+Var.lootLocations.size());

                        }
                    }
                }
            }
            if(Var.lootLocations.size() >=1 )return true;
        }
        return false;

    }

    //learn to make threads!!
    public static void threadedCamera(final NPC c) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Camera.turnTo(c);
            }
        };
        Thread cameraTurn = new Thread(r);
        cameraTurn.start();

    }




}//end

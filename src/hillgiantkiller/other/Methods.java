package hillgiantkiller.other;

import hillgiantkiller.nodes.Eat;
import hillgiantkiller.nodes.EquipShield;
import hillgiantkiller.nodes.Looting;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * User: Stefano Tabone
 * Date: 4/8/13
 * Time: 11:09 PM
 */
public class Methods {

    public interface Condition{
        public boolean accept();
    }

    //generic wait for
    public static void waitFor(Condition x, int timer){
        Timer t = new Timer(timer);
        while(t.isRunning() && !x.accept()){
            Task.sleep(150, 350);
        }
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
        return e != null && e.isOnScreen() && (actionBar == null || !(actionBar != null && actionBar.isOnScreen()
                && actionBar.getBoundingRectangle().contains(e.getCentralPoint())));

//        return e != null && e.isOnScreen() && (actionBar == null || !actionBar.isOnScreen() || !actionBar.contains(e.getCentralPoint()));
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

    public static boolean needToHeal(){
        return getHpPercent() <= Eat.HEAL_PERCENT;
    }




    /*
    returns true when 1 npc has been killed
    and Global.lootLocations has a Tile in it
     */
    public static boolean needToLoot(){
        return Looting.lootLocations.size() >= Global.lootAfter;

    }

    public static boolean droppedLoot(){
        int lootRad = Global.lootRadius;
        Tile location = Global.deathLocation;
        if(location!= null){
            Area lootZone = new Area(new Tile(location.getX() + lootRad, location.getY() + lootRad, Game.getPlane())
                    ,new Tile(location.getX() - lootRad, location.getY() - lootRad, Game.getPlane()) );
            for(Tile t: lootZone.getTileArray()){
                GroundItem[] potential = GroundItems.getLoadedAt(t.getX(), t.getY());
                if(Global.lootByPrice && Looting.lootIds.isEmpty() && potential.length > 0){
                    System.out.println("No loot id's selected; adding tile");
                    addTile(t);
                }else{
                    for(GroundItem p: potential){
                        for(int i: Looting.lootIds){
                            if(i == p.getId()){
                                addTile(t);
                                System.out.println("Tiles in list(method): "+ Looting.lootLocations.size());
                            }
                        }

                    }
                }

            }
            if(Looting.lootLocations.size() >=1) return true;
        }
        return false;

    }

    public static void addTile(Tile t){
        if(Looting.lootLocations.size() == 0 || (Looting.lootLocations.size() >0 && !Looting.lootLocations.contains(t))){
            System.out.println("Added tile(method)");
            Looting.lootLocations.add(t);

        }
    }

    //makes sure rejuvenate is not reloading, HP is less than HEAL_PERCENT but greater than
    //HEAL_PERCENT - 10
    public static boolean canUseReju(){
        return ActionBar.getNode(0).canUse() && Equipment.containsOneOf(EquipShield.shieldId)
                && ActionBar.getAdrenaline() == 1000 && getHpPercent() <= Eat.HEAL_PERCENT
                && getHpPercent() > Eat.HEAL_PERCENT - 10;
    }



}

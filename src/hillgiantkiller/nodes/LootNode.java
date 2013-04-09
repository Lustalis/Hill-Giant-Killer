package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.net.GeItem;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.Collections;
import java.util.Iterator;

/**
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:35 AM
 */
public class LootNode extends Node {
    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Var.NPC_IDS);
        return Methods.needToLoot() && !Methods.fullInv() && Methods.haveFood(Var.foodId) && Players.getLocal().getInteracting() == null
                && x!=null && Players.getLocal().getAnimation() == -1 && !Var.isAdding;
    }

    @Override
    public void execute() {
        System.out.println("In the loot node");
        Var.isLooting = true;
        System.out.println("Tiles in list: "+Var.lootLocations.size());
        Collections.reverse(Var.lootLocations);
        for(Tile tile: Var.lootLocations){
            GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());
            for(GroundItem i: item){

                if (Var.lootIds.contains(i.getId())) {
                    //Making sure item is on screen
                    if(!Methods.isOnScreen(i)){
                        Camera.turnTo(i);
                        Task.sleep(500);
                        if(!Methods.isOnScreen(i)){
                            Walking.walk(i);
                            Methods.waitForOnScreen(i);
                        }
                    }
                    i.interact("Take", i.getGroundItem().getName());
                    Methods.waitForInvChange(i.getId());
                    System.out.println("Picked up: " +i.getGroundItem().getName());
                } else if(Var.lootByPrice && !Var.priceTable.containsKey(i.getId())){
                    System.out.println("Item no in list: "+i.getGroundItem().getName()+". Looking up price");
                    Var.priceTable.put(i.getId(),0);
                    new PriceLoader(i.getId()).start();
                    while(Var.priceTable.get(i.getId()) == 0){
                        Task.sleep(50,100);
                    }
                    int price = Var.priceTable.get(i.getId());
                    System.out.println("Item price: "+Var.priceTable.get(i.getId()));
                    if((price != -1) && price*i.getGroundItem().getStackSize() >= Var.minPriceToLoot){
                        System.out.println("Added to list");
                        Var.lootIds.add(i.getId());

                        //Making sure item is on screen
                        if(!Methods.isOnScreen(i)){
                            Camera.turnTo(i);
                            Task.sleep(500);
                            if(!Methods.isOnScreen(i)){
                                Walking.walk(i);
                                Methods.waitForOnScreen(i);
                            }
                        }
                        i.interact("Take", i.getGroundItem().getName());
                        Methods.waitForInvChange(i.getId());
                        System.out.println("Picked up: " +i.getGroundItem().getName());

                    }
                }


            }


        }
        Var.lootLocations.clear();
        Methods.droppedLoot();
        if(Var.eatFoodForSpace && Inventory.isFull() && Inventory.getCount(Var.foodId) != 0){
            Inventory.getItem(Var.foodId).getWidgetChild().interact("Eat");
        }
        if(Var.burryBones && Inventory.isFull()){
            Camera.turnTo(Var.insideSafeZone);
            System.out.println("Inventory full; going to burry bones");
            do{
                Walking.findPath(Var.insideSafeZone).traverse();
            }while(Calculations.distanceTo(Var.insideSafeZone) >=2 );

            for(Item x: Inventory.getItems()){
                if(x.getId() == 532){
                    x.getWidgetChild().interact("Bury");
                    Methods.waitForInvChange(532);
                }
            }


        }

    }

    class PriceLoader extends Thread {

        private final int itemId;

        public PriceLoader(final int itemId) {
            this.itemId = itemId;
        }

        @Override
        public void run() {
            GeItem geInfo = GeItem.lookup(itemId);
            if(geInfo == null) {
                geInfo = GeItem.lookup(itemId -1);
            }
            Var.priceTable.put(itemId, geInfo != null ? geInfo.getPrice() : -1);
        }

    }
}

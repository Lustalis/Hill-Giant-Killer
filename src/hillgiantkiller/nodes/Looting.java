package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Looting extends Node {
    /*
    Map and walking stuff
    */
    public static final Tile insideSafeZone = new Tile(3109,9848,0);
    public static ArrayList<Integer> lootIds =  new ArrayList<>();
    public static Map<Integer, Integer> priceTable = new HashMap<>();

    public static ArrayList<Tile> lootLocations = new ArrayList<>();

    @Override
    public boolean activate() {

        return Variables.shouldLoot && Methods.needToLoot() ;
    }

    @Override
    public void execute() {
        Paint.status = "Looting";
        System.out.println("In the loot node");
        System.out.println("Tiles in list: "+ lootLocations.size());
        Collections.reverse(lootLocations);
        for(Tile tile: lootLocations){
            if (!Inventory.isFull()) {
                final GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());//get all items on tile
                for(GroundItem i: item){
                    if (lootIds.contains(i.getId())) {//if its a preset loot id
                        new Fight.MoveCamera(i);
                        if(!i.isOnScreen()){//Making sure item is on screen
                            Task.sleep(500);
                            if(!Methods.isOnScreen(i)){
                                Walking.walk(i);
                                Methods.waitForOnScreen(i);
                            }
                        }
                        if(!priceTable.containsKey(i.getId())){
                            priceTable.put(i.getId(), getPrice(i.getId())); //temp place
                        }
                        i.interact("Take", i.getGroundItem().getName());
                        Paint.profit += priceTable.get(i.getId());
                        Methods.waitForInvChange();
                        System.out.println("Picked up: " +i.getGroundItem().getName());
                    } else if(Variables.lootByPrice && !priceTable.containsKey(i.getId())){//if its not a preset id but looting by price is enabled
                                                                                         //and price has not been cached
                        System.out.println("Item not in list: " + i.getGroundItem().getName() + ". Looking up price");
                        priceTable.put(i.getId(), getPrice(i.getId())); //temp place
                        final int price = priceTable.get(i.getId());
                        System.out.println("Item price: " + priceTable.get(i.getId()));
                        if((price != 0) && price*i.getGroundItem().getStackSize() >= Variables.minPriceToLoot){
                            System.out.println("Added to list");
                            lootIds.add(i.getId());
                            new Fight.MoveCamera(i);
                            if(!i.isOnScreen()){
                                Task.sleep(500);
                                if(!i.isOnScreen()){
                                    Walking.walk(i);
                                    Methods.waitForOnScreen(i);
                                }
                            }
                            i.interact("Take", i.getGroundItem().getName());
                            Paint.profit += priceTable.get(i.getId());
                            Methods.waitForInvChange();
                            System.out.println("Picked up: " +i.getGroundItem().getName());

                        }
                    }
                }
            } else {
                break;
            }
        }
        Variables.gKilled = 0;
        lootLocations.clear();
        System.out.println("Checking for loot another time");
        Methods.droppedLoot();

        //Eat food for space
        if(Variables.eatFoodForSpace && Inventory.isFull() && Inventory.getCount(Variables.foodId) != 0){
            Inventory.getItem(Variables.foodId).getWidgetChild().interact("Eat");
        }

        //Bone Burying
        if(Variables.burryBones && Inventory.isFull()){

            for(Item x: Inventory.getItems()){
                if(x.getId() == 532){
                    x.getWidgetChild().interact("Bury");
                    Methods.waitForInvChange();
                }
            }


        }

    }//End of Execute

    private static int getPrice(final int id) {
        final String add = "http://scriptwith.us/api/?return=text&item=" + id;
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(
                new URL(add).openConnection().getInputStream()))) {
            final String line = in.readLine();
            return Integer.parseInt(line.split("[:]")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

} //End of Node
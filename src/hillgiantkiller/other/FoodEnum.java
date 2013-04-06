package hillgiantkiller.other;

/**
 * Created with IntelliJ IDEA.
 * User: Stefano
 * Date: 06/04/13
 * Time: 2:38 AM
 * To change this template use File | Settings | File Templates.
 */
public enum FoodEnum {
    LOBSTER("Lobster",1),
    SWORDFISH("Swordfish",1),
    SHARK("Shark",1),
    MONKFISH("Monkfish",1),
    CUSTOMID("Custom ID",1);

    private int id;
    private String name;

    private FoodEnum(String n, int i){
        name = n;
        id = i;

    }




}

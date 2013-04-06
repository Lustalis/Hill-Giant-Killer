package hillgiantkiller.other;

/**
 * Created with IntelliJ IDEA.
 * User: Stefano
 * Date: 06/04/13
 * Time: 2:38 AM
 * To change this template use File | Settings | File Templates.
 */
public enum FoodEnum {
    LOBSTER("Lobster"),
    SWORDFISH("Swordfish"),
    SHARK("Shark"),
    MONKFISH("Monkfish"),
    CUSTOMID("Custom ID");

    private int id;
    private String name;

    public FoodEnum(String n, int i){
        name = n;
        id = i;

    }




}


import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Var {

    public static final Area dungArea = new Area(new Tile[] { new Tile(3114, 3458, 0), new Tile(3107, 3453, 0), new Tile(3109, 3445, 0),
            new Tile(3118, 3444, 0), new Tile(3122, 3452, 0) });

    public static final Area bankArea = new Area(new Tile[] { new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0) });

    public static final Tile[] path = new Tile[] { new Tile(3149, 3473, 0), new Tile(3156, 3470, 0), new Tile(3160, 3464, 0),
            new Tile(3156, 3458, 0), new Tile(3149, 3456, 0), new Tile(3142, 3455, 0),
            new Tile(3135, 3455, 0), new Tile(3128, 3452, 0), new Tile(3123, 3447, 0),
            new Tile(3116, 3445, 0) };

    public static final int[] npcIds = {117, 4689, 4690, 4691, 4692, 4693, 10706, 10707,
             10708, 10709, 10710, 10711, 10712, 10713, 10714, 10715, 10716, 10717, };

    public static NPC theGiant = null;

    public static final int[] foodIds = {379, 380};
}

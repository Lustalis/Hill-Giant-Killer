package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import hillgiantkiller.sk.action.ActionBar;
import hillgiantkiller.sk.action.BarNode;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.util.Filter;

public class UseAbilities extends Node {
    @Override
    public boolean activate() {

        return Variables.useAbilities && !Variables.useMomentum;
    }

    @Override
    public void execute() {
        ActionBar.makeReady();
        BarNode x = ActionBar.getNode(new Filter<BarNode>() {
            @Override
            public boolean accept(BarNode bn) {
                if ( Variables.useRejuvenate && Methods.getHpPercent() >= (Eat.HEAL_PERCENT +5) && ActionBar.getNode(1).canUse()
                        && ActionBar.getAdrenaline() == 1000){
                    return bn.getSlot() == 1;
                } else{
                    return bn != null && bn.canUse();
                }

            }
        });

        if(x != null){
            System.out.println("Using: " +x.toString());
            x.use();
        }


    }//End of Execute

} //End of Node
package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.state.Node;

public class UseAbilities extends Node {
    final private int x = Variables.useRejuvenate ? 2:0;

    @Override
    public boolean activate() {

        return Variables.useAbilities && !Variables.useMomentum;
    }

    @Override
    public void execute() {
        ActionBar.setExpanded(true);
        for(int i = x; i<12; i++){
            if(x == 2 && (Methods.getHpPercent() >= (Eat.HEAL_PERCENT + 5)) && ActionBar.isReady(1)
                    && ActionBar.getAdrenaline() == 1000 ){
                ActionBar.useSlot(1);
            }else{
                if(ActionBar.isReady(i)){
                    ActionBar.useSlot(i);
                    return;
                }

            }

        }


    }//End of Execute

} //End of Node
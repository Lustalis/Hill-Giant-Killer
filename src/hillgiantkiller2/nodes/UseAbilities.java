package hillgiantkiller2.nodes;

import hillgiantkiller.other.Var;
import hillgiantkiller2.Variables;
import hillgiantkiller2.sk.action.ActionBar;
import org.powerbot.core.script.job.state.Node;

public class UseAbilities extends Node {


    @Override
    public boolean activate() {

        return Variables.useAbilities;
    }

    @Override
    public void execute() {
        int x = Var.useRejuvenate ? 1: 0;
        for(int i = x; i<12; i++){
            if(ActionBar.isReady(i)){
                ActionBar.useSlot(i);
            }

        }

    }//End of Execute

} //End of Node
package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.sk.action.ActionBar;
import hillgiantkiller.sk.action.BarNode;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.util.Filter;

public class UseAbilities extends Node {
    @Override
    public boolean activate() {

        return Global.useAbilities && !Global.useMomentum;
    }

    @Override
    public void execute() {
        ActionBar.makeReady();
        BarNode x = ActionBar.getNode(new Filter<BarNode>() {
            @Override
            public boolean accept(BarNode bn) {
                if (useUlt()){
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

    private boolean useRejuv(){
        return Global.useRejuvenate && Methods.needToHeal() && ActionBar.getAdrenaline() == 1000 && ActionBar.getNode(0).canUse() &&
            ActionBar.getNode(0).canUse();
    }

    private boolean useUlt(){
        return Global.useRejuvenate ?  Methods.getHpPercent() >= (Eat.HEAL_PERCENT +5) && ActionBar.getNode(1).canUse()
                && ActionBar.getAdrenaline() == 1000 : ActionBar.getNode(1).canUse() && ActionBar.getAdrenaline() == 1000;
    }

} //End of Node




















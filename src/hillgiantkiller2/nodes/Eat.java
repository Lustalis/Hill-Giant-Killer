package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import org.powerbot.core.script.job.state.Node;

public class Eat extends Node {


    @Override
    public boolean activate() {

        return Methods.needToHeal();
    }

    @Override
    public void execute() {

    }//End of Execute

} //End of Node
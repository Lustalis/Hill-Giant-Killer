package hillgiantkiller.sk.map.ultimate;

import hillgiantkiller.sk.map.ultimate.teleport.Teleport;

public interface Network extends Teleport {
	
	public Network[] getDestinations();
	public boolean isSameNetwork(Teleport t);
	public boolean equals(Object o);
	
}

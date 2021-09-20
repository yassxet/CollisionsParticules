/**
 * Évenement qui releve les changements doption des objets
 * @author Alexandre Deneault
 * @version 30/3/15
 */

package evenement;

import geometrie.Elements;

import java.util.EventListener;

public interface ChangementOptionListener extends EventListener{
	public void optionChangee();
	
	public void supprimer(Elements elem);
}

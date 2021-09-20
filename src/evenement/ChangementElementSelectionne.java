/**
 * Évenement qui releve les changements de selection d'objet
 * @author Alexandre Deneault
 * @version 12/2/15
 */

package evenement;

import enemeration.Elem;

import java.util.EventListener;

public interface ChangementElementSelectionne extends EventListener{
	public void changementElement(Elem elementSelectionne);
	
}

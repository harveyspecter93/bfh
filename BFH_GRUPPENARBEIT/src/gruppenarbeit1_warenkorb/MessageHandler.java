package gruppenarbeit1_warenkorb;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageHandler {	    
	/*
	* Gruppenarbeit 01: Warenkorb
	* Klasse 1o
	* Ziegler, Andrin; Frei, Yannick; Dräyer, Michael
	*/
		private ResourceBundle bundle;

	    public MessageHandler(ResourceBundle bundle) {
	    	this.bundle = bundle;
	    }
	    public String getString(String key) {
	        try {
	            return this.bundle.getString(key);
	        } catch (MissingResourceException e) {
	            return '!' + key + '!';
	        }
	    }
	    public String getString(String key, Object... params  ) {
	        try {
	            return MessageFormat.format(this.bundle.getString(key), params);
	        } catch (MissingResourceException e) {
	            return '!' + key + '!';
	        }
	    }
}

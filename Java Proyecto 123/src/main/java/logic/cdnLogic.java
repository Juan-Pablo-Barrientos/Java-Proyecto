package logic;

import java.time.Instant;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class cdnLogic {

	static public Cloudinary getCdn() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	    		"cloud_name", "clawgames",
	    		"api_key", "321725931626423",
	    		"api_secret", "dOEM4HZ9AQg46Ag_rbwldYHINYo",
	    		"secure", true));
		return cloudinary;
	}
}

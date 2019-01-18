package ap1;

import static spark.Spark.*;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.log4j.BasicConfigurator;

import com.google.gson.Gson;

import dealmanager.*;

//Add GSON
//Add JDBC
public class API1 {
	private static int idCounter = 0;
	private static DealManager dm;
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		dm = new DealManager();
		Gson gson = new Gson();
		get("/makedeal/:amount", (request, response) -> {
			double dealAmount = Double.parseDouble(request.params(":amount"));
			Deal deal = new Deal(idCounter++, "Buyer", "Seller", dealAmount, generateCurrentTimestamp());
			dm.add(deal);
			return gson.toJson(dm);//(dm.toString());
		});
        
	}
	
	public static String generateCurrentTimestamp() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}

}

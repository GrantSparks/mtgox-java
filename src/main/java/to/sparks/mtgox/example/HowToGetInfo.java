/*
 * The MtGox-Java API is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The MtGox-Java API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the Lesser GNU General Public License
 * along with the MtGox-Java API .  If not, see <http://www.gnu.org/licenses/>.
 */
package to.sparks.mtgox.example;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import to.sparks.mtgox.MtGoxHTTPClient;
import to.sparks.mtgox.model.AccountInfo;
import to.sparks.mtgox.model.Lag;
import to.sparks.mtgox.model.Order;
import to.sparks.mtgox.model.Ticker;

/**
 * This example will show how to get some types of information from MtGox
 *
 * @author SparksG
 * @author Werner Keil
 */
public class HowToGetInfo {

    static final Logger logger = Logger.getLogger(HowToGetInfo.class.getName());

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("to/sparks/mtgox/example/Beans.xml");
        MtGoxHTTPClient mtgoxClient = null;
    	final String currCode = System.getProperty("curr");
        if (currCode!= null && currCode.length()>0) {
	            // Obtain a CHF instance of the API
	    		mtgoxClient = (MtGoxHTTPClient) context.getBean("mtgoxAPI");
        } else {
	    	throw new IllegalArgumentException("No currency given or currency " + currCode + " not supported.");
        }
        Lag lag = mtgoxClient.getLag();
        logger.log(Level.INFO, "Current lag: {0}", lag.getLag());


        Ticker ticker = mtgoxClient.getTicker();
        if (ticker != null) {
	        logger.log(Level.INFO, "Currency: {0}", ticker.getCurrencyCode());
	        logger.log(Level.INFO, "Last price: {0}", ticker.getLast().toPlainString());
        } else {
        	logger.log(Level.WARNING, "No ticker returned.");
        }
        // Get the private account info
        AccountInfo info = mtgoxClient.getAccountInfo();
        logger.log(Level.INFO, "Logged into account: {0}", info.getLogin());

        Order[] openOrders = mtgoxClient.getOpenOrders();

        if (ArrayUtils.isNotEmpty(openOrders)) {
            for (Order order : openOrders) {
                logger.log(Level.INFO, "Open order: {0} status: {1} price: {2}{3} amount: {4}", new Object[]{order.getOid(), order.getStatus(), order.getCurrency().getCurrencyCode(), order.getPrice().getDisplay(), order.getAmount().getDisplay()});
            }
        } else {
            logger.info("There are no currently open bid or ask orders.");
        }
        context = null;
        System.exit(0);
    }
}

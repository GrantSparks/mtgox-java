package to.sparks.mtgox.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import to.sparks.mtgox.MtGoxHTTPClient;
import to.sparks.mtgox.model.CurrencyInfo;
import to.sparks.mtgox.model.Lag;
import to.sparks.mtgox.model.MtGoxBitcoin;
import to.sparks.mtgox.model.MtGoxFiatCurrency;
import to.sparks.mtgox.model.Ticker;

import java.math.BigDecimal;

/**
 * Created by si on 12/17/13.
 */
public class StopLimit {

    private static final Logger logger = LoggerFactory.getLogger(StopLimit.class);

    public static void main(String[] args) throws Exception {
        // Obtain a $USD instance of the API
        ApplicationContext context = new ClassPathXmlApplicationContext("to/sparks/mtgox/example/Beans.xml");
        MtGoxHTTPClient mtgoxUSD = (MtGoxHTTPClient) context.getBean("mtgoxUSD");

//        Lag lag = mtgoxUSD.getLag();
//        logger.log(Level.INFO, "Current lag: {0}", lag.getLag());


        boolean placedOrder = false;

        BigDecimal stopLimitPrice = new BigDecimal("712");

        CurrencyInfo currencyInfo = mtgoxUSD.getCurrencyInfo(mtgoxUSD.getBaseCurrency());
        logger.debug("Base currency: {}", currencyInfo.getCurrency().getCurrencyCode());

        while (!placedOrder) {
            try {

                Thread.sleep(20000L);  // poll every 20 seconds

                Ticker ticker = mtgoxUSD.getTicker();
                logger.debug("Last price: {}", ticker.getLast().toPlainString());

                if (ticker.getLast().compareTo(stopLimitPrice) < 0) {
                    // then sell!!!
                    placedOrder = true;
                    logger.info("Placing SELL order!");

                    // sell 1.1 bitcoins for 710 (exchange rate)
                    MtGoxFiatCurrency fiatUnit = new MtGoxFiatCurrency(new BigDecimal("710"), currencyInfo);  // We use the currencyInfo to be explicit about what this money represents
                    MtGoxBitcoin bitcoinUnit = new MtGoxBitcoin(new BigDecimal("1.1"));  // You should probably use BigDecimals instead of double types to represent money.
                    String orderRef = mtgoxUSD.placeOrder(MtGoxHTTPClient.OrderType.Ask, fiatUnit, bitcoinUnit);
                    logger.info("orderRef: {}", new Object[]{orderRef});

                }


            } catch (Exception e) {
                logger.error("Problem hitting mtgox", e);
                throw e;
            }
        }





    }
}

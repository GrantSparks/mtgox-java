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
package to.sparks.mtgox.examples;

import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import to.sparks.mtgox.DepthEvent;
import to.sparks.mtgox.StreamEvent;
import to.sparks.mtgox.TickerEvent;
import to.sparks.mtgox.TradeEvent;
import to.sparks.mtgox.model.Depth;
import to.sparks.mtgox.model.Ticker;
import to.sparks.mtgox.model.Trade;

/**
 * Shows how to use the MtGox real-time streaming websocket API
 *
 * @author SparksG
 */
public class WebsocketExamples implements ApplicationListener<StreamEvent> {

    /* The logger */
    static final Logger logger = Logger.getLogger(WebsocketExamples.class.getName());

    public WebsocketExamples() {
    }

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("to/sparks/mtgox/examples/WebsocketExamples.xml");
        WebsocketExamples me = context.getBean("websocketExamples", WebsocketExamples.class);
    }

    @Override
    public void onApplicationEvent(StreamEvent event) {

        if (event instanceof DepthEvent) {
            Depth depth = (Depth) event.getPayload();
            logger.info(depth.getItem());
        } else if (event instanceof TickerEvent) {
            Ticker ticker = (Ticker) event.getPayload();
            logger.info(ticker.getLast().toPlainString());
        } else if (event instanceof TradeEvent) {
            Trade trade = (Trade) event.getPayload();
            logger.info(trade.getPrice_currency());
        }
    }
}

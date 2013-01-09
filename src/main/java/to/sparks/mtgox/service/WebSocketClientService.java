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
package to.sparks.mtgox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jwebsocket.client.java.BaseWebSocket;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import to.sparks.mtgox.WebSocketClient;
import to.sparks.mtgox.model.Depth;
import to.sparks.mtgox.model.IEventTime;
import to.sparks.mtgox.model.Ticker;
import to.sparks.mtgox.model.Trade;
import to.sparks.mtgox.net.EventListener;
import to.sparks.mtgox.net.SocketListener;

/**
 *
 * @author SparksG
 */
class WebSocketClientService implements EventListener, Runnable, WebSocketClient {

    private Logger logger;
    private List<Depth> depthHistory = new CopyOnWriteArrayList<>();
    private List<Ticker> tickerHistory = new CopyOnWriteArrayList<>();
    private List<Trade> tradeHistory = new CopyOnWriteArrayList<>();
    final BaseWebSocket websocket = new BaseWebSocket();
    private ThreadPoolTaskExecutor taskExecutor;

    public WebSocketClientService(Logger logger, ThreadPoolTaskExecutor taskExecutor) {
        this.logger = logger;
        this.taskExecutor = taskExecutor;
    }

    public void init() {
        taskExecutor.execute(this);
    }

    public void destroy() {
        try {
            websocket.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutdown() {
        taskExecutor.shutdown();
    }

    /*
     * Return ALL the depth items younger than the timestamp
     */
    @Override
    public List<Depth> getAllDepthSince(long timestamp) {
        int eventIndex = getEventIndex(depthHistory, timestamp);
        return eventIndex <= depthHistory.size() - 1 ? depthHistory.subList(eventIndex, depthHistory.size()) : new ArrayList<Depth>();
    }

    @Override
    public List<Trade> getAllTradesSince(long timestamp) {
        int eventIndex = getEventIndex(tradeHistory, timestamp);
        return eventIndex <= tradeHistory.size() - 1 ? tradeHistory.subList(eventIndex, tradeHistory.size()) : new ArrayList<Trade>();
    }

    private int getEventIndex(List list, long timestamp) {
        int length = list.size();
        int result = length;
        for (int i = 0; i < length; i++) {
            IEventTime eventTime = (IEventTime) list.get(i);
            if (eventTime != null && eventTime.getEventTime() > timestamp) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public void tradeEvent(Trade trade) {
        getTradeHistory().add(trade);
    }

    @Override
    public void tickerEvent(Ticker ticker) {
        tickerHistory.add(ticker);
    }

    @Override
    public void depthEvent(Depth depth) {
        depthHistory.add(depth);
    }

    @Override
    public List<Depth> getDepthHistory() {
        return depthHistory;
    }

    @Override
    public List<Ticker> getTickerHistory() {
        return tickerHistory;
    }

    @Override
    public List<Trade> getTradeHistory() {
        return tradeHistory;
    }

    @Override
    public void run() {
        try {

            websocket.addListener(new SocketListener(logger, this));
            websocket.open("ws://websocket.mtgox.com/mtgox");
            logger.info("WS API started.");

        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }
}

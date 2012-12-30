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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import to.sparks.mtgox.MtGoxAPI;
import to.sparks.mtgox.model.*;

/**
 * All MtGox API interactions (both HTTP/REST and Websocket) are handled by this
 * API class.
 *
 * @author SparksG
 */
class MtGoxServiceImpl implements MtGoxAPI {

    private static Logger logger;
    private MtGoxWebSocketClient wsApi;
    private Currency currency;
    MtGoxHTTPClient httpAPI;

    public MtGoxServiceImpl(final Logger logger, MtGoxHTTPClient httpAPI, MtGoxWebSocketClient mtGoxWebSocketApi, Currency currency) {
        this.logger = logger;
        this.currency = currency;
        this.httpAPI = httpAPI;
        this.wsApi = mtGoxWebSocketApi;
    }

    @Override
    public List<Depth> getAllDepthSince(long timestamp) {
        return wsApi != null ? wsApi.getAllDepthSince(timestamp) : null;
    }

    @Override
    public List<Trade> getAllTradesSince(long timestamp) {
        return wsApi != null ? wsApi.getAllTradesSince(timestamp) : null;
    }

    @Override
    public List<Depth> getDepthHistory() {
        return wsApi != null ? wsApi.getDepthHistory() : null;
    }

    @Override
    public List<Ticker> getTickerHistory() {
        return wsApi != null ? wsApi.getTickerHistory() : null;
    }

    @Override
    public List<Trade> getTradeHistory() {
        return wsApi != null ? wsApi.getTradeHistory() : null;
    }

    @Override
    public FullDepth getFullDepth() throws IOException, Exception {
        return httpAPI.getFullDepth(currency);
    }

    @Override
    public String placeOrder(OrderType orderType, MtGoxFiatUnit price, MtGoxBitcoinUnit volume) throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception {

        HashMap<String, String> params = new HashMap<>();
        if (orderType == OrderType.Bid) {
            params.put("type", "bid");
        } else {
            params.put("type", "ask");
        }

        if (price != null) {
//            params.put("price_int", String.valueOf(convertPricetoInt(currency.getCurrencyCode(), price)));
            params.put("price_int", String.valueOf(price.getCredits().unscaledValue().longValue()));
        }
        params.put("amount_int", String.valueOf(volume.getCredits().unscaledValue().longValue()));

        return httpAPI.placeOrder(currency, params);
    }

    @Override
    public String placeMarketOrder(OrderType orderType, MtGoxBitcoinUnit volume) throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception {
        return placeOrder(orderType, null, volume);
    }

    @Override
    public OrderResult getOrderResult(OrderType orderType, String orderRef) throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception {
        HashMap<String, String> params = new HashMap<>();
        if (orderType == OrderType.Bid) {
            params.put("type", "bid");
        } else {
            params.put("type", "ask");
        }
        params.put("order", orderRef);

        return httpAPI.getPrivateOrderResult(params);
    }

    @Override
    public Order[] getOpenOrders() throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception {
        return httpAPI.getOpenOrders();
    }

    @Override
    public Ticker getTicker() throws IOException, Exception {
        return httpAPI.getTicker(currency);
    }

    @Override
    public Currency getBaseCurrency() {
        return currency;
    }

    @Override
    public Info getInfo() throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception {
        return httpAPI.getPrivateInfo();
    }
}
package to.sparks.mtgox.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author SparksG
 */
@JsonAutoDetect
public class Trade extends DtoBase implements IEventTime, CurrencyKludge {

    private MtGoxBitcoinUnit amount;
    private long price_int;
    private long date;
    private String item;
    private String type;
    private String primary;
    private String properties;
    private String tid;
    private String trade_type;
    private CurrencyInfo currencyInfo = null;

    public Trade(@JsonProperty("tid") String tid,
            @JsonProperty("primary") String primary,
            @JsonProperty("price_currency") String price_currency,
            @JsonProperty("type") String type,
            @JsonProperty("properties") String properties,
            @JsonProperty("item") String item,
            @JsonProperty("amount") double amount,
            @JsonProperty("price") double price,
            @JsonProperty("trade_type") String trade_type,
            @JsonProperty("date") long date,
            @JsonProperty("amount_int") long amount_int,
            @JsonProperty("price_int") long price_int) {
        this.tid = tid;
        this.primary = primary;
        this.type = type;
        this.properties = properties;
        this.item = item;
        this.trade_type = trade_type;
        this.date = date;
        this.amount = new MtGoxBitcoinUnit(amount_int);
        this.price_int = price_int;
    }

    public String getTradeId() {
        return tid;
    }

    public String getPrimary() {
        return primary;
    }

    public String getType() {
        return type;
    }

    public String getProperties() {
        return properties;
    }

    public String getItem() {
        return item;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public long getDate() {
        return date;
    }

    public MtGoxBitcoinUnit getAmount() {
        return amount;
    }

    public MtGoxFiatUnit getPrice() {
        return new MtGoxFiatUnit(price_int, currencyInfo);
    }

    @Override
    public long getEventTime() {
        return getDate();
    }

    @Override
    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
    }
}

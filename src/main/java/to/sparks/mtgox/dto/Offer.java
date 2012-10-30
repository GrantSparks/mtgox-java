package to.sparks.dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * {
 *  "price":12.06422, 
 *  "amount":0.2,
 *  "price_int": "1206422",
 *  "amount_int":"20000000", 
 *  "stamp":"1348182250883337" 
 * }
 *
 * @author SparksG
 */
public class Offer {
    private double price;
    private double amount;
    private long price_int;
    private long amount_int;
    private long stamp;

    public Offer(@JsonProperty("price")  double price,
            @JsonProperty("amount") double amount,
            @JsonProperty("price_int") long price_int,
            @JsonProperty("amount_int") long amount_int,
            @JsonProperty("stamp") long stamp )
    {
     this.price = price;
     this.amount=amount;
     this.price_int=price_int;
     this.amount_int=amount_int;
     this.stamp=stamp;
    }
    
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the price_int
     */
    public long getPrice_int() {
        return price_int;
    }

    /**
     * @param price_int the price_int to set
     */
    public void setPrice_int(long price_int) {
        this.price_int = price_int;
    }

    /**
     * @return the amount_int
     */
    public long getAmount_int() {
        return amount_int;
    }

    /**
     * @param amount_int the amount_int to set
     */
    public void setAmount_int(long amount_int) {
        this.amount_int = amount_int;
    }

    /**
     * @return the stamp
     */
    public long getStamp() {
        return stamp;
    }

    /**
     * @param stamp the stamp to set
     */
    public void setStamp(long stamp) {
        this.stamp = stamp;
    }
    
}

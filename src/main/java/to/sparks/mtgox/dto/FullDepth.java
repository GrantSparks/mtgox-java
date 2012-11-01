package to.sparks.mtgox.dto;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author SparksG
 */
public class FullDepth {

    private Offer[] asks;
    private Offer[] bids;

    @JsonCreator
    public FullDepth(@JsonProperty("asks") Offer[] asks,
            @JsonProperty("bids") Offer[] bids) {
        this.asks = asks;
        this.bids = bids;
    }

    /**
     * @return the asks
     */
    public Offer[] getAsks() {
        return asks;
    }

    /**
     * @return the bids
     */
    public Offer[] getBids() {
        return bids;
    }
}

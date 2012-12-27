package to.sparks.mtgox.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author SparksG
 */
@JsonAutoDetect
public class OpPrivateDepth extends OpPrivate<Depth> {

    private Depth depth;

    public OpPrivateDepth(@JsonProperty("op") String op,
            @JsonProperty("channel") String channel,
            @JsonProperty("private") String messageType,
            @JsonProperty("depth") Depth depth,
            @JsonProperty("origin") String origin) {
        super(op, channel, messageType, origin);
        this.depth = depth;
    }

    /**
     * @return the depth
     */
    public Depth getDepth() {
        return depth;
    }
}

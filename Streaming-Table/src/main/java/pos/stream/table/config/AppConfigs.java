package pos.stream.table.config;

public class AppConfigs {
    public final static String applicationID = "StreamingTable";
    public final static String bootstrapServers = "localhost:19092";
    public final static String topicName = "stock-tick";
    public final static String stateStoreLocation = "tmp/state-store";
    public final static String stateStoreName = "kt01-store";
    public final static String regExSymbol = "(?i)HDFCBANK|TCS";

}

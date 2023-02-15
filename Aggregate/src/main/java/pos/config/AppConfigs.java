package pos.config;

public class AppConfigs {
    public final static String applicationID = "aggregate-1";
    public final static String bootstrapServers = "localhost:19092";
    public final static String posTopicName = "word-count";
    public final static String reduceTopicName = "pos-invoice-1";
    public final static String aggregateTopic = "agg-topic";
    public final static String stateStoreName = "word-count-store";
    public final static String notificationTopic = "loyalty";
    public final static String hadoopTopic = "hadoop-sink";
    public final static String DELIVERY_TYPE_HOME_DELIVERY = "HOME-DELIVERY";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static Double LOYALTY_FACTOR = 0.02;
}

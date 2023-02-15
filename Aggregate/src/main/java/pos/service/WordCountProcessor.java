package pos.service;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.Notifications;
import pos.config.AppConfigs;
import project.commons.domain.Notification;
import project.commons.serdes.AppSerdes;

import java.util.Arrays;

@Service
public class WordCountProcessor {

    @Autowired
    public void streamWords(StreamsBuilder streamsBuilder) {
        KStream<String,String> wordStreams = streamsBuilder.stream(AppConfigs.posTopicName);
        KStream<String,String> wordsStreams = wordStreams.flatMapValues(value -> Arrays.asList(value.split(" ")));
        KGroupedStream<String,String> keyWordsStream = wordsStreams.groupBy((key, value) -> value);
        keyWordsStream.count().toStream().print(Printed.toSysOut());
        streamsBuilder.build();

    }

    @Autowired
    public void reduce(StreamsBuilder streamsBuilder) {
        KStream<String,Notification> notificationsKStream = streamsBuilder.stream(AppConfigs.reduceTopicName, Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()))
                .filter((key,value) -> value.getCustomerType().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME))
                .map((key,value) -> new KeyValue<>(value.getCustomerCardNo(), Notifications.getNotification(value)));

        KGroupedStream<String,Notification> notificationKGroupedStream = notificationsKStream.groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.Notification()));

        KTable<String,Notification> notificationKTable = notificationKGroupedStream.reduce((aggValue,newValue) -> {
            newValue.setTotalLoyaltyPoints(newValue.getEarnedLoyaltyPoints() + aggValue.getTotalLoyaltyPoints());
            return newValue;
        });
        notificationKTable.toStream().print(Printed.toSysOut());
        streamsBuilder.build();
    }

//    @Autowired
//    public void aggregate(StreamsBuilder streamsBuilder) {
//        streamsBuilder.stream(AppConfigs.aggregateTopic,Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()))
//                .groupBy((key,value) -> value.getCustomerCardNo())
//                .aggregate(
//                        //initializer
//                        () ->
//                        //aggregator
//                        //seriazlizer
//                );
//    }
}

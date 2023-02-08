package pos.rewards.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pos.rewards.RewardsTransformer;
import pos.rewards.config.AppConfigs;
import project.commons.domain.Notification;
import project.commons.domain.PosInvoice;
import project.commons.serdes.AppSerdes;

@Service
public class InvoiceStreamService {
    private KeyValueStore<String,Double> doubleKeyValue ;
    @Autowired
    public void streamInvoices(StreamsBuilder streamsBuilder) {
        KStream<String, PosInvoice> posInvoiceKStream = streamsBuilder.stream(AppConfigs.posTopicName,Consumed.with(Serdes.String(), AppSerdes.PosInvoice()));
        posInvoiceKStream.print(Printed.toSysOut());

        KStream<String, PosInvoice> posInvoiceKStream1 = posInvoiceKStream.filter((key,value) -> value.getCustomerType().equalsIgnoreCase("PRIME"));
        StoreBuilder<KeyValueStore<String, Double>> storeBuilder = Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore(AppConfigs.REWARDS_STORE_NAME),
//                it will create an embedded local RocksDB database
//                Stores.persistentKeyValueStore(AppConfigs.REWARDS_STORE_NAME),
                AppSerdes.String(),AppSerdes.Double()
        );

        streamsBuilder.addStateStore(storeBuilder);


        posInvoiceKStream1.transformValues(RewardsTransformer::new,AppConfigs.REWARDS_STORE_NAME)
                .to(AppConfigs.notificationTopic, Produced.with(AppSerdes.String(),AppSerdes.Notification()));
    }


}

package pos.stream.table.service;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Suppressed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.stream.table.config.AppConfigs;

import java.time.Duration;

@Service
public class StreamTableService {

    @Autowired
    public void streamTables(StreamsBuilder streamsBuilder) {

        KTable<String,String> stringStringKTable = streamsBuilder.table(AppConfigs.topicName);
        stringStringKTable.toStream().print(Printed.toSysOut());

        KTable<String,String> stringStringKTable1 = stringStringKTable
                .filter((key,value) -> key.matches(AppConfigs.regExSymbol) && !value.isEmpty()
                , Materialized.as(AppConfigs.stateStoreName));
        stringStringKTable1.toStream().print(Printed.toSysOut());

        KTable<String,String> stringStringKTable2 = stringStringKTable1
                .filter((key,value) -> key.matches("ICICI") && !value.isEmpty()
                        , Materialized.as("new-state-store"))
                .suppress(Suppressed.untilTimeLimit(Duration.ofMinutes(5),Suppressed.BufferConfig.maxBytes(100000L).emitEarlyWhenFull()));
        stringStringKTable2.toStream().print(Printed.toSysOut());



    }
}

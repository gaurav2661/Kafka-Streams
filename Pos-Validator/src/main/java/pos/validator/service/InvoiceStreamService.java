package pos.validator.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.validator.RecordBuilder;
import pos.validator.config.AppConfigs;
import project.commons.domain.Notification;
import project.commons.domain.PosInvoice;
import project.commons.serdes.AppSerdes;


@Service
public class InvoiceStreamService {

    @Autowired
    public void streamInvoices(StreamsBuilder streamsBuilder) {
        KStream<String, PosInvoice> posInvoiceKStream = streamsBuilder.stream(AppConfigs.posTopicName,Consumed.with(Serdes.String(), AppSerdes.PosInvoice()));
        posInvoiceKStream.print(Printed.toSysOut());


        KStream<String,PosInvoice> posInvoiceKStreamFiltered = posInvoiceKStream.filter((key,value) -> value.getDeliveryType().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY));
        posInvoiceKStreamFiltered.to(AppConfigs.shipmentTopicName, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice()));

        posInvoiceKStream.filter((key,value) -> value.getCustomerType().equalsIgnoreCase("PRIME"))
                .mapValues(RecordBuilder::getNotification).print(Printed.toSysOut());
    }
}

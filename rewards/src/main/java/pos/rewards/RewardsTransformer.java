package pos.rewards;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.stereotype.Service;
import pos.rewards.config.AppConfigs;
import project.commons.domain.Notification;
import project.commons.domain.PosInvoice;

@Slf4j
@Service
public class RewardsTransformer implements ValueTransformer<PosInvoice, Notification> {

    private KeyValueStore<String,Double> doubleKeyValue ;
    @Override
    public void init(ProcessorContext processorContext) {
        this.doubleKeyValue = processorContext.getStateStore(AppConfigs.REWARDS_STORE_NAME);
    }

    @Override
    public Notification transform(PosInvoice posInvoice) {
        Notification notification = new Notification();
        notification.setInvoiceNumber(posInvoice.getInvoiceNumber());
        notification.setCustomerCardNo(posInvoice.getCustomerCardNo());
        notification.setTotalAmount(posInvoice.getTotalAmount());
        notification.setEarnedLoyaltyPoints(posInvoice.getTotalAmount()*AppConfigs.LOYALTY_FACTOR);
        doubleKeyValue.put(posInvoice.getCustomerCardNo(),notification.getEarnedLoyaltyPoints() );
        notification.setTotalLoyaltyPoints(0.0);
        Double accumulatedPoint = doubleKeyValue.get(posInvoice.getCustomerCardNo());
        if(accumulatedPoint != null) {
            notification.setTotalLoyaltyPoints(accumulatedPoint+notification.getEarnedLoyaltyPoints());
            doubleKeyValue.put(posInvoice.getCustomerCardNo(),accumulatedPoint+notification.getEarnedLoyaltyPoints() );
        }
        return notification;
    }

    @Override
    public void close() {

    }
}

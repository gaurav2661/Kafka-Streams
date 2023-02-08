package pos.simulator.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.simulator.datagenerator.InvoiceGenerator;
import project.commons.domain.PosInvoice;


@RestController
@RequestMapping("/produce")
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, PosInvoice> posInvoiceKafkaTemplate;
    @GetMapping("/invoices")
    public void produceInvoices(){
        for(int i = 0;i<100;i++) {
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            PosInvoice posInvoice = invoiceGenerator.getNextInvoice();
            ProducerRecord<String, PosInvoice> producerRecord = new ProducerRecord<>("pos-invoice",posInvoice.getPosID(),posInvoice);
            posInvoiceKafkaTemplate.send(producerRecord);
        }
    }

}

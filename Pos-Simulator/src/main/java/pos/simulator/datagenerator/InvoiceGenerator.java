
package pos.simulator.datagenerator;

import org.springframework.stereotype.Service;
import project.commons.domain.LineItem;
import project.commons.domain.PosInvoice;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InvoiceGenerator {
    List<String> deliveryType = new ArrayList<>();
    List<String> customerType = new ArrayList<>();
    public PosInvoice getNextInvoice() {
        Random random = new Random();
        PosInvoice invoice = new PosInvoice();
        invoice.setInvoiceNumber(Integer.toString(random.nextInt(999999)));
        invoice.setCreatedTime(System.currentTimeMillis());
        invoice.setStoreID(String.valueOf(random.nextInt(9999)));
        invoice.setCashierID(String.valueOf(random.nextInt(9999)));
        invoice.setPosID(String.valueOf(random.nextInt(9999)));
        invoice.setCustomerCardNo(String.valueOf(random.nextInt(9999999)));
        invoice.setCustomerType(getCustomerType().get(random.nextInt(2)));
        int itemCount = random.nextInt(10);
        Double totalAmount = 0.0;
        List<LineItem> items = new ArrayList<>();
        ProductGenerator productGenerator = new ProductGenerator();
        for (int i = 0; i < itemCount; i++) {
            LineItem item = productGenerator.getNextProduct();
            totalAmount = totalAmount + item.getTotalValue();
            items.add(item);
        }
        invoice.setDeliveryType(getDeliveryType().get(random.nextInt(2)));
        invoice.setNumberOfItems(itemCount);
        invoice.setInvoiceLineItems(items);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxableAmount(totalAmount);
        invoice.setCGST(totalAmount * 0.025);
        invoice.setSGST(totalAmount * 0.025);
        invoice.setCESS(totalAmount * 0.00125);
        return invoice;
    }

    public List<String> getDeliveryType() {
        deliveryType.add("HOME-DELIVERY");
        deliveryType.add("PICK-UP");
        return deliveryType;
    }

    public List<String> getCustomerType() {
        customerType.add("PRIME");
        customerType.add("NON-PRIME");
        return customerType;
    }

}

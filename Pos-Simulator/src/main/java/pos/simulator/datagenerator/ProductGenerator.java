
package pos.simulator.datagenerator;

import org.springframework.stereotype.Service;
import project.commons.domain.LineItem;

import java.util.Random;

@Service
public class ProductGenerator {

    public LineItem getNextProduct() {
        Random random = new Random();
        LineItem lineItem = new LineItem();
        lineItem.setItemQty(random.nextInt(10));
        lineItem.setItemCode(getItemCode()+random.nextInt(100000));
        lineItem.setItemPrice(random.nextInt(10000));
        lineItem.setTotalValue(lineItem.getItemPrice() * lineItem.getItemQty());
        return lineItem;
    }

    private String getItemCode(){
        return "GHTYY";
    }
}

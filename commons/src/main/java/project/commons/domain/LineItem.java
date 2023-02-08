
package project.commons.domain;

import lombok.Data;

@Data
public class LineItem {

    private String itemCode;
    private String itemDescription;
    private Integer itemPrice;
    private Integer itemQty;
    private Integer totalValue;

}

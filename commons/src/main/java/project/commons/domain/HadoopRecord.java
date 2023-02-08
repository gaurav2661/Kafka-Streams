
package project.commons.domain;

import lombok.Data;


@Data
public class HadoopRecord {

    private String invoiceNumber;
    private Long createdTime;
    private String storeID;
    private String posID;
    private String customerType;
    private String paymentMethod;
    private String deliveryType;
    private String city;
    private String state;
    private Integer pinCode;
    private String itemCode;
    private String itemDescription;
    private Integer itemPrice;
    private Integer itemQty;
    private Integer totalValue;



}

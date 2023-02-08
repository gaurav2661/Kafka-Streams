
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
    private String pinCode;
    private String itemCode;
    private String itemDescription;
    private Double itemPrice;
    private Integer itemQty;
    private Double totalValue;



}

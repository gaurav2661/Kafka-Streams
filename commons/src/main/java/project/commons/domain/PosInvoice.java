
package project.commons.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
public class PosInvoice {
    private UUID uuid;
    private String invoiceNumber;
    private Long createdTime;
    private String storeID;
    private String posID;
    private String cashierID;
    private String customerType;
    private String customerCardNo;
    private Double totalAmount;
    private Integer numberOfItems;
    private String paymentMethod;
    private Double taxableAmount;
    private Double cGST;
    private Double sGST;
    private Double cESS;
    private String deliveryType;
    private DeliveryAddress deliveryAddress;
    private List<LineItem> invoiceLineItems = new ArrayList<LineItem>();
}

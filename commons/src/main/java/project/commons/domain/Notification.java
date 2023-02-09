
package project.commons.domain;

import lombok.Data;


@Data
public class Notification {
    private String invoiceNumber;
    private String customerCardNo;
    private Double totalAmount;
    private Double earnedLoyaltyPoints;
    private Double totalLoyaltyPoints;

}

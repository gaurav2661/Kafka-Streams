
package project.commons.domain;

import lombok.Data;

@Data
public class DeliveryAddress {

    private String addressLine;
    private String city;
    private String state;
    private Integer pinCode;
    private Integer contactNumber;

}

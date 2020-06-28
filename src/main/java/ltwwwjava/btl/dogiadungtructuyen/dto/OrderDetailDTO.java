package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDetailDTO implements Serializable {

    private String id;
    private int status;
    private Date billDate;
    private double tax;
    private String customer;
    private String products;
    private int quantity;
    private String orderId;
}

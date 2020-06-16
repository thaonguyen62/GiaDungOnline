package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDTO implements Serializable {

    private String id;
    private int status;
    private Date billDate;
    private double tax;
    private String customer;
    private String products;
    private int quantity;
}

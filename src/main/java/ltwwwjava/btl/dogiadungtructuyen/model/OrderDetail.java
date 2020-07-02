package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class OrderDetail implements Serializable {
    @Id
    private String id;
    private int status;
    private Date billDate;
    private double tax;
    private String customer;
    private Product products;
    private int quantity;
    private String orderId;
    //private String serviceAddress;


}

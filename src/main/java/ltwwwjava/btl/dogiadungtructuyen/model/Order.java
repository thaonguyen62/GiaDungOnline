package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.dto.CustomerDTO;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Order implements Serializable {



    @Id
    private String id;
    private int status;
    private Date billDate;
    private double tax;
    private String customer;
    private String products;
    private int quantity;
    //private String serviceAddress;


}

package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Feedback {


    @Id
    private String id;
    private String name;
    private Date date;
    private String content;
    private String phone;
    private String product;

}

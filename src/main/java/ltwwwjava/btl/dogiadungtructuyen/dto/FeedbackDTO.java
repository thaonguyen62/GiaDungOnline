package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FeedbackDTO implements Serializable {

    private String id;
    private String name;
    private Date date;
    private String content;
    private String phone;
    private String product;

}

package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Customer implements Serializable {

    @Id
    private String id;
    private String name;
    private String address;
    private Date birth;
    private String phone;
    private String mail;
    private String username;
    private String password;

}

package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String name;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
    private String mail;
    private String username;
    private String password;
    private String retypePassword;
    private int accountType;
}

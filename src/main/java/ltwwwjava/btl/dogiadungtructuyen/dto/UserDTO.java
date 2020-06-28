package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String mail;

}

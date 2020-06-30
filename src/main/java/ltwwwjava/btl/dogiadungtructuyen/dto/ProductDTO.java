package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDTO implements Serializable {

    private String id;
    private String name;
    private double price;
    private String description;
    private String category;
    private List<String> fileName;


}

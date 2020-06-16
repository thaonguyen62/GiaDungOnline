package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.dto.CategoryDTO;
import ltwwwjava.btl.dogiadungtructuyen.model.Feedback;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDTO implements Serializable {

    private String id;
    private String name;
    private double price;
    private String description;
    private String category;
    private String fileName;


}

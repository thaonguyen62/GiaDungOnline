package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@Document
public class Category implements Serializable {

    @Id
    private String id;
    private String name;
    private int status;


}

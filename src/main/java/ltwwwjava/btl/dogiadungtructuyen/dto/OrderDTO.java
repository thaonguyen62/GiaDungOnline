package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    List<OrderDetailDTO> list;
    double price;
}

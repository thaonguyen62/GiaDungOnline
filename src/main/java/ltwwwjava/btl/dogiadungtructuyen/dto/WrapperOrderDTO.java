package ltwwwjava.btl.dogiadungtructuyen.dto;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;

import java.util.List;

@Data
public class WrapperOrderDTO {
    List<OrderDetail> orderDetails;
}

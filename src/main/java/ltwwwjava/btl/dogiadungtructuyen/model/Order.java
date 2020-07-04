package ltwwwjava.btl.dogiadungtructuyen.model;

import lombok.Data;
import ltwwwjava.btl.dogiadungtructuyen.dto.OrderDetailDTO;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    List<OrderDetail> orderDetailDTOS;
    Date billDate;
    double total;
    private String idCustomer;
}

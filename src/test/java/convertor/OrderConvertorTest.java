package convertor;

import com.zooplus.convertor.OrderConvertor;
import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.OrderDetailResponse;
import com.zooplus.util.DateUtil;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class OrderConvertorTest extends OrderConvertor {

    @Test
    public void convertOrderToOrderDOTest(){
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setAmount(1);
        orderDetailRequest.setCurrency(Currency.EUR);
        orderDetailRequest.setCustomerId(1);
        OrderDO order = OrderConvertor.convertOrderToOrderDO(orderDetailRequest);
        assertEquals(OrderStatusEnum.INIT.name(), order.getOrderStatus());
        assertEquals(1, order.getAmount());
        assertEquals(Currency.EUR.name(), order.getCurrency());
        assertEquals(1, order.getCustomerId());
    }

    @Test
    public void reconvertToOrderDetailResponseTest(){
        OrderDO orderDO = new OrderDO();
        orderDO.setAmount(1);
        orderDO.setCurrency(Currency.EUR.name());
        orderDO.setCustomerId(1);
        orderDO.setOrderBal(-1);
        orderDO.setOrderStatus(OrderStatusEnum.INIT.name());
        OrderDetailResponse order = OrderConvertor.reconvertToOrderDetailResponse(orderDO);
        assertEquals(OrderStatusEnum.INIT.name(), order.getOrderStatus());
        assertEquals(1, order.getOrigAmount());
        assertEquals(-1, order.getOrderBal());
        assertEquals(Currency.EUR, order.getOrderAmtCurr());
        assertEquals(1, order.getCustomerId());
    }
}

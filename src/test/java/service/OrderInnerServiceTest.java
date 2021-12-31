package service;

import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.persistence.OrderRepository;
import com.zooplus.service.OrderInnerService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class OrderInnerServiceTest extends AbstractTest{
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderInnerService orderInnerService;

    @Test
    public void createOrUpdateOrderTest() {
        Mockito.when(orderRepository.save(Mockito.any(OrderDO.class))).thenReturn(getOrderDO());
        OrderDO order = orderInnerService.createOrUpdateOrder(new OrderDO());
        assertEquals(order.getOrderStatus(), OrderStatusEnum.INIT.name());
    }

    @Test
    public void getOrderDetails() throws ZooplusCommonException {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getOrderDO()));
        OrderDO order = orderInnerService.getOrderDetails(1);
        assertEquals(order.getOrderStatus(), OrderStatusEnum.INIT.name());
    }

    @Test
    public void lockOrderDetails() throws ZooplusCommonException {
        Mockito.when(orderRepository.lockOrderDetails(Mockito.anyLong())).thenReturn(Optional.of(getOrderDO()));
        OrderDO order = orderInnerService.lockOrderDetails(1);
        assertEquals(order.getOrderStatus(), OrderStatusEnum.INIT.name());
    }

    @Test(expected = ZooplusCommonException.class)
    public void getOrderDetailsExceptionTest()  {
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        OrderDO order = orderInnerService.getOrderDetails(1);
     }

    @Test(expected = ZooplusCommonException.class)
    public void lockOrderDetailsExceptionTest() {
        Mockito.when(orderRepository.lockOrderDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        OrderDO order = orderInnerService.lockOrderDetails(1);
    }

    private OrderDO getOrderDO(){
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderBal(1);
        orderDO.setOrderStatus(OrderStatusEnum.INIT.name());
        orderDO.setCurrency(Currency.EUR.name());
        orderDO.setCustomerId(1);
        orderDO.setAmount(1);
        return orderDO;
    }

}

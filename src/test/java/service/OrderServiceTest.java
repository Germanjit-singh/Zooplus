package service;

import com.zooplus.convertor.OrderConvertor;
import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.request.OrderDetailRequest;
import com.zooplus.model.response.OrderDetailResponse;
import com.zooplus.service.CustomerInnerService;
import com.zooplus.service.CustomerService;
import com.zooplus.service.OrderInnerService;
import com.zooplus.service.OrderService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest extends AbstractTest{

    @Mock
    CustomerInnerService customerInnerService;

    @Mock
    OrderInnerService orderInnerService;

    @Mock
    CustomerDO customerDO;

    @InjectMocks
    OrderService orderService;

    @Test
    public void getOrderDetailsTest() throws ZooplusCommonException {
        Mockito.when(orderInnerService.getOrderDetails(Mockito.anyLong())).thenReturn(getOrderDO());
        OrderDetailResponse order = orderService.getOrderDetails(1);
        assertEquals(order.getOrderStatus(), OrderStatusEnum.INIT.name());
    }
    @Test
    public void createOrUpdateOrderTest() throws ZooplusCommonException {
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenReturn(customerDO);
        Mockito.when(customerInnerService.lockCustomerDetails(Mockito.anyLong())).thenReturn(customerDO);
        Mockito.when(orderInnerService.createOrUpdateOrder(Mockito.any(OrderDO.class))).thenReturn(getOrderDO());
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setCustomerId(1);
        orderDetailRequest.setAmount(1);
        orderDetailRequest.setCurrency(Currency.EUR);
        OrderDetailResponse order = orderService.createOrUpdateOrder(orderDetailRequest);
        assertEquals(order.getOrderStatus(),OrderStatusEnum.INIT.name());
    }
    @Test
    public void getOrderBalanceTest() throws ZooplusCommonException {
        Mockito.when(orderInnerService.getOrderDetails(Mockito.anyLong())).thenReturn(getOrderDO());
        String orderAmt = orderService.getOrderBalance(1);
        assertEquals(orderAmt, "1 EUR");

    }

    @Test(expected = ZooplusCommonException.class)
    public void getOrderDetailsExceptionTest()  {
        Mockito.when(orderInnerService.getOrderDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        OrderDetailResponse order = orderService.getOrderDetails(1);
    }
    @Test(expected = ZooplusCommonException.class)
    public void createOrUpdateOrderExceptionTest(){
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        OrderDetailResponse order = orderService.createOrUpdateOrder(new OrderDetailRequest());
    }
    @Test(expected = ZooplusCommonException.class)
    public void getOrderBalanceExceptionTest()  {
        Mockito.when(orderInnerService.getOrderDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        String orderAmt = orderService.getOrderBalance(1);
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

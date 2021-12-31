package service;

import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.model.request.PaymentDetailRequest;
import com.zooplus.model.response.PaymentDetailResponse;
import com.zooplus.service.CustomerInnerService;
import com.zooplus.service.OrderInnerService;
import com.zooplus.service.PaymentInnerService;
import com.zooplus.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentServiceTest extends AbstractTest{

    @Mock
    OrderInnerService orderInnerService;

    @Mock
    CustomerInnerService customerInnerService;

    @Mock
    PaymentInnerService paymentInnerService;

    @Mock
    OrderDO orderDO;

    @Mock
    CustomerDO customerDO;

    @InjectMocks
    PaymentService paymentService;

    @Test
    public void makePaymentTest() throws Exception {
        PaymentDetailRequest pdr = new PaymentDetailRequest();
        pdr.setOrderId(1);
        pdr.setAmount(1);
        pdr.setCurrency(Currency.EUR);
        pdr.setPayToolType("CREDIT_CARD");
        Mockito.when(orderInnerService.getOrderDetails(Mockito.anyLong())).thenReturn(orderDO);
        Mockito.when(orderInnerService.lockOrderDetails(Mockito.anyLong())).thenReturn(orderDO);
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenReturn(customerDO);
        Mockito.when(customerInnerService.lockCustomerDetails(Mockito.anyLong())).thenReturn(customerDO);
        Mockito.when(paymentInnerService.makePayment(Mockito.any(PaymentDO.class))).thenReturn(getPaymentDO());
        PaymentDetailResponse pdrs = paymentService.makePayment(pdr);
        assertEquals(pdrs.getPaymentStatus(), PaymentStatusEnum.FULLY_PAID);
    }

    @Test
    public void getPaymentDetailsTest() throws Exception {
        Mockito.when(paymentInnerService.getPaymentDetails(Mockito.anyLong())).thenReturn(getPaymentDO());
        PaymentDetailResponse pdrs = paymentService.getPaymentDetails(1);
        assertEquals(pdrs.getPaymentStatus(), PaymentStatusEnum.FULLY_PAID);
    }

    @Test(expected = ZooplusCommonException.class)
    public void getPaymentDetailsExceptionTest() throws Exception {
        Mockito.when(paymentInnerService.getPaymentDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        PaymentDetailResponse pdrs = paymentService.getPaymentDetails(1);
    }

    @Test
    public void getPaymentDetailsForOrderTest() throws Exception {
        Mockito.when(paymentInnerService.getPaymentDetailsForOrder(Mockito.anyLong())).thenReturn(getPaymentDOList());
        List<PaymentDetailResponse> pdrs = paymentService.getPaymentDetailsForOrder(1);
        assertEquals(pdrs.size(), 2);
    }

    @Test(expected = ZooplusCommonException.class)
    public void getPaymentDetailsForOrderExceptionTest() throws Exception {
        Mockito.when(paymentInnerService.getPaymentDetailsForOrder(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        List<PaymentDetailResponse> pdrs = paymentService.getPaymentDetailsForOrder(1);
        assertEquals(pdrs.size(), 2);
    }
    public PaymentDO getPaymentDO(){
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setPaymentStatus(PaymentStatusEnum.FULLY_PAID.name());
        paymentDO.setOrderBal(1);
        paymentDO.setCurrency(Currency.EUR.name());
        paymentDO.setOrderId(1);
        paymentDO.setRunningBal(1);
        paymentDO.setCustomerId(1);
        return paymentDO;
    }
    public List<PaymentDO> getPaymentDOList(){
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setPaymentStatus(PaymentStatusEnum.FULLY_PAID.name());
        paymentDO.setOrderBal(1);
        paymentDO.setCurrency(Currency.EUR.name());
        paymentDO.setOrderId(1);
        paymentDO.setRunningBal(1);
        paymentDO.setCustomerId(1);
        List<PaymentDO> lst = new ArrayList<>();
        lst.add(paymentDO);
        lst.add(paymentDO);
        return lst;
    }
}

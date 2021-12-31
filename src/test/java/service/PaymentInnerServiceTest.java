package service;

import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.persistence.PaymentRepository;
import com.zooplus.service.PaymentInnerService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class PaymentInnerServiceTest extends AbstractTest{
    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentInnerService paymentInnerService;

    @Test
    public void getPaymentDetailsTest() throws ZooplusCommonException {
        Mockito.when(paymentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getPaymentDO()));
        PaymentDO pdr = paymentInnerService.getPaymentDetails(1);
        assertEquals(pdr.getPaymentStatus(), PaymentStatusEnum.FULLY_PAID.name());
    }
    @Test
    public void getPaymentDetailsForOrderTest(){
        Mockito.when(paymentRepository.getPaymentDetailsForOrder(Mockito.anyLong())).thenReturn(getPaymentDOList());
        List<PaymentDO> pdr = paymentInnerService.getPaymentDetailsForOrder(1);
        assertEquals(2, pdr.size());
    }

    @Test
    public void makePaymentTest() {
        Mockito.when(paymentRepository.save(Mockito.any(PaymentDO.class))).thenReturn(getPaymentDO());
        PaymentDO payment = paymentInnerService.makePayment(new PaymentDO());
        assertEquals(payment.getPaymentStatus(), PaymentStatusEnum.FULLY_PAID.name());
    }

    @Test(expected = ZooplusCommonException.class)
    public void getPaymentDetailsExceptionTest() {
        Mockito.when(paymentRepository.findById(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        PaymentDO pdr = paymentInnerService.getPaymentDetails(1);
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

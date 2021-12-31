package convertor;

import com.zooplus.convertor.PaymentConvertor;
import com.zooplus.enums.PaymentStatusEnum;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.PaymentDO;
import com.zooplus.model.request.PaymentDetailRequest;
import com.zooplus.model.response.PaymentDetailResponse;
import com.zooplus.util.DateUtil;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PaymentConvertorTest {

    @Test
    public void convertPaymentToPaymentDOTest(){
        PaymentDetailRequest payment = new PaymentDetailRequest();
        payment.setAmount(1);
        payment.setPayToolType("UPI");
        payment.setCurrency(Currency.EUR);
        payment.setOrderId(1);
        payment.setPaymentStatus(PaymentStatusEnum.INIT);
        payment.setOrderBal(-9);
        payment.setRunningBal(-9);
        payment.setCustomerId(1);
        PaymentDO paymentDO = PaymentConvertor.convertPaymentToPaymentDO(payment);
        assertEquals(1, paymentDO.getAmount());
        assertEquals("UPI", paymentDO.getPayToolType());
        assertEquals(Currency.EUR.name(), paymentDO.getCurrency());
        assertEquals(1, paymentDO.getOrderId());
        assertEquals("INIT", paymentDO.getPaymentStatus());
        assertEquals(-9, paymentDO.getOrderBal());
        assertEquals(-9, paymentDO.getRunningBal());
        assertEquals(1, paymentDO.getCustomerId());

    }

    @Test
    public  void convertPaymentDOToPaymentTest(){
        PaymentDO payment = new PaymentDO();
        payment.setAmount(1);
        payment.setPayToolType("UPI");
        payment.setCurrency(Currency.EUR.name());
        payment.setOrderId(1);
        payment.setPaymentStatus(PaymentStatusEnum.INIT.name());
        payment.setOrderBal(-9);
        PaymentDetailResponse paymentDetailResponse = PaymentConvertor.convertPaymentDOToPayment(payment);
        assertEquals(1, paymentDetailResponse.getAmount());
        assertEquals("UPI", paymentDetailResponse.getPayToolType());
        assertEquals(Currency.EUR, paymentDetailResponse.getCurrency());
        assertEquals(1, paymentDetailResponse.getOrderId());
        assertEquals("INIT", paymentDetailResponse.getPaymentStatus().name());
        assertEquals(-9, paymentDetailResponse.getOrderBal());
    }

    @Test
    public void convertPaymentDOListToPaymentListTest(){
        List<PaymentDO> paymentDOList = new ArrayList<>();
        PaymentDO payment = new PaymentDO();
        payment.setAmount(1);
        payment.setPayToolType("UPI");
        payment.setCurrency(Currency.EUR.name());
        payment.setOrderId(1);
        payment.setPaymentStatus(PaymentStatusEnum.INIT.name());
        payment.setOrderBal(-9);
        paymentDOList.add(payment);
        paymentDOList.add(payment);
        List<PaymentDetailResponse> lst = PaymentConvertor.convertPaymentDOListToPaymentList(paymentDOList);
        assertEquals(2, lst.size());
    }
}

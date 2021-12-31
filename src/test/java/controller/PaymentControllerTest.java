package controller;

import com.zooplus.model.Currency;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.request.PaymentDetailRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class PaymentControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void makePaymentTest() throws Exception {
        String uri = "/makePayment";
        PaymentDetailRequest paymentDetailRequest = new PaymentDetailRequest();
        paymentDetailRequest.setOrderId(1);
        paymentDetailRequest.setAmount(10);
        paymentDetailRequest.setCurrency(Currency.EUR);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(mapToJson(paymentDetailRequest))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void getPaymentDetailsTest() throws Exception {
        String uri = "/getPaymentDetails/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void getPaymentDetailsForOrderTest() throws Exception {
        String uri = "/getPaymentDetails/order/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}

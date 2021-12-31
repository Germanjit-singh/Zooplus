package convertor;

import com.zooplus.convertor.CustomerConvertor;
import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.response.CustomerDetailResponse;
import com.zooplus.util.DateUtil;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class CustomerConverterTest {
    @Test
    public void convertCustomerToCustomerDOTest(){
        CustomerDetailRequest customerDetailRequest= new CustomerDetailRequest();
        customerDetailRequest.setCustomerName("Customer");
        CustomerDO customerDO = CustomerConvertor.convertCustomerToCustomerDO(customerDetailRequest);
        assertEquals(0, customerDO.getCustomerBalance());
        assertEquals("Customer", customerDO.getCustomerName());
        assertEquals(Currency.EUR.name(), customerDO.getCustomerBalCurr());
    }

    @Test
    public void convertCustomerDoToCustomerTest(){
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerName("Customer");
        customerDO.setCustomerId(1);
        customerDO.setCustomerBalance(1);
        customerDO.setCustomerBalCurr(Currency.EUR.name());

        CustomerDetailResponse customer = CustomerConvertor.convertCustomerDoToCustomer(customerDO);
        assertEquals(1, customer.getCustomerBal());
        assertEquals("Customer", customer.getCustomerName());
        assertEquals(Currency.EUR, customer.getCustomerBalCurr());
        assertEquals(1, customer.getCustomerId());
    }
}

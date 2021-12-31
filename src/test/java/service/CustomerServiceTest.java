package service;

import com.zooplus.convertor.CustomerConvertor;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.request.CustomerDetailRequest;
import com.zooplus.model.response.CustomerDetailResponse;
import com.zooplus.service.CustomerInnerService;
import com.zooplus.service.CustomerService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CustomerServiceTest extends AbstractTest{
    @Mock
    CustomerInnerService customerInnerService;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void createCustomerTest(){
        Mockito.when(customerInnerService.createOrUpdateCustomer(Mockito.any(CustomerDO.class))).thenReturn(getCustomerDO());
        CustomerDetailResponse customerDO = customerService.createCustomer(new CustomerDetailRequest());
        assertEquals(customerDO.getCustomerName(), "Customer");
     }

    @Test
    public void getCustomerDetailsTest() throws ZooplusCommonException {
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenReturn(getCustomerDO());
        CustomerDetailResponse customerDO = customerService.getCustomerDetails(1);
        assertEquals(customerDO.getCustomerName(), "Customer");
    }

    @Test
    public void getCustomerBalanceTest() throws ZooplusCommonException {
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenReturn(getCustomerDO());
        String customerBal = customerService.getCustomerBalance(1);
        assertEquals( "1 EUR",customerBal);
    }

    @Test(expected = ZooplusCommonException.class)
    public void getCustomerDetailsExceptionTest() throws ZooplusCommonException {
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        CustomerDetailResponse customerDO = customerService.getCustomerDetails(1);
      }

    @Test(expected = ZooplusCommonException.class)
    public void getCustomerBalanceExceptionTest()  {
        Mockito.when(customerInnerService.getCustomerDetails(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        String customerBal = customerService.getCustomerBalance(1);
    }

    private CustomerDO getCustomerDO(){
        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId(1);
        customerDO.setCustomerBalance(1);
        customerDO.setCustomerBalCurr(Currency.EUR.name());
        customerDO.setCustomerName("Customer");
        return customerDO;
    }
}

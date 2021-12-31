package service;

import com.zooplus.enums.OrderStatusEnum;
import com.zooplus.exception.ZooplusCommonException;
import com.zooplus.model.Currency;
import com.zooplus.model.dataobject.CustomerDO;
import com.zooplus.model.dataobject.OrderDO;
import com.zooplus.persistence.CustomerRepository;
import com.zooplus.service.CustomerInnerService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CustomerInnerServiceTest extends AbstractTest{
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerInnerService customerInnerService;

    @Test
    public void createOrUpdateCustomerTest() {
        Mockito.when(customerRepository.save(Mockito.any(CustomerDO.class))).thenReturn(getCustomerDO());
        CustomerDO customerDO = customerInnerService.createOrUpdateCustomer(new CustomerDO());
        assertEquals("Customer", customerDO.getCustomerName());
    }

    @Test
    public void getCustomerDetailsTest() throws ZooplusCommonException {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCustomerDO()));
        CustomerDO customerDO = customerInnerService.getCustomerDetails(1);
        assertEquals("Customer", customerDO.getCustomerName());
    }

    @Test
    public void lockCustomerDetails() throws ZooplusCommonException {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getCustomerDO()));
        CustomerDO customerDO = customerInnerService.getCustomerDetails(1);
        assertEquals("Customer", customerDO.getCustomerName());
    }

    @Test(expected = ZooplusCommonException.class)
    public void getCustomerDetailsExceptionTest()  {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        CustomerDO customerDO = customerInnerService.getCustomerDetails(1);
     }

    @Test(expected = ZooplusCommonException.class)
    public void lockCustomerDetailsExceptionTest()  {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenThrow(new ZooplusCommonException(""));
        CustomerDO customerDO = customerInnerService.getCustomerDetails(1);
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

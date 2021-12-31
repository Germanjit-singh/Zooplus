package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AbstractTest {
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

   @Test
    public void test(){
       assertEquals(true,true);
   }
}

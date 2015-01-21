
package cz.muni.fi.stavebnistroje.test.serviceImpl;

import cz.muni.fi.stavebnistroje.dao.CustomerDao;
import cz.muni.fi.stavebnistroje.dto.CustomerDto;
import cz.muni.fi.stavebnistroje.entity.Customer;
import cz.muni.fi.stavebnistroje.service.CustomerService;
import cz.muni.fi.stavebnistroje.serviceImpl.CustomerServiceImpl;
import cz.muni.fi.stavebnistroje.util.LegalStatus;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Test for CustomerServiceImp
 * @author Milos Petrovic
 */
public class CustomerServiceTest extends AbstractIntegrationTest {

    private Customer c1;
    private Customer c2;
    private Customer c3;


    private CustomerService customerService;

    @Mock
    private CustomerDao customerDao;

    @Autowired
    private DozerBeanMapper mapper;

    public CustomerServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * method for setting new customer
     * @param firstName first Name  of the customer
     * @param lastname last name  of the customer
     * @param address address  of the customer
     * @param legalStatus legal Status of the customer
     * @return
     */
    private Customer setNewCustomer(String firstName, String lastname, String address, LegalStatus legalStatus) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSecondName(lastname);
        customer.setAddress(address);
        customer.setLegalStatus(legalStatus);

        return customer;
    }

    /**
     * SetUp method is used for preparation
     * @throws ParseException
     */
    @Before
    public void setUp() throws ParseException {

        customerService = new CustomerServiceImpl();
        ReflectionTestUtils.setField(customerService, "customerDao", customerDao);
        ReflectionTestUtils.setField(customerService, "dozerBeanMapper", mapper);

        c1 = this.setNewCustomer("Petar", "Petrovic", "Tehnicka 9", LegalStatus.LEGAL);
        c2 = this.setNewCustomer("Jan", "Novak", "Ceska 9", LegalStatus.LEGAL);
        c3 = this.setNewCustomer("Nick", "Foster", "Uvoz 9", LegalStatus.LEGAL);
    }

    @After
    public void tearDown() {
        customerDao = null;
        customerService = null;
    }

    /**
     * Test of createCustomer method from CustomerServiceImpl.
     */
    @Test
    public void testCreateCustomer() {
        try {
            customerService.createCustomer(null);
            fail("Didn't throw exception when customer to be created is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace(System.out);
            fail("Customer to be created is null.");
        }

        Customer customer = this.setNewCustomer("Petr", "Novak", "Vinarska 5", LegalStatus.LEGAL);
        CustomerDto custDto = mapper.map(customer, CustomerDto.class);
        customerService.createCustomer(custDto);
        verify(customerDao).persist(customer);

    }

    /**
     * Test of getCustomer() method from CustomerServiceImpl.
     */
    @Test
    public void testGetCustomer() {
        Customer customer = new Customer();
        customer.setId(100L);
        customer.setFirstName("Milos");
        customer.setSecondName("Petrovic");
        customer.setAddress("Tehnicka 9");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(100L);
        customerDto.setFirstName("Milos");
        customerDto.setSecondName("Petrovic");
        customerDto.setAddress("Tehnicka 9");

        customerService.createCustomer(customerDto);

        Long id = customer.getId();
        Mockito.when(customerDao.findById(id)).thenReturn(customer);
        CustomerDto res = customerService.getCustomer(id);
        Mockito.verify(customerDao, Mockito.times(1)).findById(id);
        assertEquals(customerDto.getId(), res.getId());

    }
     @Test
    public void testSearchCustomer() {
        System.out.println("findAll of customer service impl test");
        List<Customer> customers = new ArrayList<>(Arrays.asList(new Customer[]{c1}));
        List<CustomerDto> customersDto = new ArrayList<>(Arrays.asList(new CustomerDto[]{
                    mapper.map(c1, CustomerDto.class),

                }));
        when(customerDao.findByName(c1.getFirstName())).thenReturn(customers);
        List<CustomerDto> allCustomers = (List<CustomerDto>) customerService.searchCustomer(c1.getFirstName());
        assertEquals(allCustomers, customersDto);

    }

    /**
     * Test for updateCustomer() method from CustomerServiceImpl.
     */
    @Test
    public void testUpdateCustomer() {
        try {
            customerService.updateCustomer(null);
            fail("Didn't throw exception when customer to be updated is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Customer to be updated is null.");
        }

        Customer customer = this.setNewCustomer("Petr", "Novak", "Vinarska 5", LegalStatus.LEGAL);
        customer.setId(7L);
        CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        customerService.updateCustomer(customerDto);
        verify(customerDao).update(customer);
    }

    /**
     * Test for removeCustomer() method from CustomerServiceImpl.
     */
    @Test
    public void testRemove() {
        try {
            customerService.removeCustomer(null);
            fail("Didn't throw exception when customer to be removed is null.");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("ustomer to be removed is null.");
        }


        Customer customer = this.setNewCustomer("Petr", "Novak", "Vinarska 5", LegalStatus.LEGAL);
        customer.setId(7L);
        CustomerDto remove = mapper.map(customer, CustomerDto.class);
        customerService.removeCustomer(remove);
        verify(customerDao).remove(customer);
    }


    /**
     * Test for findAllCustomer() method from CustomerServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll of customer service impl test");
        List<Customer> customers = new ArrayList<>(Arrays.asList(new Customer[]{c1, c2, c3}));
        List<CustomerDto> customersDto = new ArrayList<>(Arrays.asList(new CustomerDto[]{
                    mapper.map(c1, CustomerDto.class),
                    mapper.map(c2, CustomerDto.class),
                    mapper.map(c3, CustomerDto.class)
                }));
        when(customerDao.findAll()).thenReturn(customers);
        List<CustomerDto> allCustomers = (List<CustomerDto>) customerService.findAllCustomer();
        assertEquals(allCustomers, customersDto);
    }
}

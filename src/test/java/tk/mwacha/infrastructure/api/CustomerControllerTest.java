package tk.mwacha.infrastructure.api;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tk.mwacha.helper.MockCustomerHelper;
import tk.mwacha.helper.ParseJsonHelper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

    private static final String CONTEXT_ROOT = "/api/v1/customers";
    @Autowired
    private MockMvc mockMvc;


    @Test
    @SneakyThrows
    void should_create_a_customer() {
        var json = ParseJsonHelper.toJson(MockCustomerHelper.buildCustomer());
        mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    @SneakyThrows
    @Disabled
    void should_not_create_a_customer_invalid_address() {
        final var customer = MockCustomerHelper.buildCustomer();
        customer.changeAddress(null);

        final var json = ParseJsonHelper.toJson(customer);
        mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                // .andExpect(NullPointerException.class)
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NullPointerException));
        // .andExpect(result -> Assertions.assertEquals("internal error", result.getResolvedException().getMessage()));

    }

    @Test
    @SneakyThrows
  //  @Disabled
    void should_list_all_customer() {
        final var customers = MockCustomerHelper.buildCustomers();

        var customer1 = ParseJsonHelper.toJson(customers.get(0));
        final var responseCustomer1 = mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(customer1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        var customer2 = ParseJsonHelper.toJson(customers.get(1));
        final var responseCustomer2 = mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(customer1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


//        final var result = mockMvc
//                .perform(get(CONTEXT_ROOT))
//               // .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//               // .andExpect(content().json(json, true))
//                .andReturn();
//       Assertions.assertTrue(result.getResponse().);

        final var listResponseXML =  mockMvc
                .perform(get(CONTEXT_ROOT).accept("Accept", "application/xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                // .andExpect(content().json(json, true))
                .andReturn();

    }
}
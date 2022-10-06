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
import tk.mwacha.helper.MockProductHelper;
import tk.mwacha.helper.ParseJsonHelper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    private static final String CONTEXT_ROOT = "/api/v1/products";
    @Autowired
    private MockMvc mockMvc;


    @Test
    @SneakyThrows
    void should_create_a_product() {
        var json = ParseJsonHelper.toJson(MockProductHelper.buildProduct());
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
    void should_not_create_a_product_invalid_address() {
        final var product = MockProductHelper.buildProduct();
        product.changePrice(null);

        final var json = ParseJsonHelper.toJson(product);
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
    void should_list_all_product() {
        final var products = MockProductHelper.buildProducts();

        var product1 = ParseJsonHelper.toJson(products.get(0));
        final var responseProduct1 = mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(product1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        var product2 = ParseJsonHelper.toJson(products.get(1));
        final var responseProduct2 = mockMvc
                .perform(
                        post(CONTEXT_ROOT)
                                .content(product1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andReturn();


        final var result = mockMvc
                .perform(get(CONTEXT_ROOT))
               // .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               // .andExpect(content().json(json, true))
                .andReturn();
//       Assertions.assertTrue(result.getResponse().);

    }
}

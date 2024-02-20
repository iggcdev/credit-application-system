package iggcdev.creditapplicationsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import iggcdev.creditapplicationsystem.dto.requests.CustomerDto
import iggcdev.creditapplicationsystem.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerResourceTest {
    @Autowired private lateinit var customerRepository: CustomerRepository
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object{
        const val URL: String = "/api/customers"
    }

    @BeforeEach fun setup() = customerRepository.deleteAll()
    @AfterEach fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`(){
        //given
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ivo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Gabriel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678900"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("igabrielc@email.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("123456789"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua do Gabriel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())
    }

    private fun buildCustomerDto(
        firstName: String = "Ivo",
        lastName: String = "Gabriel",
        cpf: String = "12345678900",
        email: String = "igabrielc@email.com",
        password: String = "112233",
        zipCode: String = "123456789",
        street: String = "Rua do Gabriel",
        income: BigDecimal = BigDecimal.valueOf(5000.0)
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        zipCode = zipCode,
        street = street,
        income = income,
    )
}
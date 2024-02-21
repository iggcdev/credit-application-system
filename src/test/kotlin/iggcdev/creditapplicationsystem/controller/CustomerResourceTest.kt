package iggcdev.creditapplicationsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import iggcdev.creditapplicationsystem.dto.requests.CustomerDto
import iggcdev.creditapplicationsystem.dto.requests.CustomerUpdateDto
import iggcdev.creditapplicationsystem.entity.Customer
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
import java.util.Random

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

    @Test
    fun `should not save customer with same CPF and return 409 status`(){
        //given
        customerRepository.save(buildCustomerDto().toEntity())
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                .value("Conflict! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                .value("class org.springframework.dao.DataIntegrityViolationException"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())


    }

    @Test
    fun `should not save customer with empty field and return status 400`(){
        //given
        val customerDto: CustomerDto = buildCustomerDto(firstName = "")
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                .value("class org.springframework.web.bind.MethodArgumentNotValidException"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").value("Field First Name can not be empty."))
            .andDo(MockMvcResultHandlers.print())
    }
    @Test
    fun `should find customer by id and return status 200`(){
        //given
        val customer: Customer= customerRepository.save(buildCustomerDto().toEntity())
        //customer.id = 2L
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("$URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.id.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ivo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Gabriel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678900"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("igabrielc@email.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("123456789"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua do Gabriel"))
            .andDo(MockMvcResultHandlers.print())

    }
    @Test
    fun `should not find customer with invalid id and return 400 status`(){
        //given
        val invalidId: Long = 2L
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("$URL/$invalidId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                .value("class iggcdev.creditapplicationsystem.exception.BusinessException"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").value("Id $invalidId not found."))
            .andDo(MockMvcResultHandlers.print())

    }
    @Test
    fun `should delete customer by Id and return 204 status`(){
        //GIVEN
        val customer: Customer = customerRepository.save( buildCustomerDto().toEntity())
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("$URL/${customer.id}")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())

    }
    @Test
    fun `should not delete customer by Id and return 400 status`(){
        //GIVEN
        val invalidId: Long = Random().nextLong()
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("$URL/$invalidId")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                .value("class iggcdev.creditapplicationsystem.exception.BusinessException"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").value("Id $invalidId not found."))
            .andDo(MockMvcResultHandlers.print())
    }
    @Test
    fun `should update a customer and return 200 status`(){
        //given
        val customer: Customer = customerRepository.save(buildCustomerDto().toEntity())
        val customerUpdateDto: CustomerUpdateDto = buildCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.patch("$URL?customerId=${customer.id}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Gabriel"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Correia"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678900"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("igabrielc@email.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value("12346-777"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua Correia"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.id.toString()))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should not update an invalid customer and return 400 status`() {
        //given
        val invalidId: Long = Random().nextLong()
        val customerUpdateDto: CustomerUpdateDto = buildCustomerUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerUpdateDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?customerId=$invalidId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                .value("class iggcdev.creditapplicationsystem.exception.BusinessException"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").value("Id $invalidId not found."))
            .andDo(MockMvcResultHandlers.print())
    }
    private fun buildCustomerUpdateDto(
        firstName: String = "Gabriel",
        lastName: String = "Correia",
        zipCode: String = "12346-777",
        street: String = "Rua Correia",
        income: BigDecimal = BigDecimal.valueOf(50000.0)
    ) = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        zipCode = zipCode,
        street = street,
        income = income
    )
    private fun buildCustomerDto(
        firstName: String = "Ivo",
        lastName: String = "Gabriel",
        cpf: String = "12345678900",
        email: String = "igabrielc@email.com",
        password: String = "112233",
        zipCode: String = "123456789",
        street: String = "Rua do Gabriel",
        income: BigDecimal = BigDecimal.valueOf(5000.0),
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
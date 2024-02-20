package iggcdev.creditapplicationsystem.dto.requests

import iggcdev.creditapplicationsystem.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Field First Name can not be empty.")val firstName: String,
    @field:NotEmpty(message = "Field Last Name can not be empty.")val lastName: String,

    @field:NotNull(message = "Field First Name can not be empty.")
    @Positive(message = "Field Income mus be a positive value.") val income: BigDecimal,

    @field:NotEmpty(message = "Field First Name can not be empty.") val zipCode: String,
    @field:NotEmpty(message = "Field First Name can not be empty.")val street: String
) {
    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return  customer
    }

}

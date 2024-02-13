package iggcdev.creditapplicationsystem.dto

import iggcdev.creditapplicationsystem.entity.Address
import iggcdev.creditapplicationsystem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Field First Name can not be empty.") val firstName: String,
    @field:NotEmpty(message = "Field Last Name can not be empty.") val lastName: String,
    @field:NotEmpty(message = "Field CPF can not be empty.")
    @CPF(message = "Cpf Inválido.")val cpf: String,
    @field:NotNull(message = "Renda não pode ser null")
    @Positive(message = "Invalid income.")val income: BigDecimal,
    @field:NotEmpty(message = "Field Email can not be empty.")
    @Email(message = "Email inválido") val email: String,
    @field:NotEmpty(message = "Field Password can not be empty.") val password: String,
    @field:NotEmpty(message = "Field Zip Code can not be empty.") val zipCode: String,
    @field:NotEmpty(message = "Field Street can not be empty.") val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}

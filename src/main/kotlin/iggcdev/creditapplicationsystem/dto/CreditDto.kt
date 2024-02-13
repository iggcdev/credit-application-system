package iggcdev.creditapplicationsystem.dto

import iggcdev.creditapplicationsystem.entity.Credit
import iggcdev.creditapplicationsystem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Field Credit Value can not be empty.")
    @Positive(message = "Credit value must be a positve value.")val creditValue: BigDecimal,
    @field:NotNull(message = "Field Date can not be empty.")
    @Future(message = "Invalid Date.") val dayFirstOfInstallment: LocalDate,
    @field:NotNull(message = "Field First Name can not be empty.")
    @Positive(message = "Invalid Number of Installments.")val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid Id.")val customerId: Long

) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}

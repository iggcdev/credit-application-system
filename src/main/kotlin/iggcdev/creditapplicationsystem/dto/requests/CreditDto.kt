package iggcdev.creditapplicationsystem.dto.requests

import iggcdev.creditapplicationsystem.entity.Credit
import iggcdev.creditapplicationsystem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Field Credit Value can not be empty.")
    @Positive(message = "Credit value must be positive.")val creditValue: BigDecimal,

    @field:NotNull(message = "Field Day of First Installment can not be empty.")
    @Future(message = "Invalid Date." ) val dayFirstInstallment: LocalDate,

    @field:NotNull(message = "Field Number of Installments can not be empty.")
    @Positive(message = "Invalid Number of Installments.") @Min(value = 1)
    @Max(value = 48) val numberOfInstallments: Int,

    @field:NotNull(message = "Invalid Id.") val customerId: Long

) {
    init {
        val currentDate = LocalDate.now()
        val currentDatePlus3Months = currentDate.plusMonths(3)
        require(dayFirstInstallment.isAfter(currentDatePlus3Months)) {
            "Day of First Installment must be at least 3 months after the current date."
        }
    }
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}

package iggcdev.creditapplicationsystem.dto

import iggcdev.creditapplicationsystem.entity.Credit
import iggcdev.creditapplicationsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customId: Long

) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallments,
        customer = Customer(id = this.customId)
    )
}

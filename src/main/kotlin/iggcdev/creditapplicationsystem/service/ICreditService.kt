package iggcdev.creditapplicationsystem.service

import iggcdev.creditapplicationsystem.entity.Credit
import iggcdev.creditapplicationsystem.entity.Customer
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findbyCreditCode(customerID: Long,creditCode: UUID): Credit
}
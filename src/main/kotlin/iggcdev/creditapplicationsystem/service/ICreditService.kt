package iggcdev.creditapplicationsystem.service

import iggcdev.creditapplicationsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(customerID: Long, creditCode: UUID): Credit
}
package iggcdev.creditapplicationsystem.service

import iggcdev.creditapplicationsystem.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}
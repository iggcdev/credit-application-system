package iggcdev.creditapplicationsystem.service.impl

import iggcdev.creditapplicationsystem.entity.Customer
import iggcdev.creditapplicationsystem.exception.BusinessException
import iggcdev.creditapplicationsystem.repository.CustomerRepository
import iggcdev.creditapplicationsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    override fun findById(id: Long): Customer = this.customerRepository.findById(id)
        .orElseThrow {throw BusinessException("Id $id not found.")
    }

    override fun delete(id: Long){
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}
package iggcdev.creditapplicationsystem.service.impl

import iggcdev.creditapplicationsystem.CreditRepository
import iggcdev.creditapplicationsystem.entity.Credit
import iggcdev.creditapplicationsystem.exception.BusinessException
import iggcdev.creditapplicationsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*


@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {


    override fun save(credit: Credit): Credit {
        this.validDayFirstInstallment(credit.dayFirstInstallment)
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessException("credit code $creditCode not found."))
        return if(credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
    }
    private fun validDayFirstInstallment(dayFirstInstalment: LocalDate): Boolean{
        val validDayFirstInstallment: LocalDate = LocalDate.now().plusMonths(3)
        return if (dayFirstInstalment.isAfter(validDayFirstInstallment)) true
        else throw BusinessException("The date of the first installment must be 3 months from now.")
    }
}
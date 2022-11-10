package com.example.kotlinspringmockmvcwithdatabaserider.infra

import com.example.kotlinspringmockmvcwithdatabaserider.domain.CustomerRepository
import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.NotFoundException
import org.springframework.stereotype.Repository

/**
 * CustomerRepository の実装クラス
 * 実際のクエリは、SpringDataCustomerRepository が操作をおこなう
 *
 * @property springDataCustomerRepository
 */
@Repository
class CustomerRepositoryImpl(val springDataCustomerRepository: SpringDataCustomerRepository) : CustomerRepository {
    override fun getCustomer(id: Int): Customer {
        return springDataCustomerRepository.findById(id).orElse(null) ?: throw NotFoundException("Customer が見つかりませんでした")
    }

    override fun registerCustomer(firstName: String, lastName: String): Customer {
        val customer = Customer(id = null, firstName = firstName, lastName = lastName)
        return springDataCustomerRepository.save(customer)
    }
}

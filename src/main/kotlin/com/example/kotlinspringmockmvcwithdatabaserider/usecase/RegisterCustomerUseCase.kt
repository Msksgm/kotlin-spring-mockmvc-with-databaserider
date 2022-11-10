package com.example.kotlinspringmockmvcwithdatabaserider.usecase

import com.example.kotlinspringmockmvcwithdatabaserider.domain.CustomerRepository
import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer
import org.springframework.stereotype.Service

/**
 * Customer 登録ユースケース
 *
 */
interface RegisterCustomerUseCase {
    /**
     * Customer 登録メソッド
     *
     * @param firstName
     * @param lastName
     * @return
     */
    fun execute(firstName: String, lastName: String): Customer = throw NotImplementedError()
}

@Service
/**
 * Customer 登録ユースケースの実クラス
 *
 * @property customerRepository
 */
class RegisterCustomerUseCaseImpl(val customerRepository: CustomerRepository) : RegisterCustomerUseCase {
    override fun execute(firstName: String, lastName: String): Customer {
        return customerRepository.registerCustomer(firstName, lastName)
    }
}

package com.example.kotlinspringmockmvcwithdatabaserider.usecase

import com.example.kotlinspringmockmvcwithdatabaserider.domain.CustomerRepository
import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer
import org.springframework.stereotype.Service

/**
 * Customer 単一取得ユースケース
 *
 */
interface GetCustomerUseCase {
    /**
     * Customer 単一取得メソッド
     *
     * @param id
     * @return
     */
    fun execute(id: Int): Customer = throw NotImplementedError()
}

/**
 * Customer 単一取得ユースケースの実クラス
 *
 * @property customerRepository
 */
@Service
class GetCustomerUseCaseImpl(val customerRepository: CustomerRepository) : GetCustomerUseCase {
    override fun execute(id: Int): Customer {
        return customerRepository.getCustomer(id)
    }
}

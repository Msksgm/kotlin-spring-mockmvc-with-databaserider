package com.example.kotlinspringmockmvcwithdatabaserider.usecase

import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer
import com.example.kotlinspringmockmvcwithdatabaserider.infra.CustomerRepositoryImpl
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
 * @property customerRepositoryImpl
 */
@Service
class GetCustomerUseCaseImpl(val customerRepositoryImpl: CustomerRepositoryImpl) : GetCustomerUseCase {
    override fun execute(id: Int): Customer {
        return customerRepositoryImpl.getCustomer(id)!!
    }
}

package com.example.kotlinspringmockmvcwithdatabaserider.presentation

import com.example.kotlinspringmockmvcwithdatabaserider.usecase.GetCustomerUseCase
import com.example.kotlinspringmockmvcwithdatabaserider.usecase.RegisterCustomerUseCase
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.CustomerApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.NotFoundException
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Customer
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.GenericErrorModel
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.GenericErrorModelErrors
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.NewCustomerRequest
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.SingleCustomerReponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

/**
 * Customer API のルーティングコントローラー
 *
 * @property getCustomerUseCase Customer 単一取得ユースケース
 * @property registerCustomerUseCase Customer 登録ユースケース
 */
@RestController
class CustomerController(
    val getCustomerUseCase: GetCustomerUseCase,
    val registerCustomerUseCase: RegisterCustomerUseCase
) : CustomerApi {
    override fun getCustomerById(id: Int): ResponseEntity<SingleCustomerReponse> {
        try {
            val customer = getCustomerUseCase.execute(id)
            return ResponseEntity(
                SingleCustomerReponse(
                    Customer(
                        id = customer.id!!,
                        firstName = customer.firstName,
                        lastName = customer.lastName
                    )
                ),
                HttpStatus.OK
            )
        } catch (e: Exception) {
            throw GetCustomerByIdErrorException(e)
        }
    }

    /**
     * getCustomerById の Exception のラッパー
     *
     * @property exception
     */
    data class GetCustomerByIdErrorException(val exception: Exception) : Exception()

    /**
     * getCustomerById で Exception が発生したときのエラーレスポンスのファクトリメソッド
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = [GetCustomerByIdErrorException::class])
    fun onGetCustomerByIdErrorException(e: GetCustomerByIdErrorException): ResponseEntity<GenericErrorModel> {
        return if (e.exception is NotFoundException) {
            ResponseEntity(
                GenericErrorModel(GenericErrorModelErrors(body = listOf("カスタマーが見つかりませんでした"))),
                HttpStatus.NOT_FOUND
            )
        } else {
            ResponseEntity(
                GenericErrorModel(GenericErrorModelErrors(body = listOf("原因不明のエラーが発生しました"))),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    override fun registerCustomer(customer: NewCustomerRequest): ResponseEntity<SingleCustomerReponse> {
        val registeredCustomer =
            registerCustomerUseCase.execute(customer.customer.firstName, customer.customer.lastName)
        return ResponseEntity(
            SingleCustomerReponse(
                customer = Customer(
                    id = registeredCustomer.id!!,
                    firstName = registeredCustomer.firstName,
                    lastName = registeredCustomer.lastName
                )
            ),
            HttpStatus.OK
        )
    }
}

package com.deliysuf.kripto.service

import com.deliysuf.kripto.model.model
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface cyripthoApı {
    // b18f7737e0de9e169f7443aa9d2f66c58a4b15a2
    //https://api.nomics.com/
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<model>>
}
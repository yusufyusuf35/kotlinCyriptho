package com.deliysuf.kripto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deliysuf.kripto.R
import com.deliysuf.kripto.adapter.vievadapter
import com.deliysuf.kripto.model.model
import com.deliysuf.kripto.service.cyripthoApı
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),vievadapter.Listener {
    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter:vievadapter
    lateinit var compositeDisposable: CompositeDisposable
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cyriptoModel:List<model>?=null
    lateinit var Textler:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cyriptoModel= ArrayList()
        compositeDisposable= CompositeDisposable()
        LoadData()

        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(this)





    }
    fun LoadData(){
        val retrofit=Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.
        create()).addCallAdapterFactory(RxJava2CallAdapterFactory.
        create()).build()

      val service=  retrofit.create(cyripthoApı::class.java)
        compositeDisposable.add(service.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers
                .mainThread()).subscribe(this::handleResponce))





     /* val call = service.getData()
       call.enqueue(object:Callback<List<model>>{
           override fun onFailure(call: Call<List<model>>, t: Throwable) {
               t.printStackTrace()
           }

           override fun onResponse(call: Call<List<model>>, response: Response<List<model>>) {
              if(response.isSuccessful){
                  cyriptoModel= response.body() as ArrayList<model>?
                  for (crp:model in cyriptoModel!!){
                      Textler.text=crp.price.toString()
                      println(crp.currency)

                  }
              }
           }

       })*/
           
       }
    fun handleResponce(cyripthoList:List<model>){
     cyriptoModel=cyripthoList

        viewAdapter= vievadapter(cyriptoModel!!,this)
        recyclerView.adapter=viewAdapter




        }








    override fun onItemClick(crp: model) {
        Toast.makeText(this,"nomussus it",Toast.LENGTH_LONG).show()
    }


}


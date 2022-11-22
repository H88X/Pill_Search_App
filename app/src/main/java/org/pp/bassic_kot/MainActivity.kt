package org.pp.bassic_kot

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.Constraints
import androidx.core.content.FileProvider
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.constraintlayout.widget.Constraints.TAG
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


class MainActivity : AppCompatActivity() {

    object RetrofitSetting {
        val API_BASE_URL = "http://9711-34-143-175-85.ngrok.io"
        val httpClient = OkHttpClient.Builder()

        val baseBuilder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        fun <S> createBaseService(serviceClass: Class<S>?): S {
            val retrofit = baseBuilder.client(httpClient.build()).build()
            return retrofit.create(serviceClass)
        }
    }


    interface RetrofitPath {
        @Multipart
        @POST("http://9711-34-143-175-85.ngrok.io")
        fun profileSend(
            @Part imageFile : MultipartBody.Part
        ): Call<String>
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondIntent = Intent(this, MainActivity2::class.java)
        val movePeal = Intent(this, MainActivity4::class.java)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            startActivity(secondIntent)
        }

        val movepeal = findViewById<Button>(R.id.movepeal)
        movepeal.setOnClickListener {
            startActivity(movePeal)
        }

      //  val btn_camera = findViewById<Button>(R.id.MovePeal)
      //  btn_camera.setOnClickListener {
      //     takeCapture() //카메라 앱을 실행하여 사진 촬영
      //  }

        val btn = findViewById<Button>(R.id.load_image)
        btn.setOnClickListener {getProFileImage()}

    }


    fun getProFileImage(){
        Log.d(ContentValues.TAG,"사진변경 호출")
        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        chooserIntent.putExtra(Intent.EXTRA_INTENT, intent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE,"사용할 앱을 선택해주세요.")
        launcher.launch(chooserIntent)

    }

    fun absolutelyPath(path: Uri?, context : Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

    /////////////////////////////////////////////////
    var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imagePath = result.data!!.data

            val file = File(absolutelyPath(imagePath, this))
            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            Log.d(TAG,file.name)

            sendImage(body)

        }
    }


    fun sendImage(image : MultipartBody.Part) {
        val service = RetrofitSetting.createBaseService(RetrofitPath::class.java) //레트로핏 통신 설정
        val call = service.profileSend(image)!! //통신 API 패스 설정

        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response?.isSuccessful) {
                    Log.d("로그 ",""+response?.body().toString())
                    val respone  = response?.body().toString()
                    val pill = respone.substring(14)
                    println(pill)
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com/search?q=$pill")
                    )
                    startActivity(browserIntent)
                    Toast.makeText(applicationContext,"통신성공",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(applicationContext,"통신실패",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("로그 ",t.message.toString())
            }
        })
    }



}
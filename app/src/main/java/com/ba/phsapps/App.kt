package com.ba.phsapps

import android.app.Application
import com.ba.phsapps.network.HttpBaseConnect
import com.ba.phsapps.network.api.ServiceAPI
import com.ba.phsapps.repository.LoginImpl
import com.ba.phsapps.repository.LoginRepository
import com.ba.phsapps.repository.UserHistoryImpl
import com.ba.phsapps.repository.UserHistoryRepository
import com.ba.phsapps.view.history.UserHistoryViewModel
import com.ba.phsapps.view.login.LoginViewModels
import com.ba.phsapps.view.medical.ServiceCenterViewModel
import com.ba.phsapps.view.register.RegisterViewModels
import com.ba.phsapps.view.resetPassword.ResetPasswordViewModels
import com.ba.phsapps.view.userUnder.UserUnderViewModel
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
class App : Application() {

    override fun onCreate() {
        super.onCreate()


        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        initKoin()

    }


    fun initKoin() {

        startKoin {

            androidContext(this@App)

            val networkModule = module {


//                single {
//                    HttpBaseConnect().getApiService()
//                }


//                single {
//                    HttpBasicConnect().getApiService()
//                }
                single<ServiceAPI> {
                    HttpBaseConnect().build()
                }

            }


            val model = module {

                single<LoginRepository> {
                    LoginImpl(get())
                }
                single<UserHistoryRepository> {
                    UserHistoryImpl(get())
                }
                single {
                    LoginImpl(get())
                    UserHistoryImpl(get())

                }

                viewModel { UserHistoryViewModel(get())  }
                viewModel { ServiceCenterViewModel(get())  }
                viewModel { ResetPasswordViewModels(get())  }
                viewModel { RegisterViewModels(get())  }
                viewModel { UserUnderViewModel(get())  }

                viewModel { LoginViewModels(get())  }
            }
//            viewModel { NotificationViewModel(get()) }


            modules(networkModule, model)


        }


    }

}
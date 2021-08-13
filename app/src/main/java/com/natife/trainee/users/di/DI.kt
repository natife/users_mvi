package com.natife.trainee.users.di

import android.os.Bundle
import androidx.room.Room
import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.domain.features.userdetails.usecase.GetUserByIdUseCase
import com.natife.trainee.domain.features.userlist.usecase.LoadUsersUseCase
import com.natife.trainee.domain.repository.UserRepository
import com.natife.trainee.domain.repository.UserRepositoryImpl
import com.natife.trainee.users.data.database.UserDatabaseRepository
import com.natife.trainee.users.data.database.UsersDatabase
import com.natife.trainee.users.data.network.UserApiService
import com.natife.trainee.users.data.network.UserNetworkRepository
import com.natife.trainee.users.ui.MainViewModel
import com.natife.trainee.users.ui.Router
import com.natife.trainee.users.ui.RouterImpl
import com.natife.trainee.users.ui.details.UserDetailsFragment
import com.natife.trainee.users.ui.details.UserDetailsViewModel
import com.natife.trainee.users.ui.list.UserListViewModel
import com.natife.trainee.users.utils.AppDispatchersImpl
import com.natife.trainee.users.utils.BASE_URL
import com.natife.trainee.users.utils.DATABASE_NAME
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DATABASE = "database"
private const val NETWORK = "network"

private val dataModule = module {
    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
    factory<AppDispatchers> {
        AppDispatchersImpl()
    }
    single {
        Room.databaseBuilder(get(), UsersDatabase::class.java, DATABASE_NAME).build()
    }
    single<UserRepository>(named(DATABASE)) {
        UserDatabaseRepository(get<UsersDatabase>().userDao())
    }
    single<UserRepository>(named(NETWORK)) {
        UserNetworkRepository(get())
    }
    single<UserRepository> {
        UserRepositoryImpl(
            databaseRepository = get(named(DATABASE)),
            networkRepository = get(named(NETWORK))
        )
    }
}

private val domainModule = module {
    factory {
        LoadUsersUseCase(get(), get())
    }
    factory {
        GetUserByIdUseCase(get(), get())
    }
}

private val navigationModule = module {
    single<Router> {
        RouterImpl()
    }
}

private val viewModelModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        UserListViewModel(
            dispatchers = get(),
            useCase = setOf(
                get<LoadUsersUseCase>()
            ),
            router = get()
        )
    }
    viewModel { parameters ->
        val userId = parameters.get<Bundle>().getString(UserDetailsFragment.KEY_USER_ID, "")
        UserDetailsViewModel(
            dispatchers = get(),
            useCase = setOf(
                get<GetUserByIdUseCase>()
            ),
            userId = userId
        )
    }
}

val modules = listOf(dataModule, domainModule, navigationModule, viewModelModule)
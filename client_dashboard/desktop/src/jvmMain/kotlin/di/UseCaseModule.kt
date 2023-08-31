package di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.thechance.common.domain.usecase.*

val UseCaseModule = module {
    singleOf(::SearchTaxisByUserNameUseCase) { bind<ISearchTaxisByUserNameUseCase>() }
    singleOf(::SearchRestaurantsByNameUseCase) { bind<ISearchRestaurantsByNameUseCase>() }
    singleOf(::FilterRestaurantsUseCase) { bind<IFilterRestaurantsUseCase>() }
    singleOf(::SearchFilterRestaurantsUseCase) { bind<ISearchFilterRestaurantsUseCase>() }
    singleOf(::LoginUserUseCase) { bind<ILoginUserUseCase>() }
    singleOf(::ManageRestaurantUseCase) { bind<IManageRestaurantUseCase>() }
    singleOf(::ManageLocationUseCase) { bind<IManageLocationUseCase>() }
    singleOf(::FilterTaxisUseCase) { bind<IFilterTaxisUseCase>() }
    singleOf(::ThemeManagementUseCase) { bind<IThemeManagementUseCase>() }
    singleOf(::ManageTaxisUseCase) { bind<IManageTaxisUseCase>() }
    singleOf(::ManageUsersUseCase) { bind<IManageUsersUseCase>() }
    singleOf(::LogoutUserUseCase) { bind<ILogoutUserUseCase>() }
}
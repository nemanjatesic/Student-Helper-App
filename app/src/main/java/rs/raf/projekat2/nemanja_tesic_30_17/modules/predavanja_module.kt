package rs.raf.projekat2.nemanja_tesic_30_17.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.nemanja_tesic_30_17.data.datasources.PredavanjaDataSource
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.PredavanjeRepository
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.PredavanjeRepositoryImpl
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.PredavanjeViewModel

val predavanjaModule = module {

    viewModel { PredavanjeViewModel(get()) }

    single<PredavanjeRepository> { PredavanjeRepositoryImpl(get()) }

    single<PredavanjaDataSource> { create(get()) }
}
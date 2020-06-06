package rs.raf.projekat2.nemanja_tesic_30_17.modules

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import rs.raf.projekat2.nemanja_tesic_30_17.data.db.ProjectDatabase
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.BeleskaRepository
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.BeleskaRepositoryImpl
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.BeleskaViewModel

val beleskaModule = module {

    viewModel { BeleskaViewModel(get()) }

    single<BeleskaRepository> { BeleskaRepositoryImpl(get()) }

    single { get<ProjectDatabase>().getBeleskaDao() }

}
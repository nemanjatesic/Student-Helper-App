package rs.raf.projekat2.nemanja_tesic_30_17.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.nemanja_tesic_30_17.data.db.ProjectDatabase
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.KorisnikRepository
import rs.raf.projekat2.nemanja_tesic_30_17.data.repositories.KorisnikRepositoryImpl
import rs.raf.projekat2.nemanja_tesic_30_17.presentation.viewmodel.KorisnikViewModel

val korisnikModule = module {

    viewModel { KorisnikViewModel(get()) }

    single<KorisnikRepository> { KorisnikRepositoryImpl(get()) }

    single { get<ProjectDatabase>().getKorisnikDao() }
}
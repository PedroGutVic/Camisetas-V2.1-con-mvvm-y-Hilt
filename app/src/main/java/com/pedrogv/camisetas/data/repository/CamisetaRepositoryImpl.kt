package com.pedrogv.camisetas.data.repository

import com.pedrogv.camisetas.data.datasource.InMemoryCamisetaDataSource
import com.pedrogv.camisetas.domain.model.Camiseta
import com.pedrogv.camisetas.domain.repository.CamisetaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CamisetaRepositoryImpl @Inject constructor(
    private val dataSource: InMemoryCamisetaDataSource
) : CamisetaRepository {
    override fun getCamisetas(): List<Camiseta> {
        return dataSource.getAll()
    }

    override fun insertCamiseta(camiseta: Camiseta) {
        dataSource.insert(camiseta)
    }

    override fun updateCamiseta(camiseta: Camiseta) {
        dataSource.update(camiseta)
    }

    override fun findCamisetaById(id: Int): Camiseta? {
        return dataSource.findById(id)
    }

    override fun deleteCamiseta(id: Int) {
        dataSource.deleteById(id)
    }
}

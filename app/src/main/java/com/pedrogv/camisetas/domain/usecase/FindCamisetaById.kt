package com.pedrogv.camisetas.domain.usecase

import com.pedrogv.camisetas.domain.model.Camiseta
import com.pedrogv.camisetas.domain.repository.CamisetaRepository
import javax.inject.Inject

class FindCamisetaById @Inject constructor(
    private val repository: CamisetaRepository
) {
    operator fun invoke(id: Int): Camiseta? {
        return repository.findCamisetaById(id)
    }
}

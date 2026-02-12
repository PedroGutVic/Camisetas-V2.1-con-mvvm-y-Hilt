package com.pedrogv.camisetas.domain.usecase

import com.pedrogv.camisetas.domain.model.Camiseta
import com.pedrogv.camisetas.domain.repository.CamisetaRepository
import javax.inject.Inject

class AddCamiseta @Inject constructor(
    private val repository: CamisetaRepository
) {
    operator fun invoke(camiseta: Camiseta) {
        repository.insertCamiseta(camiseta)
    }
}

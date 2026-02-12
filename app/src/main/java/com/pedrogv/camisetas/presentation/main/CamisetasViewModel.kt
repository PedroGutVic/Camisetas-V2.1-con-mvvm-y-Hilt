package com.pedrogv.camisetas.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrogv.camisetas.domain.model.Camiseta
import com.pedrogv.camisetas.domain.usecase.AddCamiseta
import com.pedrogv.camisetas.domain.usecase.DeleteCamiseta
import com.pedrogv.camisetas.domain.usecase.FindCamisetaById
import com.pedrogv.camisetas.domain.usecase.GetCamisetas
import com.pedrogv.camisetas.domain.usecase.UpdateCamiseta
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CamisetasViewModel @Inject constructor(
    private val getCamisetas: GetCamisetas,
    private val addCamiseta: AddCamiseta,
    private val updateCamiseta: UpdateCamiseta,
    private val findCamisetaById: FindCamisetaById,
    private val deleteCamiseta: DeleteCamiseta
) : ViewModel() {

    private val _camisetas = MutableLiveData<List<Camiseta>>()
    val camisetas: LiveData<List<Camiseta>> = _camisetas

    init {
        refreshCamisetas()
    }

    fun refreshCamisetas() {
        _camisetas.value = getCamisetas()
    }

    fun addCamiseta(camiseta: Camiseta) {
        addCamiseta.invoke(camiseta)
        refreshCamisetas()
    }

    fun updateCamiseta(camiseta: Camiseta) {
        updateCamiseta.invoke(camiseta)
        refreshCamisetas()
    }

    fun deleteCamiseta(id: Int) {
        deleteCamiseta.invoke(id)
        refreshCamisetas()
    }

    fun findCamisetaById(id: Int): Camiseta? {
        return findCamisetaById.invoke(id)
    }
}

package com.pedrogv.camisetas.presentation.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.domain.model.Camiseta
import com.pedrogv.camisetas.presentation.main.CamisetasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamisetaFormFragment : Fragment() {

    private val viewModel: CamisetasViewModel by activityViewModels()

    private val args: CamisetaFormFragmentArgs by navArgs()
    private var camisetaId: Int = -1
    private var camisetaToEdit: Camiseta? = null

    private lateinit var etNombre: EditText
    private lateinit var etPrecio: EditText
    private lateinit var etImagenUrl: EditText
    private lateinit var btnSave: Button
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        camisetaId = args.camisetaId

        if (camisetaId != -1) {
            camisetaToEdit = viewModel.findCamisetaById(camisetaId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camiseta_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNombre = view.findViewById(R.id.etNombre)
        etPrecio = view.findViewById(R.id.etPrecio)
        etImagenUrl = view.findViewById(R.id.etImagenUrl)
        btnSave = view.findViewById(R.id.btnSaveCamiseta)
        tvTitle = view.findViewById(R.id.tvFormTitle)

        camisetaToEdit?.let { camiseta ->
            tvTitle.text = "Editar Camiseta"
            etNombre.setText(camiseta.nombre)
            etPrecio.setText(camiseta.precio.toString())
            etImagenUrl.setText(camiseta.imagenUrl.toString())
            btnSave.text = "Actualizar"
        } ?: run {
            tvTitle.text = "Anadir Camiseta"
            btnSave.text = "Guardar"
        }

        btnSave.setOnClickListener {
            saveOrUpdateCamiseta()
        }
    }

    private fun saveOrUpdateCamiseta() {
        val nombre = etNombre.text.toString()
        val precioStr = etPrecio.text.toString()
        val imagenUrlStr = etImagenUrl.text.toString()

        if (nombre.isBlank() || precioStr.isBlank() || imagenUrlStr.isBlank()) {
            return
        }

        val precio = precioStr.toDoubleOrNull() ?: return
        val imagenUrl = imagenUrlStr.toIntOrNull() ?: return

        if (camisetaToEdit == null) {
            val newCamiseta = Camiseta(id = 0, nombre = nombre, precio = precio, imagenUrl = imagenUrl)
            viewModel.addCamiseta(newCamiseta)
        } else {
            val updatedCamiseta = Camiseta(id = camisetaId, nombre = nombre, precio = precio, imagenUrl = imagenUrl)
            viewModel.updateCamiseta(updatedCamiseta)
        }

        parentFragmentManager.popBackStack()
    }
}

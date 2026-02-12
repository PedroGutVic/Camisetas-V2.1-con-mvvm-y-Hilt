package com.pedrogv.camisetas.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.presentation.main.CamisetasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamisetaDetailFragment : Fragment() {

    private val args: CamisetaDetailFragmentArgs by navArgs()
    private val viewModel: CamisetasViewModel by activityViewModels()

    private lateinit var detailImage: ImageView
    private lateinit var detailName: TextView
    private lateinit var detailPrice: TextView
    private lateinit var detailId: TextView
    private lateinit var detailDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_camiseta_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailImage = view.findViewById(R.id.detailImage)
        detailName = view.findViewById(R.id.detailName)
        detailPrice = view.findViewById(R.id.detailPrice)
        detailId = view.findViewById(R.id.detailId)
        detailDescription = view.findViewById(R.id.detailDescription)

        viewModel.camisetas.observe(viewLifecycleOwner) { list ->
            val position = args.recyclerPosition
            if (position in list.indices) {
                val camiseta = list[position]
                detailName.text = camiseta.nombre
                detailPrice.text = getString(R.string.detail_price_format, camiseta.precio)
                detailId.text = getString(R.string.detail_id_format, camiseta.id, position)
                detailDescription.text = getString(
                    R.string.detail_description_format,
                    camiseta.nombre
                )
                Glide
                    .with(detailImage)
                    .load(camiseta.imagenUrl)
                    .centerCrop()
                    .into(detailImage)
            } else {
                detailName.text = getString(R.string.detail_not_found)
                detailPrice.text = ""
                detailId.text = ""
                detailDescription.text = ""
                detailImage.setImageResource(R.drawable.camiseta)
            }
        }
    }
}


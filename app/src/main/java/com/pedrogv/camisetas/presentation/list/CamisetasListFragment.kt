package com.pedrogv.camisetas.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.presentation.adapter.AdapterCamisetas
import com.pedrogv.camisetas.presentation.main.CamisetasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamisetasListFragment : Fragment() {

    private val viewModel: CamisetasViewModel by activityViewModels()
    private lateinit var adapter: AdapterCamisetas
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_camisetas_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.camisetasRecyclerView)
        fabAdd = view.findViewById(R.id.addCamisetaFab)

        adapter = AdapterCamisetas(
            listTshit = mutableListOf(),
            onEditClick = { camisetaId ->
                val action = CamisetasListFragmentDirections
                    .actionCamisetasListFragmentToCamisetaFormFragment(camisetaId)
                findNavController().navigate(action)
            },
            onDeleteClick = { camisetaId ->
                viewModel.deleteCamiseta(camisetaId)
            },
            onItemClick = { position ->
                val action = CamisetasListFragmentDirections
                    .actionCamisetasListFragmentToCamisetaDetailFragment(position)
                findNavController().navigate(action)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.camisetas.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list.toMutableList())
        }

        fabAdd.setOnClickListener {
            val action = CamisetasListFragmentDirections
                .actionCamisetasListFragmentToCamisetaFormFragment(-1)
            findNavController().navigate(action)
        }
    }
}


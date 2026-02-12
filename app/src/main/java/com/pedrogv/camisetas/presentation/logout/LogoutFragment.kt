package com.pedrogv.camisetas.presentation.logout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pedrogv.camisetas.R
import com.pedrogv.camisetas.presentation.login.LoginActivity

class LogoutFragment : Fragment(R.layout.fragment_logout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }
}

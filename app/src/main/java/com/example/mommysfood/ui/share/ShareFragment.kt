package com.example.mommysfood.ui.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mommysfood.R


class ShareFragment : Fragment() {

    private lateinit var shareViewModel: ShareViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Download this App")
        val app_url = "Wanna Keep track of your status of food consumed by you.Download this awesome app from the playstore link <-URL->"
        shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
        startActivity(Intent.createChooser(shareIntent, "Share via"))
        return root
    }
}
package com.example.spacex.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.databinding.CardviewMenuBinding
import com.example.spacex.model.listaRespuesta
import com.example.spacex.utils.alertDialogLoading
import com.example.spacex.utils.loadGlide
import com.example.spacex.utils.loadUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SapaceAdapter(var context: Context, listDatos: List<listaRespuesta>?) :
    RecyclerView.Adapter<SapaceAdapter.ViewHolderMenu>() {
    var listDatos: List<listaRespuesta>? = listDatos
    private var dialog: AlertDialog? = null

    private var binding: CardviewMenuBinding? = null
    private lateinit var scope: CoroutineScope
    @Override
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMenu {
        binding = CardviewMenuBinding.inflate(LayoutInflater.from(viewGroup.context)
            , viewGroup, false)
        scope =  CoroutineScope(Job() + Dispatchers.Main)
        val li = LayoutInflater.from(context)
        dialog = alertDialogLoading(
            context,
            li, context.getString(R.string.wait)
        )
        return ViewHolderMenu(binding!!.root)
    }


    override fun onBindViewHolder(holder: ViewHolderMenu, position: Int) {
        holder.apply {
                    bindData(listDatos!![position])
        }

    }


    @Override
    override fun getItemCount(): Int {
        return if (listDatos != null) {
            listDatos!!.size
        } else {
            0
        }
    }

    inner class ViewHolderMenu(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindData(datos: listaRespuesta){
            itemView.apply {

                if (datos.urlImg!= "")  binding!!.iconC.loadGlide(context, datos.urlImg)
                else
                    binding!!.iconC.loadGlide(context, context.getString(R.string.defaultUrl))



                binding!!.titles.text = datos.title
                binding!!.descrip.text = datos.overview
                binding!!.date.text = datos.overview


                binding!!.cardView.setOnClickListener {
                    if (datos.urlImg!= "") loadUrl(datos.urlImg,context)
                    else loadUrl(datos.urlWeb,context)

                }
            }
        }

    }


}

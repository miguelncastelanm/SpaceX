package com.example.spacex

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.FragmentFirstBinding
import com.example.spacex.model.listaRespuesta
import com.example.spacex.presenter.PresenterSpace
import com.example.spacex.ui.adapters.SapaceAdapter
import com.example.spacex.utils.GridSpacingItemPerfomance
import com.example.spacex.utils.alertDialogLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var arrayList = ArrayList<listaRespuesta>()
    var gridLayoutManager = GridLayoutManager(activity, 1)
    private lateinit var scope: CoroutineScope
    private var presenter = PresenterSpace()
    private var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        startService()
    }



    private fun initUi() {
        binding!!.listaSpace.layoutManager = gridLayoutManager as RecyclerView.LayoutManager?
        binding!!.listaSpace.addItemDecoration(GridSpacingItemPerfomance(2, 1, true))
        arrayList.clear()
        scope =  CoroutineScope(Job() + Dispatchers.Main)
        dialog = alertDialogLoading(
            context,
            layoutInflater,
            getString(R.string.wait)
        )

    }

    private fun startService() {
        scope.launch {
            dialog!!.show()
            var response = presenter.getLatest(getString(R.string.base_url),getString(R.string.timeout).toLong() )

            when(response.success){
                true->{
                    var data = listaRespuesta()
                    data.date_utc = response.date_utc!!
                    data.name = response.name!!
                    data.overview = "Space X Patch"
                    data.title = response.name!!
                    data.type = "img"
                    data.urlImg = response.links!!.patch.small!!
                    data.urlWeb = ""
                    arrayList.add(data)

                    data = listaRespuesta()
                    data.date_utc = response.date_utc!!
                    data.name = response.name!!
                    data.overview = "Space X Reddit"
                    data.title = response.name!!
                    data.type = "link"
                    data.urlImg = ""
                    data.urlWeb = response.links!!.reddit.campaign!!

                    arrayList.add(data)


                }
                else->{

                }

            }

            val a = SapaceAdapter(requireContext(), arrayList)
            binding!!.listaSpace.adapter = a
            a.notifyDataSetChanged()
            binding!!.listaSpace.clearOnScrollListeners()
            dialog!!.dismiss()

            dialog!!.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
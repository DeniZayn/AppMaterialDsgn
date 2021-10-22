package com.example.appmaterialdsgn

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_main.*


class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newIstance() = PictureOfTheDayFragment()
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    val text = "Link is Empty"
    val duration = Toast.LENGTH_SHORT


    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(this@PictureOfTheDayFragment,
            Observer<PictureOfTheDayData> { renderData(it) })
    }


    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(context, "Link is Empty", Toast.LENGTH_SHORT).show()
                } else {
                    image_view.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)

                    }
                }
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}







//                if (url.isNullOrEmpty()) {
//Отобразите ошибку
//                showError("Сообщение, что ссылка пустая")
//                } else {
//Отобразите фото
//showSuccess()
//Coil в работе: достаточно вызвать у нашего ImageView
//нужную extension-функцию и передать ссылку и заглушки для
//                    placeholder
//                        image_view.load(url) {
//                        lifecycle(this@PictureOfTheDayFragment)
//                        error(R.drawable.ic_load_error_vector)
//                        placeholder(R.drawable.ic_no_photo_vector)
//                    }
//                }
//            }
//            is MainActivity.PictureOfTheDayData.Loading -> {
//Отобразите загрузку
            //showLoading()
//            }
//        }
//        is MainActivity.PictureOfTheDayData.Error -> {
//        }





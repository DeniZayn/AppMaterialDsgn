package settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appmaterialdsgn.MainActivity
import com.example.appmaterialdsgn.R
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate (R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipGroup.setOnCheckedChangeListener {chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?. let {
                Toast.makeText(context, "Selected ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        chip_close.setOnCloseIconClickListener{
            Toast.makeText(
                context, "Close is Clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun changeTheme(isDark: Boolean) {
        (requireActivity() as? MainActivity)?. let {
            val themeId = if (isDark) R.style.AppTheme else R.style.AppThemeDark
            it. changeTheme(themeId)
            }
        activity?.recreate()
        requireActivity().recreate()

        fragmentManager?.beginTransaction()
            ?.replace(R.id.activity_chooser_view_content, Fragment())
            ?.addToBackStack(null)
            ?.commit()
    }
}


package com.example.textapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textOneSpan()
        textTwoSpan()
        textThreeSpan()
    }

    private fun textOneSpan() {

        val spannableStringBuilder = SpannableStringBuilder ("Test String Span.")

        val fColor = ForegroundColorSpan(Color.RED)
        spannableStringBuilder.setSpan(fColor, 2,4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val bColor = BackgroundColorSpan(Color.GREEN)
        spannableStringBuilder.setSpan(bColor, 6, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        spannableStringBuilder.append("Test")
        textViewOne.text = spannableStringBuilder
    }

    private fun textTwoSpan() {

        val spannableString = SpannableString("WTF?")

        val sizeSpan = RelativeSizeSpan(2f)
        spannableString.setSpan(sizeSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val styleSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val underlineSpan = UnderlineSpan()
        spannableString.setSpan(underlineSpan, 8, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        textViewTwo.text = spannableString
    }

    private fun textThreeSpan() {

        val spannableString = SpannableString("Click")

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@MainActivity, "OK!", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)

                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickableSpan, 0, 13, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        textViewThree.text = spannableString
        textViewThree.movementMethod = LinkMovementMethod.getInstance()
    }
}

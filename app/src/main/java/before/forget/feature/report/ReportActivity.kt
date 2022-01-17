package before.forget.feature.report

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.databinding.ActivityReportBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.io.ByteArrayOutputStream

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var reportViewPagerAdapter: ReportViewPagerAdapter

    private val cal: Calendar = Calendar.getInstance()
    private var currentYear = cal.get(Calendar.YEAR)
    private var currentMonth = cal.get(Calendar.MONTH) + 1
    private var selectYear = currentYear
    private var selectMonth = currentMonth
    private var IS_FIRST = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)

        initAdapter()
        initBtnClickListener()
        setContentView(binding.root)
    }

    private fun initBtnClickListener() {
        with(binding) {
            ivBackBtn.setOnClickListener {
                finish()
            }
            ivDownloadBtn.setOnClickListener {
                initShareSheet()
            }
            btnDatePicker.setOnClickListener {
                setDatePicker()
            }
        }
    }

    private fun initShareSheet() {
        val image: Bitmap = getBitmapFromView(binding.clReportArea)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, getImageUri(this, image))
        intent.setPackage("com.kakao.talk")
        startActivity(Intent.createChooser(intent, "공유"))
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    private fun getBitmapFromView(view: View): Bitmap {
        // Define a bitmap with the same size as the view
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        // Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        // Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) {
            // has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            // does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        // draw the view on the canvas
        view.draw(canvas)
        // return the bitmap
        return returnedBitmap
    }

    private fun setDatePicker() {
        val dialog = AlertDialog.Builder(this).create()
        val view: View =
            LayoutInflater.from(this).inflate(R.layout.layout_report_datepicker_dialog, null)
        val year: NumberPicker = view.findViewById(R.id.picker_year)
        val month: NumberPicker = view.findViewById(R.id.picker_month)
        val cancel: Button = view.findViewById(R.id.picker_cancel)
        val confirm: Button = view.findViewById(R.id.picker_confirm)

        // TODO: 서버에서 값 받아오기
        var firstRecordYear = 2018
        var firstRecordMonth = 0

        // release edittext setting
        year.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        year.wrapSelectorWheel = false

        month.minValue = 1
        month.maxValue = 12

        // setting current year and month
        if (IS_FIRST) {
            if (currentMonth == 1) {
                year.minValue = firstRecordYear
                year.maxValue = currentYear - 1
                currentYear = currentYear - 1
                currentMonth = 12
                year.value = currentYear
                month.value = currentMonth
            }
        } else {
            year.minValue = firstRecordYear
            year.maxValue = currentYear
            year.value = selectYear
            month.value = selectMonth
        }
        IS_FIRST = false
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }

        confirm.setOnClickListener {
            selectMonth = month.value
            selectYear = year.value
            binding.btnDatePicker.text = "${selectYear}년 ${selectMonth}월"

            dialog.dismiss()
            dialog.cancel()
        }

        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            ReportLabelingFragment(),
            ReportGraphFragment(),
            ReportRankingFragment(),
            ReportSentenceFragment(),
            ReportOnepageFragment()
        )

        reportViewPagerAdapter = ReportViewPagerAdapter(this)
        reportViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpReport.adapter = reportViewPagerAdapter

        initIndicator()
    }

    private fun initIndicator() {
        TabLayoutMediator(binding.tlReport, binding.vpReport) { _tab, _position ->
            _tab.setIcon(R.drawable.tab_selector_report)
        }.attach()
    }
}

package com.example.barbercut2

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class ScheduleActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button5: Button
    private lateinit var button3: Button
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        button5 = findViewById(R.id.button5)
        button3 = findViewById(R.id.button3)
        editText = findViewById(R.id.calendarioTXT)

        button3.setOnClickListener(this)
        regresar()
    }

    private fun regresar() {
        title = "Cambiar a perfil"
        button5.setOnClickListener {
            val intent = Intent(this@ScheduleActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(view: View?) {
        val dialogFecha = DatePickerFragment { year, month, day -> mostrar(year, month, day) }
        dialogFecha.show(supportFragmentManager, "DatePicker")
    }

    private fun mostrar(year: Int, month: Int, day: Int) {
        editText.setText("$year/$month/$day")
    }

    class DatePickerFragment(val listener: (year: Int, month: Int, day: Int) -> Unit) :
        DialogFragment(), DatePickerDialog.OnDateSetListener {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            var year = c.get(Calendar.YEAR)
            var month = c.get(Calendar.MONTH)
            var day = c.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(requireActivity(), this, year, month, day)
        }

        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
            listener(year, month, day)
        }
    }
}

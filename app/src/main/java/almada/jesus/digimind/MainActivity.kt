package almada.jesus.digimind

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.reminder_view.view.*

class MainActivity : AppCompatActivity() {
    var recordatorio = ArrayList<Reminder>()
    var adaptador : AdaptadorReminder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarRecordatorio()

        adaptador = AdaptadorReminder(this, recordatorio)

        gridview.adapter= adaptador

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    fun cargarRecordatorio(){
        //cargar recordatorios

        recordatorio.add(Reminder("Comprar Suplementos","Jueves","8:00"))
        recordatorio.add(Reminder("Pagar Mensualidad del gym","Lunes","6:00"))
        recordatorio.add(Reminder("terminar la tarea de ITB","viernes","19:00"))
        recordatorio.add(Reminder("Pastilla de la alergia","Jueves","15:00"))
        recordatorio.add(Reminder("Pasear al perro","Jueves","20:00"))
        recordatorio.add(Reminder("Darle Comida al perro","Jueves","13:00"))
        recordatorio.add(Reminder("Darle comida al perro","Jueves","19:00"))
        recordatorio.add(Reminder("comprar comida para el perro","Jueves","21:00"))
        recordatorio.add(Reminder("Comprar la despensa","Lunes","15:00"))
    }

    class AdaptadorReminder: BaseAdapter {
        var reminder = ArrayList<Reminder>()
        var contexto: Context? = null

        constructor(contexto:Context,reminder:ArrayList<Reminder>){
            this.contexto= contexto
            this.reminder= reminder
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var reminder = reminder[position]
            var inflater =
                contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflater.inflate(R.layout.reminder_view, null)

            vista.reminder_titulo.setText(reminder.titulo)
            vista.reminder_day.setText(reminder.dia)
            vista.reminder_hour.setText(reminder.hora)

            return vista
        }
        override fun getItem(position: Int): Any {
            return reminder[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return reminder.size
        }

    }
}

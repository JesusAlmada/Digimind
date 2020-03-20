package almada.jesus.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import almada.jesus.digimind.R
import almada.jesus.digimind.task
import android.content.Context
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.reminder_view.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas?=null
    companion object{
        var tasks = ArrayList<task>()
        var first = true
    }
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        if(first) {
            llenarTareas()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        root.gridview.adapter = adaptador

        return root
    }

    fun llenarTareas(){
        tasks.add(task(" examen ITB", arrayListOf("Tuesday"),"17:30"))
        tasks.add(task("tarea evaluacion de software", arrayListOf("Monday","Sunday"),"17:50"))
        tasks.add(task("subir proyecto Digimind a github", arrayListOf("Wednesday"),"14:00"))
        tasks.add(task("comprar comida", arrayListOf("friday"),"11:00"))
        tasks.add(task("comprar gel antibacterial", arrayListOf("Friday"),"13:00"))
        tasks.add(task("bañar a Dante", arrayListOf("Thursday"),"10:30"))
        tasks.add(task("desinfectar la casa", arrayListOf("Monday"),"12:30"))
    }

    private class AdaptadorTareas: BaseAdapter {
        var tasks = ArrayList<task>()
        var contexto: Context? = null
        constructor(contexto: Context, tasks:ArrayList<task>){
            this.contexto= contexto
            this.tasks = tasks
        }
        override fun getView(p0:Int, p1:View?, p2:ViewGroup): View {
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view,null)

            vista.tv_title.setText(task.title)
            vista.tv_time.setText(task.time)
            vista.tv_days.setText(task.days.toString())

            return vista
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }

    }
}
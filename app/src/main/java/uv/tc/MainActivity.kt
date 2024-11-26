package uv.tc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import uv.tc.databinding.ActivityMainBinding
import uv.tc.poko.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cliente: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        obtenerDatosCliente()
        cargarDatosCliente()
    }

    fun obtenerDatosCliente(){
        val jsoncliente = intent.getStringExtra("cliente")
        if(jsoncliente != null) {
            val gson = Gson()
            cliente = gson.fromJson(jsoncliente, Cliente::class.java)
        }
    }

    fun cargarDatosCliente(){
        binding.tvNombreCliente.text = cliente.nombreC + " " + cliente.apellidoPaterno + " " + cliente.apellidoMaterno
        binding.tvCorreo.text = cliente.correo
        binding.tvTelefono.text = cliente.telefono
        binding.tvFechaNacimiento.text = cliente.fechaNacimiento
        binding.tvFechaInscripcion.text = cliente.fechaInscripcion
        binding.tvEstatura.text = cliente.estatura.toString()
        binding.tvPeso.text = cliente.peso.toString()
        binding.tvEntrenadorD.text = cliente.nombreCo
    }
}
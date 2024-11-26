package uv.tc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.databinding.ActivityLoginBinding
import uv.tc.poko.LoginCliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnIngresar.setOnClickListener {
            val correo = binding.txtCorreo.text.toString()
            val password = binding.txtContrasenia.text.toString()
            if(sonCamposValidos(correo, password)){
                verificarCredenciales(correo, password)
            }
        }
    }

    fun sonCamposValidos(correo : String, password : String) : Boolean{
        var camposValidos = true
        if(correo.isEmpty()){
            camposValidos = false
            binding.txtCorreo.setError("Correo electronico obligatorio.")
        }
        if(password.isEmpty()){
            camposValidos = false
            binding.txtContrasenia.setError("Contraseña obligatoria.")
        }
        return camposValidos
    }

    fun verificarCredenciales(correo: String, password: String){
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false) //Esta linea solo se pone una sola ves que se quiera usar Ion
        //Consumo WS
        Ion.with(this@LoginActivity)
            .load("POST", "http://192.168.0.112:8084/WSRestM/api/login/cliente") //Tienes que cambiar siempre la IP desde cmd
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("correo", correo)
            .setBodyParameter("password", password)
            .asString()
            .setCallback { e, result ->
                if(e == null){
                    serializarInformacion(result)
                } else {
                    Toast.makeText(this@LoginActivity, "Error: "+e.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun serializarInformacion(json : String){
        val gson = Gson()
        val respuestaLoginCliente = gson.fromJson(json, LoginCliente::class.java)
        Toast.makeText(this@LoginActivity, respuestaLoginCliente.mensaje, Toast.LENGTH_LONG).show()
        if(!respuestaLoginCliente.error){
            val clienteJSON = gson.toJson(respuestaLoginCliente.cliente)
            irPantallaPrincipal(clienteJSON)
        }
    }

    fun irPantallaPrincipal(cliente : String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivity(intent)
        finish()
    }
}
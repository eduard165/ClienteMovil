package uv.tc.poko

data class Cliente(
    val idCliente : Int,
    val nombreC : String,
    val apellidoPaterno : String,
    val apellidoMaterno : String,
    val fechaNacimiento : String,
    val telefono : String,
    val peso : Float,
    val estatura : Int,
    val correo : String,
    val password : String,
    val idColaborador : Int,
    val nombreCo : String,
    val fechaInscripcion : String,
    val foto : String,
    val fotoBase64 : String ?
)
package uv.tc.poko

data class LoginCliente(
    val error : Boolean,
    val mensaje : String,
    val cliente : Cliente ?
)


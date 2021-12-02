package br.edu.ifsp.scl.ads.pdm.seriesmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifsp.scl.ads.pdm.seriesmanager.R
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityCadastrarUsuarioBinding

class CadastrarUsuarioActivity : AppCompatActivity() {
    private val activityCadastrarUsuarioBinding: ActivityCadastrarUsuarioBinding by lazy{
        ActivityCadastrarUsuarioBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityCadastrarUsuarioBinding.root)
        supportActionBar?.subtitle = "Cadastrar usuário"
        with(activityCadastrarUsuarioBinding){
            cadastrarUsuarioBt.setOnClickListener{
                val email = emailEt.text.toString()
                val senha = senhaEt.text.toString()
                val repetirSenha = repetirSenhaEt.text.toString()
                if(senha == repetirSenha){
                    // cad user
                    AutenticacaoFirebase.firebaseAuth.createUserWithEmailAndPassword(email,senha).addOnSuccessListener {
                        Toast.makeText(this@CadastrarUsuarioActivity, "Usuário $email cadastrado", Toast.LENGTH_SHORT).show()
                        finish()
                    }.addOnFailureListener{
                        Toast.makeText(this@CadastrarUsuarioActivity, "Falha do cadastro", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this@CadastrarUsuarioActivity, "Senhas não coincidem", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
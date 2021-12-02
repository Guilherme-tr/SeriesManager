package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityAutenticacaoBinding
import com.google.firebase.auth.FirebaseAuth

class AutenticacaoActivity : AppCompatActivity() {
    private val activityAutenticacaoBiding: ActivityAutenticacaoBinding by lazy {
        ActivityAutenticacaoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityAutenticacaoBiding.root)
        supportActionBar?.subtitle = "Autenticação"

        with(activityAutenticacaoBiding){
            cadastrarUsuarioBt.setOnClickListener{
                startActivity(Intent(this@AutenticacaoActivity, CadastrarUsuarioActivity::class.java))
            }

            recuperarSenhaBt.setOnClickListener{
                startActivity(Intent(this@AutenticacaoActivity, RecuperarSenhaActivity::class.java))
            }

            entrarBt.setOnClickListener{
                val email = emailEt.text.toString()
                val senha = senhaEt.text.toString()
                AutenticacaoFirebase.firebaseAuth.signInWithEmailAndPassword(email, senha).addOnSuccessListener {
                    Toast.makeText(this@AutenticacaoActivity, "Usuário autenticado", Toast.LENGTH_SHORT).show()
                    iniciarSerieListagemActivity()
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this@AutenticacaoActivity, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(AutenticacaoFirebase.firebaseAuth.currentUser != null){
            iniciarSerieListagemActivity()
        }
    }

    private fun iniciarSerieListagemActivity(){
        startActivity(Intent(this@AutenticacaoActivity, SerieListagemActivity::class.java))
        finish()
    }
}
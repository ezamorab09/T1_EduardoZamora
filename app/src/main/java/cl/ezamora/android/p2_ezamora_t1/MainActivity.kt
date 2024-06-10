package cl.ezamora.android.p2_ezamora_t1

/* importacion de librerias necesarias */
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import cl.ezamora.android.taller_unidad_1.Modelo.Cuenta

/* definicion de la clase principal MainActivity */

class MainActivity : AppCompatActivity() {
    /* Declaracion de variables EditText y switch */

    private lateinit var etTotalPedido: EditText
    private lateinit var etPropina: EditText
    private lateinit var etTotalVenta: EditText
    private lateinit var etCant1: EditText
    private lateinit var etCant2: EditText
    private lateinit var etValorPastelDeChoclo: EditText
    private lateinit var etValorCazuela: EditText
    private lateinit var switchPropina: Switch

    /*Declaración de una instancia de la clase Cuenta y una variable booleana para la propina*/

    private var cuenta: Cuenta = Cuenta()
    private var calcularPropina: Boolean = false

    /* Metodo que se ejecuta cuando se crea la actividad*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    /* Inicializacion de las variables con los componentes */

        switchPropina = findViewById(R.id.switchPropina)
        etCant1 = findViewById(R.id.etCant1)
        etCant2 = findViewById(R.id.etCant2)
        etValorPastelDeChoclo = findViewById(R.id.etVentaPastelDeChoclo)
        etValorCazuela = findViewById(R.id.etVentaCazuela)
        etTotalPedido = findViewById(R.id.etTotalPedido)
        etTotalVenta = findViewById(R.id.etTotalVenta)
        etPropina = findViewById(R.id.etPropina)

        /* Manejar el cambio de texto en los EditText*/

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                actualizarValores()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        /* Se declaran los TextWatcher a los editText correspondientes*/

        etCant1.addTextChangedListener(textWatcher)
        etCant2.addTextChangedListener(textWatcher)

        /* Se Maneja el cambio de estado del Switch para calcular la propina */

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            calcularPropina = isChecked
            actualizarValores()
        }
    }
    /* metodo para actualizar los valores de los EditText segun lo que se ingrese*/

    private fun actualizarValores() {
        /* Se obtienen y cambian las cantidades de pastel de choclo y cazuela */

        val cantidadPastelDeChoclo = etCant1.text.toString().toFloatOrNull() ?: 0.0f
        val cantidadCazuela = etCant2.text.toString().toFloatOrNull() ?: 0.0f

        /* Se calcula el valor total de cada producto */

        val valorPastelDeChoclo = cantidadPastelDeChoclo * 12000
        val valorCazuela = cantidadCazuela * 10000

        /* Se actualizan los EditText con los valores calculados */

        etValorPastelDeChoclo.setText(valorPastelDeChoclo.toString())
        etValorCazuela.setText(valorCazuela.toString())

        /* Se genera una nueva instancia de CUENTA con los valores calculados*/

        cuenta = Cuenta(mutableListOf(valorPastelDeChoclo, valorCazuela))
        val totalPedido = cuenta.calcularTotalSinBonificacion()
        etTotalPedido.setText(totalPedido.toString())

        /* Se establece el calculo de la PROPINA si corresponde */

        val propina = if (calcularPropina) totalPedido * 0.1f else 0.0f
        etPropina.setText(propina.toString())

        /* Se establece y calcula el total de la venta incluyendo la propina */

        val totalVenta = totalPedido + propina
        etTotalVenta.setText(totalVenta.toString())
    }
}


import kotlin.random.Random

class Gate(private val gate: GateImpl) {
    fun getResult(): Boolean = gate.getResultImpl() // delegare din implementare
}

class GateBuilder(n: Int) {
    private val inputs: MutableList<Boolean>
    private var last: Int = 0

    init {
        inputs = MutableList(n) { false } //init
    }

    fun input(v: Boolean) {
        inputs[last] = v
        last++
    }

    fun makeGate(): Gate {
        return Gate(GateImpl(inputs)) //constr gate cu implementarea gateimpl
    }
}

class GateImpl(private val inputs: List<Boolean>) {
    fun getResultImpl(): Boolean = inputs.all { it } // Calculează AND pt toate intrările.
}

//fsm lipsa

fun main() {
    for (n in listOf(2, 3, 4, 8)) {
        val builder = GateBuilder(n)
        println("Gate "+ n);
        for (i in 0 until n) {
            builder.input(Random.nextBoolean())
        }

        println("result = ${builder.makeGate().getResult()}")
    }
}

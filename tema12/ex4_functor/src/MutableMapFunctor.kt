class MutableMapFunctor<K, V>(val map: MutableMap<K, V>) {
    fun map(function: (V) -> V): MutableMapFunctor<K, V> {
        val result = mutableMapOf<K, V>()

        for (it in map) {
            result[it.key] = function(it.value)
        }
        return MutableMapFunctor(result)
    }
}
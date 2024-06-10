package cl.ezamora.android.taller_unidad_1.Modelo

class Cuenta(val pedidos: MutableList<Float> = mutableListOf<Float>()) {
    fun agregarPedido(pedido: Float) {
        pedidos.add(pedido)
    }

    fun calcularBonificacion(): Float {
        if (pedidos.isEmpty()) return 0.0f
        val sumaPedidos = pedidos.sum()
        return sumaPedidos * 0.1f // 10% de bonificación
    }

    fun calcularTotalConBonificacion(): Float {
        val sumaPedidos = pedidos.sum()
        val bonificacion = calcularBonificacion()
        return sumaPedidos + bonificacion
    }

    fun calcularTotalSinBonificacion(): Float {
        return pedidos.sum()
    }
}


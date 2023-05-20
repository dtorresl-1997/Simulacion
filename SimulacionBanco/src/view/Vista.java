package view;

import javax.swing.JOptionPane;

public class Vista {
    public static void mostrarEstadisticasFinales(int[] clientesAtendidosPorFranja, String[] tiposTransaccion,
            int[][] transaccionesAtendidasTotales, int[][] tiempoAtencionPorTransaccionYCajero) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Estadísticas finales\n");
        mensaje.append("--------------------\n\n");

        mensaje.append("Número total de clientes atendidos por franja horaria:\n");
        for (int i = 0; i < clientesAtendidosPorFranja.length; i++) {
            mensaje.append("Franja horaria ").append(i + 1).append(": ").append(clientesAtendidosPorFranja[i]).append("\n");
        }

        mensaje.append("\nDistribución de transacciones por tipo de transacción atendidas totales:\n");
        for (int i = 0; i < tiposTransaccion.length; i++) {
            mensaje.append("Transacción: ").append(tiposTransaccion[i]).append("\n");
            for (int j = 0; j < transaccionesAtendidasTotales[i].length; j++) {
                mensaje.append("Cajero ").append(j + 1).append(": ").append(transaccionesAtendidasTotales[i][j]).append("\n");
            }
        }

        mensaje.append("\nLista de transacciones clasificadas descendentemente por cajero:\n");
        for (int i = 0; i < tiempoAtencionPorTransaccionYCajero.length; i++) {
            mensaje.append("Cajero ").append(i + 1).append(":\n");
            for (int j = 0; j < tiempoAtencionPorTransaccionYCajero[i].length; j++) {
                int tiempoAtencion = tiempoAtencionPorTransaccionYCajero[i][j];
                if (tiempoAtencion > 0) {
                    mensaje.append(tiposTransaccion[j]).append(": ").append(tiempoAtencion).append(" minutos\n");
                }
            }
        }

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Estadísticas finales", JOptionPane.INFORMATION_MESSAGE);
    }
}

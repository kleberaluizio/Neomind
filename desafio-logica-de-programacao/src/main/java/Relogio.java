public class Relogio {

    public int retornaAnguloRelogio(int hora, int minuto) {

        int grausPorMinuto = 6; // Ponteiro do minuto: 00:30h = 180 graus (180/30)!
        int grausPorHora = 30;  // Ponteiro da hora: 1h = 30 graus!

        try {
            verificaDadosDeEntrada(hora, minuto);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return -1;
        }

        int anguloEntrePonteiroHoraEZero = hora * grausPorHora;
        int anguloEntrePonteiroMinutoEZero = minuto * grausPorMinuto;
        int anguloEntrePonteiros = Math.abs(anguloEntrePonteiroHoraEZero - anguloEntrePonteiroMinutoEZero);

        return anguloEntrePonteiros;
    }

    public void verificaDadosDeEntrada(int hora, int minuto) {


        if (minuto > 60) {
            throw new IllegalArgumentException("O valor informado em minutos não pode ser maior a 60 minutos");
        }
        if (hora > 24) {
            throw new IllegalArgumentException("O valor informado em hora não pode ser maior a 24 horas");
        }
        if (hora < 0 || minuto < 0) {
            throw new IllegalArgumentException("O valor informado em hora e/ou minuto não pode ser menor que Zero");
        }
        // Considerando 24 horas por ciclo (NBR 5892:2019 - Tempo, datas e horas)
        if (hora > 12) {
            hora = hora - 12;
        }

    }
}

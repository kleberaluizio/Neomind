public class Calcular {

    //Dado um determinado horário, ele seja capaz de calcular o ângulo entre os 2 ponteiros do relógio.
    public int retornaAnguloRelogio(int hora, int minuto) {

        int grausPorMinuto = 6; // 00:30h = 180 graus (180/30)!
        int grausPorHora = 30;

        // Considerando 24 horas por ciclo (NBR 5892:2019 - Tempo, datas e horas)
        if (hora > 12) hora = hora - 12;

        if (minuto > 60){
            // Lançar exceção!
        }
        if (hora > 24){
            // Lançar exceção!
        }
        int anguloEntreHoraZero = hora * grausPorHora;
        int anguloEntreMinutoZero = minuto * grausPorMinuto;
        int anguloEntrePonteiros = Math.abs(anguloEntreHoraZero - anguloEntreMinutoZero);

        return anguloEntrePonteiros;
    }
}

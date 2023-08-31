public class Relogio {

    public int retornaAnguloRelogio(int hora, int minuto) {

        int grausPorMinuto = 6; // Ponteiro do minuto: 00:30h = 180 graus (180/30)!
        int grausPorHora = 30;  // Ponteiro da hora: 1h = 30 graus!

        // Considerando 24 horas por ciclo (NBR 5892:2019 - Tempo, datas e horas)
        if (hora > 12) {hora = hora - 12;}
        
        if (minuto > 60){
            throw new RuntimeException("O valor informado em minutos não pode ser maior a 60 minutos");
        }
        if (hora > 24){
            throw new RuntimeException("O valor informado em hora não pode ser maior a 24 horas");
        }
        if (hora < 0 || minuto < 0){
            throw new RuntimeException("O valor informado em hora e/ou minuto não pode ser menor Zero");
        }

        int anguloEntrePonteiroHoraEZero = hora * grausPorHora;
        int anguloEntrePonteiroMinutoEZero = minuto * grausPorMinuto;
        int anguloEntrePonteiros = Math.abs(anguloEntrePonteiroHoraEZero - anguloEntrePonteiroMinutoEZero);

        return anguloEntrePonteiros;
    }
}

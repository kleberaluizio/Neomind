public class Main {
    public static void main(String[] args) {

        int hora = 3;
        int minuto = -59;

        Relogio relogio = new Relogio();
        int anguloEntrePonteiros = relogio.retornaAnguloRelogio(hora, minuto);

        System.out.println(anguloEntrePonteiros);
    }



}
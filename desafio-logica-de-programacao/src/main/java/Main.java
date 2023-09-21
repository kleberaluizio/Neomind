public class Main {
    public static void main(String[] args) {

        int hora = 2;
        int minuto = 49;

        Relogio relogio = new Relogio();
        int anguloEntrePonteiros = relogio.retornaAnguloRelogio(hora, minuto);

        System.out.println(anguloEntrePonteiros);
    }



}
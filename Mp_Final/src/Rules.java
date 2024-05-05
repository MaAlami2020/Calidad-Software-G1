public class Rules {


    public String ShowRules(){
        String rules = "Reglas";
        rules += "1. Cada jugador debe elegir un personaje para pelear, con habilidades, equipo, y esbirros.";
        rules += "2. El objetivo del juego es reducir la barra de vida del oponente a cero.";
        rules += "3. Para poder pelear le deberas mandar un desafío a quién quieras desafiar ";
        rules += "4. No podrás mandar un desafío a un jugador que haya perdido en el último día o podrás ser baneado ";
        rules += "5. La pelea se divide en rondas, donde cada jugador tira un dado para realizar una accion, en caso de éxito se comparan potenciales y el que tenga mas daño que defensa al segundo jugador le quita un pinto de vida";
        rules += "6. Cada personaje tiene unas caracteristicas unicas que varían a lo largo de la pelea";
        return rules;

    }
}

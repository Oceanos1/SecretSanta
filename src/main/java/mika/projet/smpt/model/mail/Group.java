package mika.projet.smpt.model.mail;

/**
 * Created by Michael Spierer
 */
public class Group {
    Personne secretSanta;
    Personne reciever;

    public Group(){ }

    public Personne getSecretSanta() {
        return secretSanta;
    }

    public void setSecretSanta(Personne secretSanta) {
        this.secretSanta = secretSanta;
    }

    public Personne getReciever() {
        return reciever;
    }

    public void setReciever(Personne reciever) {
        this.reciever = reciever;
    }

    public Boolean isValid(){
        return !secretSanta.equals(reciever);
    }
}

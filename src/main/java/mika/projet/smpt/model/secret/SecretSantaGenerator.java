package mika.projet.smpt.model.secret;

import mika.projet.smpt.config.IConfig;
import mika.projet.smpt.model.mail.Group;
import mika.projet.smpt.model.mail.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael Spierer
 */
public class SecretSantaGenerator {
    private IConfig config;
    private final int NB_MIN_PARTICIPANTS = 1;

    public SecretSantaGenerator(IConfig config) {
        this.config = config;
    }

    public List<SecretSanta> generatePranks() {
        List<String> messages = new ArrayList<String>(config.getMessages());
        List<SecretSanta> secretSantas = new ArrayList<SecretSanta>();

        if(config.getParticipants().size() <= NB_MIN_PARTICIPANTS){
            throw new IllegalStateException("Il n'y a pas assez de participants");
        }
        // Genere des groupes de 2 valides
        List<Group> groups = generateGroups(config.getParticipants());

        for (Group g : groups) {

            String message = "Tu es le Secret Santa de "+ g.getReciever().getName()+" "+g.getReciever().getLastName();
            SecretSanta p = new SecretSanta(config.getOrganisateur(), g.getSecretSanta(), messages.get(0)+message);

            secretSantas.add(p);
        }
        return secretSantas;
    }

    public List<Group> generateGroups(List<Personne> participants) {

        Boolean isValid;
        List<Group> groups;
        do {
            isValid=true;
            groups = new ArrayList<Group>();
            Collections.shuffle(participants);

            for (int i = 0; i < participants.size(); ++i) {
                groups.add(new Group());
                groups.get(i).setSecretSanta(participants.get(i));
                groups.get(i).setReciever(participants.get((i+1)%participants.size()));
                isValid &= groups.get(i).isValid();
            }

        }while(!isValid);

        //pour debug, verification dans la console
        /*
        int i = 1;
        for (Group g :groups) {
            System.out.println("Groupe "+i+": "+g.getSecretSanta().getName() +" est le santa de "+ g.getReciever().getName());
            i++;
        }
        */
        return groups;
    }
}

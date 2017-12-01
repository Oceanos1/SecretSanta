package mika.projet.smpt.model.mail;

/**
 * Created by Michael Spierer
 */
public class Personne {
    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public Personne(String line) {
        String[] getNames = line.split(",");
        this.email = getNames[0];
        //recupere le nom et le prenom du mail et mets la premiere lettre en majuscule
        this.name = getNames[1].substring(0, 1).toUpperCase() + getNames[1].substring(1);
        this.lastName = getNames[2].substring(0, 1).toUpperCase() + getNames[2].substring(1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Personne.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Personne other = (Personne) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (! this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
}

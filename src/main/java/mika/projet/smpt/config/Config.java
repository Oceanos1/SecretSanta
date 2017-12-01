package mika.projet.smpt.config;

import mika.projet.smpt.model.mail.Personne;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Michael Spierer
 */
public class Config implements IConfig {

    private String smtpServerAddress;
    private int smtpServerPort;
    private Personne organisateur;
    private final List<Personne> participants;
    private final List<String> messages;

    //constructor
    public Config() throws IOException {
        participants = loadAddressesFromFile("./config/participants.utf8");
        messages = loadMessagesFromFile("./config/messages.utf8");
        loadProprietes("./config/config.properties");
    }

    //create list of Personne from file containing list of email addresses
    public List<Personne> loadAddressesFromFile(String fileName) throws IOException {
        List<Personne> personnes = new ArrayList<Personne>();
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String addresse = reader.readLine();
        while (addresse != null) {
            personnes.add(new Personne(addresse));
            addresse = reader.readLine();
        }
        return personnes;
    }

    //Create list of String (messages) from file containing messages separate by ==
    public List<String> loadMessagesFromFile(String fileName) throws IOException {
        List<String> result = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String line = reader.readLine();
        while (line != null) {
            StringBuilder sb = new StringBuilder();
            while ((line != null) && (!line.equals("=="))) {
                sb.append(line + "\r\n");
                line = reader.readLine();
            }
            result.add(sb.toString());
            line = reader.readLine();
        }
        return result;
    }

    public List<Personne> getParticipants() {
        return participants;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Personne getOrganisateur() {
        return organisateur;
    }

    private void loadProprietes(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        this.smtpServerAddress = properties.getProperty("smtpServerAdress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.organisateur = new Personne(properties.getProperty("organisator"));
    }
}

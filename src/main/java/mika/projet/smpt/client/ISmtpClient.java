package mika.projet.smpt.client;

import mika.projet.smpt.model.secret.SecretSanta;

import java.io.IOException;

/**
 * Created by Michael Spierer
 */
public interface ISmtpClient {

    void sendMessage(SecretSanta p) throws IOException;

}

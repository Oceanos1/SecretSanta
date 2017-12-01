package mika.projet.smpt.client;


import mika.projet.smpt.model.secret.SecretSanta;
import mika.projet.smpt.protocol.SmtpProtocol;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Michael Spierer
 */
public class SmtpClientImpl implements ISmtpClient {


    private static final Logger LOG = Logger.getLogger(SmtpClientImpl.class.getName());

    private String smtpServerAdress;
    private int smtpServerPort;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SmtpClientImpl(String smtpServerAdress, int port) throws IOException {
        this.smtpServerAdress = smtpServerAdress;
        this.smtpServerPort = port;
    }

    private void sendToServer(String s) {
        out.print(s);
        out.flush();
    }


    public void sendMessage(SecretSanta p) throws IOException {
        String response;
        socket = new Socket(smtpServerAdress, smtpServerPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

        LOG.info(in.readLine());

        sendToServer(SmtpProtocol.CMD_EHLO + "secretsanta.com\r\n");

        while (!(response = in.readLine()).startsWith("250 ")) {
            LOG.info(response);
        }

        LOG.info(response);

        sendToServer(SmtpProtocol.CMD_MAIL_FROM + p.getEnvoyeur().getEmail() + "\r\n");

        LOG.info(in.readLine());

        sendToServer(SmtpProtocol.CMD_RCPT_TO + p.getReceveurs().getEmail() + "\r\n");
        LOG.info(in.readLine());

        sendToServer(SmtpProtocol.CMD_DATA);
        LOG.info(in.readLine());

        out.write("From: " + p.getEnvoyeur().getEmail() + "\r\n");


        if (p.getReceveurs() != null) {
            out.write("To: " + p.getReceveurs().getEmail());
            out.write("\r\n");
        }

        out.flush();

        sendToServer(p.getMessageToSend());
        sendToServer(SmtpProtocol.CMD_DATA_END);

        LOG.info(in.readLine());

        sendToServer(SmtpProtocol.CMD_BYE);
        socket.close();
        in.close();
        out.close();
    }


}

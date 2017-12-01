
package mika.projet.smpt.protocol;

/**
 * Created by Michael Spierer
 */
public class SmtpProtocol {


    public final static String CMD_EHLO = "EHLO ";
    public final static String CMD_MAIL_FROM = "MAIL FROM: ";
    public final static String CMD_RCPT_TO = "RCPT TO: ";
    public final static String CMD_DATA = "DATA\r\n";
    public final static String CMD_DATA_END = "\r\n.\r\n";
    public final static String CMD_BYE = "quit\r\n";

    public final static String[] SUPPORTED_COMMANDS = new String[]{CMD_EHLO, CMD_MAIL_FROM, CMD_RCPT_TO, CMD_DATA, CMD_DATA_END, CMD_BYE};

}
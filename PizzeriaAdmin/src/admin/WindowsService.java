package admin;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class WindowsService {
	private final static Component frame = null;

	/*
	 * Ilmoitusruutu, kun k‰ytt‰j‰ on syˆtt‰nyt kirjautumistiedot liian monta kertaa v‰‰rin
	 */
	
	public void bannedWindow() {
		final String content = "Virheellisten kirjautumisten m‰‰r‰ t‰ynn‰.\n You have been banned.";
		final String topic = "Too bad, so sad.";
		JOptionPane.showMessageDialog(frame,
			    content, topic,
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/*
	 * Ilmoitusruutu, kun bannilistalla oleva k‰ytt‰j‰ koittaa kirjautua
	 */
	
	public void isBannedWindow(int timeLeft) {
		final String content = "P‰‰sy estetty.\n Voit koittaa uudelleen\n" + 
			    timeLeft + " minuutin kuluttua.";
		final String topic = "Too bad, so sad.";
		JOptionPane.showMessageDialog(frame,
			    content, topic,
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/*
	 * Kirjautumisikkuna
	 */
	
	@SuppressWarnings("deprecation")
	public ArrayList<String> loginWindow() {
		final String topic = "Kirjautuminen";
		ArrayList<String> idPassword = new ArrayList<String>();
		JTextField jtf = new JTextField(16);
		jtf.addAncestorListener(new RequestFocusListener()); //Tehd‰‰n kentt‰ k‰ytt‰j‰tunnukselle ja laitetaan siihen focus
		JPasswordField jpf = new JPasswordField(24);
		final JLabel lt = new JLabel("Anna k‰ytt‰j‰tunnus: ");
	    final JLabel ls = new JLabel("Anna salasana: ");
	    Box box = Box.createVerticalBox(); 
	    box.add(lt);
	    box.add(jtf);
	    box.add(ls);
	    box.add(jpf);
	    int x = JOptionPane.showConfirmDialog(null, box, topic, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION) {
			return idPassword;
		}
		if (x == JOptionPane.OK_OPTION) {
			idPassword.add(0, jtf.getText());
			idPassword.add(1, jpf.getText());
			return idPassword;
		}
		return idPassword;
	}
}

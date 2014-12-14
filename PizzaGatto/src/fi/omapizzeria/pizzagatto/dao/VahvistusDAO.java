package fi.omapizzeria.pizzagatto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fi.omapizzeria.pizzagatto.bean.Tilaus;
import fi.omapizzeria.pizzagatto.bean.Tilausrivi;

public class VahvistusDAO extends Yhteys{
	
	public VahvistusDAO() throws DAOPoikkeus{
		
	}
	
	/**
	 * Lis‰‰ k‰ytt‰j‰n tilauksen Tilaus-tauluun
	 * @param t Lis‰tt‰v‰ tilaus
	 * @param ti Tilaukseen kuuluvat tilausrivit
	 */
	public void lisaaVahvistus(Tilaus t, ArrayList<Tilausrivi> ti) throws DAOPoikkeus{
		Connection yhteys = avaaYhteys();
		try {
			int maxid=0;
			String sql = "insert into Tilaus(katuosoite, puhelinnumero, kokonaishinta, postinumero) values(?,?,?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			lause.setString(1, t.getAsiakastiedot().getOsoite());
			lause.setString(2, t.getAsiakastiedot().getPuhelin());
			lause.setDouble(3, t.getKokonaishinta());
			lause.setString(4, t.getAsiakastiedot().getPstnro());
			lause.executeUpdate();
			String max = "select MAX(tilaus_id) from Tilaus";
			PreparedStatement lause3 = yhteys.prepareStatement(max);
			lause3.executeUpdate();
			ResultSet setit = lause3.getResultSet();
			if(setit.next()){
				maxid=setit.getInt(1);
			}

			for (int i = 0; i < ti.size(); i++) {
				String sql2 = "insert into Tilausrivi(tilaus_id, pizza_id, juoma_id, rivihinta, maara) values(?,?,?,?,?)";
				PreparedStatement lause2 = yhteys.prepareStatement(sql2);
				lause2.setInt(1, maxid);
				lause2.setInt(2, ti.get(i).getTuote().getId());
				lause2.setInt(3, ti.get(i).getTuote().getId());
				lause2.setDouble(4, ti.get(i).getKokonaishinta());
				lause2.setInt(5, ti.get(i).getKpl());
				lause2.executeUpdate();
			}
		} catch(Exception e) {
			throw new DAOPoikkeus("Tilauksen vahvistusyritys aiheutti virheen", e);
		}finally {
			suljeYhteys(yhteys);
		}
	}
}

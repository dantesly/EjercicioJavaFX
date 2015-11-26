package ch.makery.address.control;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Audio extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            FileInputStream fis;
            Player player;
            fis = new FileInputStream(
                    "C:/Users/Borja Prieto 01/git/EjercicioJavaFX/AddressApp/resources/sound/fallout_sound.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play();          // Llamada al método play
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

}

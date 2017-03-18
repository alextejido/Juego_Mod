/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_proyecto;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Alex
 */
public class Sonidos {
    public static final AudioClip Fondo= Applet.newAudioClip(Sonidos.class.getResource("/14.wav"));
    public static final AudioClip Rebote= Applet.newAudioClip(Sonidos.class.getResource("/salto.wav"));
    public static final AudioClip Gameover= Applet.newAudioClip(Sonidos.class.getResource("/GameOver.wav"));
    public static final AudioClip Juego= Applet.newAudioClip(Sonidos.class.getResource("/juego.wav"));
}

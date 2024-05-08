/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ahorcado extends JFrame implements ActionListener {
    private String[] palabras = {
    "hector", "barcelona", "amor", "perro", "gato", "casa", "computadora", "jardin", "familia", "felicidad", "estrella", "playa",
    "montaña", "avion", "escuela", "cielo", "luna", "sol", "nube", "rio", "bosque", "calle",
    "ciudad", "hoja", "libro", "television", "cafe", "taza", "silla", "mesa", "ropa", "zapato",
    "amigo", "risa", "dulce", "juego", "alegria", "color", "comida", "fiesta", "luz", "musica",
    "baile", "naturaleza", "mañana", "tarde", "noche", "cancion", "poesia", "arte", "foto", "vida",
    "viaje", "aleteo", "susurro", "silencio", "invierno", "primavera", "verano", "otoño", "deseo", "sueño",
    "esperanza", "imaginacion", "sabor", "olvido", "recuerdo", "alegria", "tristeza", "soledad", "amistad", "amabilidad",
    "belleza", "abrazo", "beso", "mariposa", "pais", "mundo", "universo", "sonrisa", "lagrima", "cariño",
    "dolor", "corazon", "fantasia", "realidad", "creatividad", "serenidad", "exito", "fracaso", "amoroso", "aventura"
};

    private String palabraAdivinar;
    private int intentos = 6;
    private StringBuilder palabraEscondida;

    private JLabel palabraEscondidaJlabel;
    private JLabel intentosRestantesJlabel;
    private JTextField adivinarTextField;
    private JButton adivinarButton;

    public Ahorcado() {
        setTitle("El juego del Ahorcado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        palabraEscondidaJlabel = new JLabel();
        intentosRestantesJlabel = new JLabel("Intentos restantes : " + intentos);
        adivinarTextField = new JTextField(10);
        adivinarButton = new JButton("Adivinar");

        adivinarButton.addActionListener(this);
        adivinarTextField.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(palabraEscondidaJlabel);
        mainPanel.add(intentosRestantesJlabel);
        mainPanel.add(adivinarTextField);
        mainPanel.add(adivinarButton);

        getContentPane().add(mainPanel);

        IniciarJuego();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void IniciarJuego() {
        palabraAdivinar = palabras[(int) (Math.random() * palabras.length)];
        palabraEscondida = new StringBuilder();
        for (int i = 0; i < palabraAdivinar.length(); i++) {
            palabraEscondida.append("_");
        }
        palabraEscondidaJlabel.setText(palabraEscondida.toString());
        intentos = 6;
        intentosRestantesJlabel.setText("Intentos restantes : " + intentos);
    }

    private void updateHiddenWord(char guess) {
        boolean found = false;
        for (int i = 0; i < palabraAdivinar.length(); i++) {
            if (palabraAdivinar.charAt(i) == guess) {
                palabraEscondida.setCharAt(i, guess);
                found = true;
            }
        }
        palabraEscondidaJlabel.setText(palabraEscondida.toString());
        if (!found) {
            intentos--;
            intentosRestantesJlabel.setText("Intentos restantes :" + intentos);
            if (intentos == 0) {
                finalJuego("Has perdido, la palabra era : " + palabraAdivinar);
            }
        } else if (palabraEscondida.toString().equals(palabraAdivinar)) {
            finalJuego("Te salvaste!");
        }
    }

    private void finalJuego(String message) {
        adivinarTextField.setEnabled(false);
        adivinarButton.setEnabled(false);
        JOptionPane.showMessageDialog(this, message, "Otro Juego", JOptionPane.INFORMATION_MESSAGE);
        IniciarJuego();
        adivinarTextField.setEnabled(true);
        adivinarButton.setEnabled(true);
        adivinarTextField.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adivinarButton || e.getSource() == adivinarTextField) {
            String guessText = adivinarTextField.getText();
            if (guessText.length() > 0) {
                char guess = guessText.charAt(0);
                updateHiddenWord(guess);
                adivinarTextField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ahorcado::new);
    }
}
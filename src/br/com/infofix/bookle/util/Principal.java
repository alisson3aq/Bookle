/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.util;

import br.com.infofix.bookle.interfaces.TelaLogin;

/** Esta é a classe principal do projeto que inicializa a janela de Login, contendo 
 * apenas a classe main.
 * @author Kelvin Santiago
 */
public class Principal {
    public static void main(String[] args){
        TelaLogin telalogin = new TelaLogin();
        telalogin.setVisible(true);
    }
}

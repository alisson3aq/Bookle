/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Esta classe é responsável por implementar a opcao de limitar o tamanho e
 * definir como entrada apenas integer.
 *
 * @author Kélvin Santiago
 */
public class JTextFieldInteiros extends JTextFieldTamanhoMaximo {

    /**
     * Construtor da classe define o tamanho máximo de caracteres de entrada no
     * componente, e é passado a classe superior JTextFieldTamanhoMaximo na qual
     * irá limitar o numero de caracteres de entrada.
     *
     * @param limit
     */
    public JTextFieldInteiros(int limit) {
        super(limit);
    }

    /**
     * Reescrita do método superior da herança na qual limita o componente a
     * apenas a entrada de numeros integers.
     *
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return;
        }

        super.insertString(offset, str, attr);
    }

}

package bookleprojeto;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{
    
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList linhas, String[] colunas){
        setLinhas(linhas);
        setColunas(colunas);
    
    }
    
    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    
    public String getColumnName(int numColuna){
        return colunas[numColuna];
    }
    
    public Object getValueAt(int numLinha, int numColuna){
        Object[] linha = (Object[])getLinhas().get(numLinha);
        return linha[numColuna];
    }
    
    public ArrayList getLinhas() {
        return linhas;
    }


    public void setLinhas(ArrayList dados) {
        linhas = dados;
    }

    public String[] getColunas() {
        return colunas;
    }


    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
  
}

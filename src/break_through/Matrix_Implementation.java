/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package break_through;

/**
 *
 * @author Munkhtenger
 */
public class Matrix_Implementation {

    private final ColorAux[][] mat;
    private final int matSize;
    
    /**
     * Constructor for Matrix_Implementation. Takes matrix size as argument and 
     * initialize a matrix of that given size
     * @param matSize 
     */
    public Matrix_Implementation(int matSize) {
        this.matSize = matSize;
        mat = new ColorAux[matSize][matSize];
        for (int i=0; i<matSize; ++i) {
            for (int j=0; j<matSize; ++j) {
                mat[i][j]=new ColorAux();
            }
        }
    }

    public int getMatSize() {
        return matSize;
    }
        
}

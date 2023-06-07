/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author PRV
 */
public class Utilidades {
    
    
    private static Usuario_EmpleadoEntity m_UsuarioSistema = new Usuario_EmpleadoEntity();

    /**
     * @return the m_UsuarioSistema
     */
    public static Usuario_EmpleadoEntity getM_UsuarioSistema() {
        return m_UsuarioSistema;
    }

    /**
     * @param aM_UsuarioSistema the m_UsuarioSistema to set
     */
    public static void setM_UsuarioSistema(Usuario_EmpleadoEntity aM_UsuarioSistema) {
        m_UsuarioSistema = aM_UsuarioSistema;
    }
    
    
    
    
    
}

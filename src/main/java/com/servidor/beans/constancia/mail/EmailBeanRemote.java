package com.servidor.beans.constancia.mail;

import javax.ejb.Remote;

import com.servidor.entidades.Constancia;
import com.servidor.entidades.Estudiante;
import com.servidor.entidades.Usuario;

@Remote
public interface EmailBeanRemote {
	
	public boolean enviarMail(Constancia oConstancia, Estudiante oEstudiante);
	
	public void enviarMailCuentaRegistrada(Usuario oUsuario);
	
	public void enviarMailCuentaConfirmada(Usuario oUsuario);
}

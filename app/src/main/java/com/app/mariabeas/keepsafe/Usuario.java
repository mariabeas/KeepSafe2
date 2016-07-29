package com.app.mariabeas.keepsafe;

/**
 * Created by MariaBeas on 16/2/16.
 */
public class Usuario {
        private String idUsuario;
        private String emailUsuario;
        private String passwordUsuario;
        private String nombreUsuario;
        private String apellidosUsuario;
        private String fechaNac;
        private String sexo;
        private String grupoSanguineo;
        private String numSeguridadSocial;
        //private String foto;

        public Usuario(String id, String mail, String nombre, String apellido, String fechaNac, String sexo, String sangre, String numSeguridad) {
            this.idUsuario=id;
            this.emailUsuario=mail;
            this.nombreUsuario=nombre;
            this.apellidosUsuario=apellido;
            this.fechaNac=fechaNac;
            this.sexo=sexo;
            this.grupoSanguineo=sangre;
            this.numSeguridadSocial=numSeguridad;
           // this.foto=foto;
        }

        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getEmailUsuario() {
            return emailUsuario;
        }

        public void setEmailUsuario(String emailUsuario) {
            this.emailUsuario = emailUsuario;
        }

        public String getPasswordUsuario() {
            return passwordUsuario;
        }

        public void setPasswordUsuario(String passwordUsuario) {
            this.passwordUsuario = passwordUsuario;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public String getApellidosUsuario() {
            return apellidosUsuario;
        }

        public void setApellidosUsuario(String apellidosUsuario) {
            this.apellidosUsuario = apellidosUsuario;
        }

        public String getFechaNac() {
            return fechaNac;
        }

        public void setFechaNac(String fechaNac) {
            this.fechaNac = fechaNac;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public String getGrupoSanguineo() {
            return grupoSanguineo;
        }

        public void setGrupoSanguineo(String grupoSanguineo) {
            this.grupoSanguineo = grupoSanguineo;
        }

        public String getNumSeguridadSocial() {
            return numSeguridadSocial;
        }

        public void setNumSeguridadSocial(String numSeguridadSocial) {
            this.numSeguridadSocial = numSeguridadSocial;
        }

        /*public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }*/
    }
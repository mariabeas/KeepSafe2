package com.app.mariabeas.keepsafe;

/**
 * Created by MariaBeas on 7/7/16.
 */
public class Telefono {
    private String nombreTfn;
    private int tfn;
    private int miniatura;

    public Telefono(){

    }
    public Telefono(String nombreTfn, int tfn,int miniatura){
        this.nombreTfn=nombreTfn;
        this.tfn=tfn;
        this.miniatura=miniatura;
    }

    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }



    public String getNombreTfn() {
        return nombreTfn;
    }

    public void setNombreTfn(String nombreTfn) {
        this.nombreTfn = nombreTfn;
    }

    public int getTfn() {
        return tfn;
    }

    public void setTfn(int tfn) {
        this.tfn = tfn;
    }


}

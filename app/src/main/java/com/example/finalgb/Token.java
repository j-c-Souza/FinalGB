package com.example.finalgb;

public class Token {
    private String data;
    private String hora;
    private String lat;
    private String lgn;
    private int pos;
    public Token(){

    }
    public Token(String data, String hora, String lat, String lgn){
        this.data = data;
        this.hora = hora;
        this.lat = lat;
        this.lgn = lgn;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLgn() {
        return lgn;
    }

    public void setLgn(String lgn) {
        this.lgn = lgn;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}

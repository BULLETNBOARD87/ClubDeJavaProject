/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

/**
 *
 * @author super
 */
public class GolfGameProperties {

    private int launches = 0;

    public void recordLaunch() {
        launches++;
    }

    public int getLaunches() {
        return launches;
    }

    public void reset() {
        launches = 0;
    }
}
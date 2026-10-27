/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author super
 */
public class GolfGameProperties {

    private final IntegerProperty launches = new SimpleIntegerProperty(0);

    public void recordLaunch() {
        launches.set(launches.get() + 1);
    }

    public int getLaunches() {
        return launches.get();
    }

    public IntegerProperty launchesProperty() {
        return launches;
    }

    public void reset() {
        launches.set(0);
    }
}
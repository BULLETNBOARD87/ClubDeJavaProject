/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

/**
 *
 * @author super
 */
public class BallComponent extends Component {
    
    private final double radius;
    private Point2D velocity = Point2D.ZERO;
    
    public BallComponent(double radius) {
        this.radius = radius;
    }

    public void launch(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getCenter() {
        return entity.getPosition().add(radius, radius);
    }
    
}

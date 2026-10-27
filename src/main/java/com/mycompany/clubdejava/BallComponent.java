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
    
    private static final double FRICTION = 0.7;  
    private static final double STOP_SPEED = 5.0;
    
    public BallComponent(double radius) {
        this.radius = radius;
    }

    public void launch(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getCenter() {        
        return entity.getPosition().add(radius, radius);
    }
    
    @Override
    public void onUpdate(double tpf) {
        if (velocity.magnitude() < STOP_SPEED) {
            velocity = Point2D.ZERO;
            return;
        }
        
        entity.translate(velocity.multiply(tpf));
        
        velocity = velocity.multiply(Math.max(0, 1 - FRICTION * tpf));
    }
    
}

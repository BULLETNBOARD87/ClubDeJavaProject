/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubdejava;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 *
 * @author super
 */
public class BallComponent extends Component {

    private final double radius;
    private PhysicsComponent physics;  

    private static final float DAMPING = 1.5f;   
    private static final double STOP_SPEED = 5.0;

    public BallComponent(double radius) {
        this.radius = radius;
    }

    @Override
    public void onAdded() {
        physics.setOnPhysicsInitialized(() -> {
            physics.getBody().setLinearDamping(DAMPING);
            physics.getBody().setAngularDamping(2.0f);
        });
    }

    public void launch(Point2D velocity) {
        physics.setLinearVelocity(velocity);
    }

    public Point2D getCenter() {
        return entity.getPosition().add(radius, radius);
    }

    @Override
    public void onUpdate(double tpf) {
        if (physics.isMoving() && physics.getLinearVelocity().magnitude() < STOP_SPEED) {
            physics.setLinearVelocity(Point2D.ZERO);
        }
    }
}

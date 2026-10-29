package com.mycompany.clubdejava;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;


public class GolfFactory implements EntityFactory {
    
        // KEEPING NOTES FOR WHAT COLOR IS WHAT: 
        public static final Color[] PLAYER_COLORS = {
        Color.web("#e53935"), // RED
        Color.web("#1e88e5"), // BLUE
        Color.web("#fdd835"), // YELLOW
        Color.web("#8e24aa") // PURPLE
    };

    
    @Spawns("floor")
    public Entity newFloor(SpawnData data) {
        
        Rectangle grass = new Rectangle(1100, 700, Color.web("#2fac52"));
        
        Circle fairway = new Circle(884, 167, 216, Color.web("#50fc7b"));
        
        Circle green = new Circle(957, 128, 39, Color.web("#42e16c"));
        
        Circle tee = new Circle(965, 571, 8, Color.web("#f8f8f8"));

        return entityBuilder(data)
                .type(EntityType.FLOOR)
                .view(new Group(grass, fairway, green, tee))
                .zIndex(-100)
                .build();
    }
    
       
    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        double width = data.get("width");
        double height = data.get("height");

        Rectangle wall = new Rectangle(width, height, Color.web("#fdcc8d"));
        
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(new FixtureDef().friction(0f).restitution(0.7f));

        return entityBuilder(data)
                .type(EntityType.WALL)
                .view(wall)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(physics)
                .collidable()
                .build();
    }
    
     
    @Spawns("goal")
    public Entity newGoal(SpawnData data) {
        Circle hole = new Circle(20, 20, 20, Color.BLACK);

        // flag pole going up from the hole, with a red flag at the top
        Line pole = new Line(20, 20, 20, -40);
        pole.setStroke(Color.DIMGRAY);
        pole.setStrokeWidth(3);
        Polygon flag = new Polygon(20, -40, 55, -30, 20, -20);
        flag.setFill(Color.RED);

        return entityBuilder(data)
                .type(EntityType.GOAL)
                .view(new Group(hole, pole, flag))
                .bbox(new HitBox(BoundingShape.circle(20)))
                .collidable()
                .build();
    }
    
        @Spawns("ball")
    public Entity newBall(SpawnData data) {
        Color color = data.get("color");

        Circle ball = new Circle(20, 20, 20, color);
        ball.setStroke(Color.BLACK);
        ball.setStrokeWidth(2);

        return entityBuilder(data)
                .type(EntityType.BALL)
                .view(ball)
                .bbox(new HitBox(BoundingShape.circle(20)))
                .collidable()
                .build();
    }
}
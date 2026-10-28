package com.mycompany.clubdejava;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;


public class GolfFactory implements EntityFactory {

    
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

        return entityBuilder(data)
                .type(EntityType.WALL)
                .view(wall)
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .collidable()
                .build();
    }
}
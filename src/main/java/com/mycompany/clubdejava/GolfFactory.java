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
import javafx.scene.shape.StrokeType;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

import com.almasb.fxgl.entity.components.CollidableComponent;


public class GolfFactory implements EntityFactory {
    
    public static final double BALL_RADIUS = 10;
    
        // KEEPING NOTES FOR WHAT COLOR IS WHAT: 
        public static final Color[] PLAYER_COLORS = {
        Color.web("#e53935"), // RED
        Color.web("#1e88e5"), // BLUE
        Color.web("#fdd835"), // YELLOW
        Color.web("#8e24aa") // PURPLE
    };

    
    @Spawns("floor")
    public Entity newFloor(SpawnData data) {
        
        double holeX = data.get("holeX");
        double holeY = data.get("holeY");
        double teeX = data.get("teeX");
        double teeY = data.get("teeY");

        Group floor = new Group();
        floor.getChildren().add(new Rectangle(1100, 700, Color.web("#2e9b4b")));

        // MAKES IT A LITTLE LIKE IRL GOLF WITH THE STRIPED GRASS
        
        for (int x = 0; x < 1100; x += 110) {
            Rectangle stripe = new Rectangle(x, 0, 55, 700);
            stripe.setFill(Color.web("#34a853"));
            floor.getChildren().add(stripe);
        }

        Circle fairway = new Circle(holeX, holeY, 216, Color.web("#4cc46a"));

        Circle green = new Circle(holeX, holeY, 60, Color.web("#6fdc8c"));
        green.setStroke(Color.web("#3fae5c"));
        green.setStrokeWidth(3);

        Rectangle teeBox = new Rectangle(teeX - 28, teeY - 20, 56, 40);
        teeBox.setArcWidth(12);
        teeBox.setArcHeight(12);
        teeBox.setFill(Color.web("#6fdc8c"));
        Circle tee = new Circle(teeX, teeY, 5, Color.web("#f8f8f8"));
        
        floor.getChildren().addAll(fairway, green, teeBox, tee);

        return entityBuilder(data)
                .type(EntityType.FLOOR)
                .view(floor)
                .zIndex(-100)
                .build();
    }
    
       
    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        double width = data.get("width");
        double height = data.get("height");

        Rectangle wall = new Rectangle(width, height, Color.web("#f3c98f"));
        
        // FIGURED OUT ROUNDED CORNERS WITH AN ACTUAL BORDER DO NOT REMOVE THSI SECTION
        wall.setArcWidth(10);
        wall.setArcHeight(10);
        wall.setStroke(Color.web("#c99157"));
        wall.setStrokeWidth(3);
        wall.setStrokeType(StrokeType.INSIDE);
        
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
        Circle hole = new Circle(20, 20, 20, Color.web("#1a1a1a"));
        hole.setStroke(Color.web("#e8e8e8"));
        hole.setStrokeWidth(3);

        // flag pole going up from the hole, with a red flag at the top
        Line pole = new Line(20, 20, 20, -40);
        pole.setStroke(Color.WHITE);
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

        Circle ball = new Circle(BALL_RADIUS, BALL_RADIUS, BALL_RADIUS, color);
        ball.setStroke(Color.BLACK);
        ball.setStrokeWidth(2);

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef()
                .density(1.0f)
                .friction(0f)
                .restitution(0.7f));

        Entity entity = entityBuilder(data)
                .type(EntityType.BALL)
                .view(ball)
                .bbox(new HitBox(BoundingShape.circle(BALL_RADIUS)))
                .with(physics)
                .with(new BallComponent(BALL_RADIUS))
                .collidable()
                .build();

        
        entity.getComponent(CollidableComponent.class).addIgnoredType(EntityType.BALL);

        return entity;
    }
}

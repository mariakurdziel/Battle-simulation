package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.SetTypes.ArmyTypes;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SimulationScreen extends Application {


    public Group rootx = new Group();
    Stage stage = new Stage();
    Army armyBlue;
    Army armyRed;
    PredictingFight predictions = new PredictingFight();

    public void setArmyBlue(Army armyBlue) {
        this.armyBlue = armyBlue;
    }

    public void setArmyRed(Army armyRed) {
        this.armyRed = armyRed;
    }

    public String setLanchesterEquation(Army armyBlue, Army armyRed){
        int whoWins = predictions.lanchesterEquation(armyBlue,armyRed);
        if(whoWins == 1) return armyBlue.getWarrior();
        else if(whoWins == 0) return "Draw";
        else if(whoWins == -1) return armyRed.getWarrior();
        else return null;
    }

    public String setAttribute(TextHandler text){
        Army x;
        if(text.getType().equals("B"))
            x = armyBlue;
        else
            x = armyRed;

        if(text.getAttribute().equals("Number"))
            return String.valueOf((x.getNumber()));
        else if (text.getAttribute().equals("Warrior"))
            return x.getWarrior();
        else if (text.getAttribute().equals("Armor"))
            return x.getArmor();
        else if (text.getAttribute().equals("Weapon"))
            return x.getWeapon();
        else if (text.getAttribute().equals("HealthPoints"))
            return String.valueOf((x.getHealthPoints()));
        else if (text.getAttribute().equals("Morale"))      // TODO zeby to zmienic i od razu tez dac mozliwosc wyboru ilosci grupy
            return String.valueOf((x.getSquads().size())); // TODO morale jest na grupe -> narazie wrzucona ilosc grup
        else if (text.getAttribute().equals("Agility"))
            return String.valueOf((x.getAgility())).substring(0,3);
        else if (text.getAttribute().equals("Atak"))
            return String.valueOf((x.getAttack())).substring(0,3);
        else if (text.getAttribute().equals("Speed"))
            return String.valueOf((x.getAttackSpeed())).substring(0,3);
        else if (text.getAttribute().equals("ArmorStats"))
            return String.valueOf((x.getArmorStats())).substring(0,3);
        else if (text.getAttribute().equals("WhoWon"))
            return String.valueOf(setLanchesterEquation(armyBlue,armyRed));
        else
            return null;
    }

    private void drawShape(GraphicsContext gc) {

        gc.setFill(Color.BLUE);
        gc.fillRect(950, 120, 25, 25);

        gc.setFill(Color.RED);
        gc.fillRect(950, 400, 25, 25);
    }

    public void addTitle(){
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text t = new Text("Battle Simulation");
        t.setEffect(ds);
        t.setCache(true);
        t.setX(450);
        t.setY(70);
        t.setFill(Color.BLUE);
        t.setFont(Font.font(null, FontWeight.BOLD, 32));

        t.setTextAlignment(TextAlignment.CENTER);
        rootx.getChildren().addAll(t);
    }

    public void addInfos(){
        for (TextHandler text : TextHandler.values()){

            Text t = new Text(text.getText() + setAttribute(text));
            t.setX(text.getLayoutX());
            t.setY(text.getLayoutY());
            t.setFont(Font.font(text.getFont()));
            rootx.getChildren().addAll(t);
        }

        Canvas canvas = new Canvas(1200, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShape(gc);
        rootx.getChildren().add(canvas);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void printArmyBlue(){
        int x;
        int y;
        int widthY = 15;
        double radius = 5.0;

        for (int i=0; i < armyBlue.getSquads().size(); i++) {
            x = armyBlue.getSquads().get(i).getX();
            y = armyBlue.getSquads().get(i).getY();
            Circle c1 = new Circle(x+100, y+200, radius);
            if((i % 30)==0){
                x = 0;
                y -= widthY;
            }
            c1.setStroke(Color.BLUE);
            c1.setFill(Color.BLUE);
            c1.setStrokeWidth(1);
            rootx.getChildren().add(c1);
        }
    }

    public void printArmyRed(){
        int x;
        int y;
        int widthY = 15;
        double radius = 5.0;

        for (int i=0; i < armyRed.getSquads().size(); i++) {
            x = armyRed.getSquads().get(i).getX();
            y = armyRed.getSquads().get(i).getY();
            Circle c1 = new Circle(x+100, y+700, radius);
            if((i % 30)==0){
                x = 0;
                y -= widthY;
            }
            c1.setStroke(Color.RED);
            c1.setFill(Color.RED);
            c1.setStrokeWidth(1);
            rootx.getChildren().add(c1);
        }
    }

    public void update() {
        printArmyBlue();
        printArmyRed();
        int temp = predictions.whoWon;
        predictions.lanchesterEquation(armyBlue, armyRed);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

            final Scene scene = new Scene(rootx, 1200, 800);
            update();
            addTitle();
            addInfos();
            stage.setScene(scene);
            stage.setTitle("Simulation");
            stage.show();}

}



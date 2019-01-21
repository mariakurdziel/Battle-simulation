package sample;

public class Squads {

    private int x;
    private int y;
    private int population;
    private double health;
    private double agility;
    private double armorStats;
    private double attack;
    private double attackSpeed;

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getArmorStats() {
        return armorStats;
    }

    public void setArmorStats(double armorStats) {
        this.armorStats = armorStats;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    private double morale = 1;

    Squads(int x, int y, int population, double health){
        this.x = x;
        this.y = y;
        this.population = population;
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMorale() {
        return morale;
    }

    public void setMorale(double morale) {
        this.morale = morale;
    }


}

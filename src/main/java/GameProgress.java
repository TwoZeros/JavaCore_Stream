import java.io.Serializable;
import java.util.Objects;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameProgress that = (GameProgress) o;
        return health == that.health && weapons == that.weapons && lvl == that.lvl && Double.compare(that.distance, distance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, weapons, lvl, distance);
    }
}
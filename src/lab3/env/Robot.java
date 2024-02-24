package lab3.env;

import lab3.util.Pos;

public class Robot {

    private final Map map;
    private Pos pos;

    public Robot(Map map, Pos pos) {
        this.map = map;
        setPos(pos);
    }

    public void setPos(Pos pos) {

        if (!map.isInsideMap(pos))
        {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        if (map.isWall(pos))
            throw new IllegalArgumentException("Try to set robot to wall");

        this.pos = pos;
    }

    public Pos getPos() {
        return pos;
    }

}

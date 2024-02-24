package lab3.env;

import lab3.util.Pos;

public class Flag {

    private final Map map;
    private Pos pos;

    public Flag(Map map, Pos pos) {
        this.map = map;
        setPos(pos);
    }

    public void setPos(Pos pos) {

        if (pos.x < 0 || pos.x >= map.getWidth() ||
                pos.y < 0 || pos.y >= map.getHeight())
        {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        if (map.isWall(pos))
            throw new IllegalArgumentException("Try to set flag to wall");

        this.pos = pos;
    }

    public Pos getPos() {
        return pos;
    }
}

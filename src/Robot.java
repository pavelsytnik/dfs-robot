public class Robot {

    private final Map map;
    private int x, y;

    public Robot(Map map, int x, int y) {
        this.map = map;
        setPos(x, y);
    }

    public void setPos(int x, int y) {

        if (x < 0 || x >= map.getWidth() ||
            y < 0 || y >= map.getHeight())
        {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }

        if (map.isWall(x, y))
            throw new IllegalArgumentException("Try to set robot to wall");

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

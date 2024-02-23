public class Main {
    public static void main(String[] args) {
        var map = new Map("res/map");
        var robot = new Robot(map, 2, 1);
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (map.isWall(x, y)) {
                    System.out.print("# ");
                } else if (robot.getX() == x && robot.getY() == y) {
                    System.out.print("@ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}

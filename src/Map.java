import java.io.*;

public class Map {

    private boolean[][] map;
    private int height;
    private int width;

    public Map(String fileName) {
        try (var br = new BufferedReader(new FileReader(fileName))) {
            String[] size = br.readLine().split(" ");
            width = Integer.parseInt(size[0]);
            height = Integer.parseInt(size[1]);
            map = new boolean[height][width];

            for (int y = 0; y < height; y++) {
                char[] buf = br.readLine().trim().toCharArray();
                if (buf.length != width)
                    throw new RuntimeException();
                for (int x = 0; x < width; x++) {
                    if (buf[x] == '#') {
                        map[y][x] = true;
                    } else if (buf[x] == '.') {
                        map[y][x] = false;
                    } else {
                        throw new RuntimeException("Illegal symbol: " + (int) buf[x]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No file with name " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isWall(Pos pos) {
        return map[pos.y][pos.x];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

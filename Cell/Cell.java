package Cell;

public class Cell {
    public int x;
    public int y;
    public int n;

    public Cell(int n) {
        this.n = n;
        this.setCoordinates(n);
    }

    // find cell coordinates according to an integer n [1-9]
    public void setCoordinates(int n) {
        this.n = n;
        switch (n) {
            case 1 -> {
                this.x = 0;
                this.y = 0;
            }
            case 2 -> {
                this.x = 0;
                this.y = 1;
            }
            case 3 -> {
                this.x = 0;
                this.y = 2;
            }
            case 4 -> {
                this.x = 1;
                this.y = 0;
            }
            case 5 -> {
                this.x = 1;
                this.y = 1;
            }
            case 6 -> {
                this.x = 1;
                this.y = 2;
            }
            case 7 -> {
                this.x = 2;
                this.y = 0;
            }
            case 8 -> {
                this.x = 2;
                this.y = 1;
            }
            case 9 -> {
                this.x = 2;
                this.y = 2;
            }
        }
    }
}

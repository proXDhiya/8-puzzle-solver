public class Board {
    private int[] table = new int[9];

    // -------------
    // | 0 | 1 | 2 |
    // -------------
    // | 3 | 4 | 5 |
    // -------------
    // | 6 | 7 | 8 |
    // -------------


    public Board(String str) {
        for (int i = 0; i < 9; i++)
            this.table[i] = Character.getNumericValue(str.charAt(i));
    }


    public Board(Board board) {
        for (int i = 0; i < 9; i++)
            this.table[i] = board.table[i];
    }


    public void moveUp() {
        for (int i = 0; i < 9; i++)
            if (this.table[i] == 0)
                if (i > 2) {
                    this.table[i] = this.table[i - 3];
                    this.table[i - 3] = 0;
                    return;
                } else
                    return;
    }


    public void moveDown() {
        for (int i = 0; i < 9; i++)
            if (this.table[i] == 0)
                if (i < 6) {
                    this.table[i] = this.table[i + 3];
                    this.table[i + 3] = 0;
                    return;
                } else
                    return;
    }


    public void moveLeft() {
        for (int i = 0; i < 9; i++)
            if (this.table[i] == 0)
                if (i % 3 != 0) {
                    this.table[i] = this.table[i - 1];
                    this.table[i - 1] = 0;
                    return;
                } else
                    return;
    }


    public void moveRight() {
        for (int i = 0; i < 9; i++)
            if (this.table[i] == 0)
                if (i % 3 != 2) {
                    this.table[i] = this.table[i + 1];
                    this.table[i + 1] = 0;
                    return;
                } else
                    return;
    }


    public String toString() {
        String str = "";
        for (int i = 0; i < 9; i++)
            str += this.table[i];
        return str;
    }


    public boolean isSolved() {
        for (int i = 0; i < 9; i++)
            if (this.table[i] != i)
                return false;
        return true;
    }


    public boolean isEqual(Board board) {
        for (int i = 0; i < 9; i++)
            if (this.table[i] != board.table[i])
                return false;
        return true;
    }
}

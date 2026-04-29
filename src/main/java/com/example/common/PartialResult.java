package com.example.common;

import java.io.Serializable;

public class PartialResult implements Serializable {
    private final int startRow;
    private final int[][] rows;

    public PartialResult(int startRow, int[][] rows) {
        this.startRow = startRow;
        this.rows = rows;
    }

    public int getStartRow() {
        return startRow;
    }

    public int[][] getRows() {
        return rows;
    }
}

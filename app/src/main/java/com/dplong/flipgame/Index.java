package com.dplong.flipgame;

import androidx.annotation.Nullable;

public class Index {

    public int x, y;

    public Index() {
        this.x = 0;
        this.y = 0;
    }

    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Reset() {
        this.x = 0;
        this.y = 0;
    }

    public boolean equals(Index obj) {
        if (obj.x == this.x && obj.y == this.y) {
            return true;
        }
        return false;
    }
}

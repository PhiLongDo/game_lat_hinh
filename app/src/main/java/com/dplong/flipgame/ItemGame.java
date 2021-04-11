package com.dplong.flipgame;

import android.widget.Button;

public class ItemGame {
    private Button btn_ixj;
    private String value;

    public ItemGame(Button btn_ixj) {
        this.btn_ixj = btn_ixj;
    }

    public ItemGame(Button btn_ixj, String value) {
        this.btn_ixj = btn_ixj;
        this.value = value;
    }

    public Button getBtn_ixj() {
        return btn_ixj;
    }

    public void setBtn_ixj(Button btn_ixj) {
        this.btn_ixj = btn_ixj;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

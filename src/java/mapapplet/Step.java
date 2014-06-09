/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import utils.*;

/**
 *
 * @author orcl
 */
public class Step {

    int id;
    int x;
    int y;

    public Step() {
    }

    public Step(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

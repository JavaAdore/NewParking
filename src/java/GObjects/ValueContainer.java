/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import java.io.Serializable;

/**
 *
 * @author orcl
 */
public class ValueContainer implements Serializable{

    private double value;

    public ValueContainer(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}

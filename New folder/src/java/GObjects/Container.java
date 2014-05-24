/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GObjects;

import java.util.ArrayList;
import pojo.Garage;

/**
 *
 * @author orcl
 */
public class Container {
    
    ArrayList<pojo.Garage> list;

    public ArrayList<Garage> getList() {
        return list;
    }

    public Container setList(ArrayList<Garage> list) {
        this.list = list;
        return this;
    }
}

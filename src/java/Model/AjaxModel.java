/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author MITICC06
 */
public class AjaxModel {
    
    public AjaxModel(int value, String label) {
        this.value = value;
        this.label = label;
    }
  
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel1() {
        return label;
    }

    public void setLabel1(String label1) {
        this.label = label;
    }
    private int value;
    private String label;
}
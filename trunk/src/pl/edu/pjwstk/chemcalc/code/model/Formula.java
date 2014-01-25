package pl.edu.pjwstk.chemcalc.code.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class which will store the Formula Items
 * 
 * @author Adrian Bala
 */
@Entity
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String chemform;
    private String result;
    private Double mass;

    public Formula() {
    }

    public Formula(String chemform, String result, Double mass) {
        this.chemform = chemform;
        this.result = result;
        this.mass = mass;
    }

    public String getChemform() {
        return chemform;
    }

    public void setChemform(String chemform) {
        this.chemform = chemform;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }
}
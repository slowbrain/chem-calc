package pl.edu.pjwstk.chemcalc.code.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class which will store the Element Items
 * 
 * @author Adrian Bala
 */
@Entity
public class ChemicalElement implements Comparable<ChemicalElement> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String symbol;
    private String name;
    private Integer atomicnumber;
    private Double atomicmass;

    public ChemicalElement() {
    }

    public ChemicalElement(Integer atomicnumber, Double atomicmass,
            String symbol, String name) {
        this.atomicnumber = atomicnumber;
        this.atomicmass = atomicmass;
        this.symbol = symbol;
        this.name = name;
    }

    public Integer getAtomicnumber() {
        return atomicnumber;
    }

    public void setAtomicnumber(Integer atomicnumber) {
        this.atomicnumber = atomicnumber;
    }

    public Double getAtomicmass() {
        return atomicmass;
    }

    public void setAtomicmass(Double atomicmass) {
        this.atomicmass = atomicmass;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ChemicalElement o) {
        return atomicnumber.compareTo(o.getAtomicnumber());
    }
}
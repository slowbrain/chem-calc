package pl.edu.pjwstk.chemcalc.code.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.edu.pjwstk.chemcalc.code.dao.EMFService;

/**
 * Model class which will store the PeriodicTable Items
 * 
 * @author Adrian Bala
 */
public class PeriodicTable {
    private Map<String, ChemicalElement> ptable = new HashMap<String, ChemicalElement>();

    @SuppressWarnings("unchecked")
    public PeriodicTable() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select chel from ChemicalElement chel");
        List<ChemicalElement> chemelems = q.getResultList();
        this.ptable = new PeriodicTable(chemelems).getPtable();
    }

    public PeriodicTable(Map<String, ChemicalElement> ptable) {
        this.ptable = ptable;
    }

    public PeriodicTable(List<ChemicalElement> table) {
        for (ChemicalElement chel : table) {
            this.ptable.put(chel.getSymbol(), chel);
        }
    }

    public Map<String, ChemicalElement> getPtable() {
        return ptable;
    }

    public void setPtable(Map<String, ChemicalElement> ptable) {
        this.ptable = ptable;
    }

    public ChemicalElement getChemicalElement(String symbol) {
        return ptable.get(symbol);
    }

    public List<ChemicalElement> getChemicalElements() {
        List<ChemicalElement> chemelems = new ArrayList<ChemicalElement>();
        for (String symbol : ptable.keySet()) {
            chemelems.add(ptable.get(symbol));
        }
        return chemelems;
    }

    public void addChemicalElement(ChemicalElement chel) {
        ptable.put(chel.getSymbol(), chel);
    }

    public void delChemicalElement(ChemicalElement chel) {
        ptable.remove(chel.getSymbol());
    }

    public void exchangeChemicalElement(ChemicalElement chel1,
            ChemicalElement chel2) {
        ptable.remove(chel1.getSymbol());
        ptable.put(chel2.getSymbol(), chel2);
    }

    public void initPeriodicTable(List<ChemicalElement> table) {
        this.ptable = new HashMap<String, ChemicalElement>();
        for (ChemicalElement chel : table) {
            this.ptable.put(chel.getSymbol(), chel);
        }
    }
}
package pl.edu.pjwstk.chemcalc.code.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.edu.pjwstk.chemcalc.code.datastore.SBAStore;
import pl.edu.pjwstk.chemcalc.code.interpreter.envs.Interpreter;
import pl.edu.pjwstk.chemcalc.code.model.ChemicalElement;
import pl.edu.pjwstk.chemcalc.code.model.Formula;
import pl.edu.pjwstk.chemcalc.code.model.PeriodicTable;
import pl.edu.pjwstk.chemcalc.code.parser.ChemcalcParser;
import pl.edu.pjwstk.chemcalc.code.result.IAbstractQueryResult;

public enum Dao {
    INSTANCE;

    private PeriodicTable periodictable = new PeriodicTable();
    private Interpreter interpreter = new Interpreter(new SBAStore());

    public boolean checkFormula(String chemform) {
        boolean b = false;
        if (chemform != "") {
            EntityManager em = EMFService.get().createEntityManager();
            Query q = em
                    .createQuery("select f from Formula f where f.chemform = :chemform");
            q.setParameter("chemform", chemform);
            b = 0 < q.getResultList().size();
        }
        return b;
    }

    public void addFormula(String chemform) {
        synchronized (this) {
            if ((chemform != null) && (!checkFormula(chemform))) {
                try {
                    ChemcalcParser parser = new ChemcalcParser(chemform);
                    parser.user_init();
                    parser.parse();
                    IAbstractQueryResult result = interpreter
                            .eval(parser.RESULT);

                    Formula formula = new Formula(chemform, result.toString(),
                            result.getMass());

                    EntityManager em = EMFService.get().createEntityManager();
                    em.persist(formula);
                    em.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public Formula getFormula(String chemform) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            Formula formula = null;
            if ((chemform != null) && (checkFormula(chemform))) {
                formula = em.find(Formula.class, chemform);
            }
            return formula;
        } finally {
            em.close();
        }
    }

    public Formula calcFormula(String chemform) {
        Formula formula = null;
        synchronized (this) {
            try {
                if (chemform != null) {
                    if (!checkFormula(chemform)) {
                        ChemcalcParser parser = new ChemcalcParser(chemform);
                        parser.user_init();
                        parser.parse();
                        IAbstractQueryResult result = interpreter
                                .eval(parser.RESULT);

                        formula = new Formula(chemform, result.toString(),
                                result.getMass());

                        EntityManager em = EMFService.get()
                                .createEntityManager();
                        em.persist(formula);
                        em.close();
                    } else {
                        formula = getFormula(chemform);
                    }
                }
            } catch (Exception e) {
                // System.err.println("Error");
                formula = null;
            }
        }
        return formula;
    }

    @SuppressWarnings("unchecked")
    public List<Formula> getFormulas(String chemform) {
        List<Formula> formulas = null;
        if (chemform != null) {
            EntityManager em = EMFService.get().createEntityManager();
            Query q = em
                    .createQuery("select f from Formula f where f.chemform = :chemform");
            q.setParameter("chemform", chemform);
            formulas = q.getResultList();
        }
        return formulas;
    }

    public void removeFormula(String chemform) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
            if ((chemform != null) && (checkFormula(chemform))) {
                Formula formula = em.find(Formula.class, chemform);
                em.remove(formula);
            }
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Formula> listFormulas() {
        EntityManager em = EMFService.get().createEntityManager();
        // read the existing entries
        Query q = em.createQuery("select f from Formula f");
        List<Formula> formulas = q.getResultList();
        return formulas;
    }

    public void addChemicalElement(ChemicalElement chel) {
        synchronized (this) {
            try {
                EntityManager em = EMFService.get().createEntityManager();
                em.persist(chel);
                em.close();
            } catch (Exception e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<ChemicalElement> listChemicalElements() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select chel from ChemicalElement chel");
        List<ChemicalElement> chemelems = q.getResultList();
        return chemelems;
    }

    public PeriodicTable getPeriodictable() {
        return periodictable;
    }

    public void uploadPeriodicTable() {
        periodictable.initPeriodicTable(listChemicalElements());
    }
}
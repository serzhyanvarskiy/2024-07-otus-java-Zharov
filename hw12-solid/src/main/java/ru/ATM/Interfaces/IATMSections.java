package ru.ATM.Interfaces;

import java.util.Set;

public interface IATMSections <S>{
    int addNewSection(IATMItem<S> section);

    int removeSection(IATMItem<Integer> section);

    //    int removeSection(IATMItem<S> section);
    int putInSection(IATMItem<S> section, int count);
    int getInSection(IATMItem<S> section, int count);
    S getCountInSection(IATMItem<S> section);
    S getBalanceInSection(IATMItem<S> section);
    S getBalance();
    Set<IATMItem<S>> getAllSections();
    IATMItem<S> getMinSection();
}

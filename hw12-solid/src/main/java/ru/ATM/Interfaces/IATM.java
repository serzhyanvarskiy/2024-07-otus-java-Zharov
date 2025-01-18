package ru.ATM.Interfaces;

public interface IATM <S>{
    S getBalance();
    S put(IATMSections<S> packet);
    IATMSections<S> get(S s);

}

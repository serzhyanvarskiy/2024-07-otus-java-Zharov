package ru.ATM.Cash;

public class Banknote implements ru.ATM.Interfaces.IATMItem<Integer>{
    private String name="unknow";
    private int size=0;
    public Banknote(int size, String name) {
        this.size=size;
        this.name=name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

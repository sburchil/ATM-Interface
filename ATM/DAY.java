package ATM;

enum DAY {
    MONDAY (true),
    TUESDAY (true),
    WEDNESDAY (true),
    THURSDAY (true),
    FRIDAY (true),
    SATURDAY (false),
    SUNDAY (false);

    boolean open;
    DAY(boolean open){
        this.open = open;
    }

    protected boolean open(){return open;}


}

package ATM;

enum DAY {
    MONDAY (true),
    TUESDAY (true),
    WEDNESDAY (true),
    THURSDAY (true),
    FRIDAY (true),
    SATURDAY (false),
    SUNDAY (false);

    final boolean open;
    DAY(boolean open){
        this.open = open;
    }


}

package ru.geekbrains.android1.hw1.events;

public class FragmentBtnClickedEvent {
    public int whichFragment = 0;

    public FragmentBtnClickedEvent(int whichFragment) {
        this.whichFragment = whichFragment;
    }
}

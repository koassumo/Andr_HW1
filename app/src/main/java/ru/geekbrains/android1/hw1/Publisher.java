package ru.geekbrains.android1.hw1;

import java.util.ArrayList;
import java.util.List;

class Publisher {
    private static Publisher instance = null;
    private List<IObserver> observers = new ArrayList<>();   // Все обозреватели

    private Publisher() {}

    static Publisher getInstance() {
        if(instance == null) {
            instance = new Publisher();
        }

        return instance;
    }

    // Подписать
    void subscribe(IObserver observer) {
        observers.add(observer);
    }

    // Отписать
    void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    // Разослать событие
    void notify(String text) {
        for (IObserver observer : observers) {
            observer.updateText(text);
        }
    }
}

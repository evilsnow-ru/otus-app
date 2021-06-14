package ru.evilsnow.otususers;

public interface Mapper<S, T> {

    T mapTo(S source);
    S mapFrom(T target);

}

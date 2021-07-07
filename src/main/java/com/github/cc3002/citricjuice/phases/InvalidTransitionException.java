package com.github.cc3002.citricjuice.phases;

/**
 * Esta clase corresponde a una excepcion que se genera cuando
 * no se puede pasar de un estado a otro en el juego
 */
public class InvalidTransitionException extends Exception {
    public InvalidTransitionException(String message) {
        super(message);
    }
}

package com.lapots.breed.quest.component.observer;

/**
 * Controls the state of the component content.
 * @param <T> content type of the component
 */
public interface ContentObserver<T> {
    /**
     * By contract it expects some kind of refresh of the content
     * that does not require direct data input. For example if the
     * component consumes the data from some resource it should
     * do that for this event.
     *
     * Doing nothing by default.
     */
    default void refreshContent() {
    }
    /**
     * By contract it expects to update the content of the
     * component with the provided input.
     *
     * Doing nothing by default.
     *
     * @param content content for component
     */
    default void refreshContent(T content) {
    }
}

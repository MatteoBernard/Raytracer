package fr.univartois.raytracing.shadow;

/**
 * This interface represents the state of shadows in the ray tracing system. Implementations
 * of this interface can represent different states of shadow rendering, such as enabled or disabled shadows.
 */
public interface ShadowState {

    /**
     * Check if shadows are enabled.
     *
     * @return true if shadows are enabled, false otherwise.
     */
    boolean areShadowsEnabled();
}

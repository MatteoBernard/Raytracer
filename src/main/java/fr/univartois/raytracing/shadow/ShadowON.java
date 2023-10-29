package fr.univartois.raytracing.shadow;

/**
 * Implementation of the ShadowState interface that represents the state where shadows are enabled.
 */
public class ShadowON implements ShadowState {

    private static ShadowON instance;

    private ShadowON() {}

    /**
     * Get the singleton instance of ShadowON.
     *
     * @return The ShadowON instance.
     */
    public static ShadowON getInstance() {
        if (instance == null) {
            instance = new ShadowON();
        }
        return instance;
    }

    /**
     * Check if shadows are enabled.
     *
     * @return true.
     */
    @Override
    public boolean areShadowsEnabled() {
        return true; // Shadows are enabled
    }
}

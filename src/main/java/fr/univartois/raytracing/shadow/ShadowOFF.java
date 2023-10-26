package fr.univartois.raytracing.shadow;

/**
 * Implementation of the ShadowState interface that represents the state where shadows are disabled.
 */
public class ShadowOFF implements ShadowState {

    private static ShadowOFF instance;

    private ShadowOFF() {}

    /**
     * Get the singleton instance of ShadowOFF.
     *
     * @return The ShadowOFF instance.
     */
    public static ShadowOFF getInstance() {
        if (instance == null) {
            instance = new ShadowOFF();
        }
        return instance;
    }

    /**
     * Check if shadows are enabled.
     *
     * @return false.
     */
    @Override
    public boolean areShadowsEnabled() {
        return false; // Shadows are disabled
    }
}

package fr.univartois.raytracing.shadow;

public class ShadowON implements ShadowState {

    private static ShadowON instance;

    private ShadowON() {};

    public static ShadowON getInstance() {
        if (instance == null)
            instance =  new ShadowON();
        return instance;
    }
}

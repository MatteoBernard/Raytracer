package fr.univartois.raytracing.shadow;

public class ShadowOFF implements ShadowState {

    private static ShadowOFF instance;

    private ShadowOFF() {};

    public static ShadowOFF getInstance() {
        if (instance == null)
            instance =  new ShadowOFF();
        return instance;
    }
}

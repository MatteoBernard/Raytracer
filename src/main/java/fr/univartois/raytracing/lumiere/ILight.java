package fr.univartois.raytracing.lumiere;

public interface ILight {
    Triplet getCoord();

    Color getColor();
}

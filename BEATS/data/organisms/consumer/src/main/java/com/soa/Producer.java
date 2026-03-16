package com.soa;

abstract class Producer extends Organism {
    public Producer(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax);
    }

    float energyDecayRate() {
        return 0.1f;
    }

    // what this organism specifically eat
    boolean canConsume(Organism target) {
        return false;
    }
}

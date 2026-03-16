package com.soa;

final class Shark extends Consumer{
    public Shark(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float hungerThreshold, float visionRadius, float speed, float deltaTime, float attackRadius, float addEnergy) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax, hungerThreshold, visionRadius, speed, deltaTime, attackRadius, addEnergy);
    }

    @Override
    public float energyDecayRate() {
        return 0.3f;
    }

    @Override
    public boolean canConsume(Organism other) {
        return other instanceof Sardine || other instanceof Producer;
    }
}

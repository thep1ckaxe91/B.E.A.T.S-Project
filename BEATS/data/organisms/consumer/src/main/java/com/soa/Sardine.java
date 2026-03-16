package com.soa;

public class Sardine extends Consumer {
    public Sardine(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float hungerThreshold, float visionRadius, float speed, float deltaTime, float attackRadius, float addEnergy) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax, hungerThreshold, visionRadius, speed, deltaTime, attackRadius, addEnergy);
    }

    @Override
    public float energyDecayRate() {
        return 0.2f;
    }

    @Override
    boolean canConsume(Organism other) {
        return other instanceof Producer;
    }

    public void schoolingBehaviour() {
        // alignment
        // cohesion
        // separation
    }

    public void avoidShark(Shark shark) {
        float dx = this.x - shark.x;
        float dy = this.y - shark.y;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) return;

        if (distance < this.visionRadius) {
            // Increase speed
            float escapeSpeed = speed * 2.0f;

            this.velocityX += (dx / distance) * escapeSpeed;
            this.velocityY += (dy / distance) * escapeSpeed;

            updatePosition();
        }
    }
}

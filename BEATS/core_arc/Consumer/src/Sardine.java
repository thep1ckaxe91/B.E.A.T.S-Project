final class Sardine extends Consumer {
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

    public Shark findNearbyShark(java.util.List<Organism> organisms) {
        Shark nearestShark = null;
        float minDistanceSq = Float.MAX_VALUE;

        for (Organism o : organisms) {
            if (o instanceof Shark) {
                float dx = o.x - this.x;
                float dy = o.y - this.y;
                float distSq = dx * dx + dy * dy;

                // chỉ quan tâm trong visionRadius
                if (distSq < visionRadius * visionRadius && distSq < minDistanceSq) {
                    minDistanceSq = distSq;
                    nearestShark = (Shark) o;
                }
            }
        }

        return nearestShark;
    }

    public boolean avoidShark(Shark shark) {
        float dx = this.x - shark.x;
        float dy = this.y - shark.y;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) return false;

        if (distance > this.visionRadius) return false;

        float escapeSpeed = speed * 2.0f;
        this.velocityX = (dx / distance) * escapeSpeed;
        this.velocityY = (dy / distance) * escapeSpeed;

        return true;
    }

    @Override
    public void update(java.util.List<Organism> organisms) {
        if (isDead) return;
        updateBiologicalState();

        Shark shark = findNearbyShark(organisms);

        if (shark != null && avoidShark(shark)) {
            updatePosition();
            return;
        }

        super.update(organisms);
    }
}

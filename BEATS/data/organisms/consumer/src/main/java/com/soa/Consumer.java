package com.soa;

abstract class Consumer extends Organism {
    float hungerThreshold;
    float visionRadius;
    float speed;
    State state;
    float attackRadius;
    float addEnergy;

    Consumer(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float hungerThreshold, float visionRadius, float speed, float deltaTime, float attackRadius, float addEnergy) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax, deltaTime);
        this.hungerThreshold = hungerThreshold;
        this.visionRadius = visionRadius;
        this.speed = speed;
        state = State.CRUISE;
        this.attackRadius = attackRadius;
        this.addEnergy = addEnergy;
    }

    public Organism searchFood(java.util.List<Organism> organisms) {
        if (energyLevel > hungerThreshold) return null;
        Organism closest = null;
        float minDistance = Float.MAX_VALUE;
        for (Organism o : organisms) {
            if (o != this && !o.isDead && canConsume(o)) {
                float dx = o.x - x;
                float dy = o.y - y;
                float distance = (float)Math.sqrt(dx * dx + dy * dy);

                if (distance < minDistance && distance < visionRadius) {
                    minDistance = distance;
                    closest = o;
                }
            }
        }
        return closest;
    }

    public void chase(Organism target) {
        float dx = target.x - x;
        float dy = target.y - y;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) return;

        float dirX = dx / distance;
        float dirY = dy / distance;

        velocityX = dirX * speed;
        velocityY = dirY * speed;

        updatePosition();
    }

    public void attack(Organism target) {
        if (canConsume(target)) {
            energyLevel += addEnergy;
            target.markDead();
            state = State.CRUISE;
        }
    }

    public void hunt(Organism target) {
        state = State.HUNT;
        chase(target);
    }

    public void update(java.util.List<Organism> organisms) {
        if (isDead) return;
        updateBiologicalState();

        Organism prey = searchFood(organisms);

        if (prey != null) {
            hunt(prey);

            float dx = prey.x - x;
            float dy = prey.y - y;

            float distance = (float)Math.sqrt(dx*dx + dy*dy);

            if(distance < attackRadius){
                attack(prey);
            } else {
                hunt(prey);
            }
        }
    }
}

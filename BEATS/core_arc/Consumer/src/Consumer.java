abstract class Consumer extends Organism {
    float hungerThreshold;
    float visionRadius;
    float speed;
    State state;
    float attackRadius;
    float addEnergy;
    Organism currentTarget;

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
        float currentSpeed = (state == State.HUNT)
                ? (speed * 1.5f)
                : speed;
        float dx = target.x - x;
        float dy = target.y - y;

        float distance = (float)Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) return;

        float dirX = dx / distance;
        float dirY = dy / distance;

        velocityX = dirX * currentSpeed;
        velocityY = dirY * currentSpeed;

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


        // Not hungry -> cruise only.
        if (energyLevel > hungerThreshold) {
            currentTarget = null;
            state = State.CRUISE;
            cruise();
            return;
        }

        // Keep current target when valid, otherwise acquire a new one.
        if (currentTarget == null || currentTarget.isDead || !canConsume(currentTarget)) {
            currentTarget = searchFood(organisms);
        }

        if (currentTarget == null) {
            state = State.CRUISE;
            cruise();
            updatePosition();
            return;
        }

        float dx = currentTarget.x - x;
        float dy = currentTarget.y - y;
        float distance = (dx * dx + dy * dy);

        if (distance > Math.pow(visionRadius, 2)) {
            currentTarget = null;
            state = State.CRUISE;
            cruise();
            updatePosition();
            return;
        }

        if (distance <= Math.pow(attackRadius, 2)) {
            attack(currentTarget);
            if (currentTarget.isDead) {
                currentTarget = null;
            }
        } else {
            hunt(currentTarget);
        }

        updatePosition();
    }

    public void cruise() {
        // Gentle deterministic wandering while not hunting.
        float angle = age * 0.7f + (x * 0.01f);
        float cruiseSpeed = speed;
        velocityX = (float)Math.cos(angle) * cruiseSpeed;
        velocityY = (float)Math.sin(angle) * cruiseSpeed;
    }
}

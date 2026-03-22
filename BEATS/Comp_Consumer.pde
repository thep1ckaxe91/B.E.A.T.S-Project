enum State {
    CRUISE,
    HUNT
}

abstract class Consumer extends Organism {
    float hungerThreshold;
    float visionRadius;
    float speed;
    State state;
    float attackRadius;
    float addEnergy;
    Organism currentTarget;

    Consumer(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float hungerThreshold, float visionRadius, float speed, float attackRadius, float addEnergy) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax);
        this.hungerThreshold = hungerThreshold;
        this.visionRadius = visionRadius;
        this.speed = speed;
        state = State.CRUISE;
        this.attackRadius = attackRadius;
        this.addEnergy = addEnergy;
    }

    void updateBiologicalState() {
            energyLevel -= 0.1;
            if (energyLevel <= 0) dead = true;
        }

    public boolean checkDepthTolerance() {
        return this.y >= optimalDepthMin && this.y <= optimalDepthMax;
    }

    // what this organism specifically eat
    abstract boolean canConsume(Organism target);

    public Organism searchFood(java.util.List<Organism> organisms) {
        Organism target = null;
        // Todo: Nếu energyLevel < hungerThresHold => tìm target nếu trong visionRadius => xác định mục tiêu
    return taget;
    }

    public void chase(Organism target) {
        // Todo: nếu State là Hunt => tăng speed lên và cập nhật lại velocityX, và velocityY
        }

    public void hunt(Organism target) {
        // Todo: state chuyển sang HUNT và chase(target)
    }

    public void attack(Organism target) {
        if (canConsume(target)) {
            energyLevel += addEnergy;
            target.markForDeletion();
            state = State.CRUISE;
        }

        // Todo:
    }

    @Override
    public void update() {
        if (isDead()) return;
        updateBiologicalState();
        // Todo: tổng hợp lại hành vi từ searchFood => hunt => attack
        super.update();
    }

    public void cruise() {
        // Todo: trang thai khi khong san moi
    }
}

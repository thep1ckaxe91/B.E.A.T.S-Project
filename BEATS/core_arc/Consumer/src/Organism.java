public abstract class Organism extends BaseEntity {
    float energyLevel;
    float maxEnergy;
    float optimalDepthMin, optimalDepthMax;
    float age;
    float lifeSpan;
    float deltaTime;

    Organism(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float deltaTime)
    {
        super(x,y);
        this.energyLevel = energyLevel;
        this.maxEnergy = maxEnergy;
        this.optimalDepthMin = optimalDepthMin;
        this.optimalDepthMax = optimalDepthMax;
        this.deltaTime = deltaTime;
    }

    public void updateBiologicalState() {
        age += deltaTime;
        energyLevel -= energyDecayRate();
        checkLifeSpan();

        // TODO: @[Architect]: write check depth tolerance function to generally affect species that move out of its bound.
    }

    public boolean checkDepthTolerance() {
        return this.y >= optimalDepthMin && this.y <= optimalDepthMax;
    }

    public void checkLifeSpan() {
        if (age >= lifeSpan || energyLevel <= 0) isDead = true;
    }

    abstract float energyDecayRate();

    // what this organism specifically eat
    abstract boolean canConsume(Organism target);
}

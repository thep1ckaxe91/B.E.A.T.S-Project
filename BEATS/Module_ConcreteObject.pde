// Module_ConcreteObject.pde
// Where all the final classes are defined



final class Crab extends Decomposer {  
    
    Crab(float x, float y, float energyLevel) {
        // Initializing with zero/defaults. 
        // TODO: @[Core-Eng]: please implement a static load method to parse data from data/organisms/crab.json
        // and reuse it for instances. Ensure the values are cached once at startup.
        super(x, y, energyLevel, 50.0f, 0.75f, 1.0f);
    }
    
    @Override
    void update() {
        if (isDead()) return;

        updateBiologicalState();

        // TODO: implement movement logic for crab (e.g., random walk, or move towards food if detected).
    }

    @Override
    void render() {
        if (isDead()) return;
        // TODO: implement rendering logic for crab (e.g., draw a simple shape representing the crab).
    }

    @Override
    boolean isSelected(float mx, float my) {
        return false; 
        // TODO: implement selection logic based on mouse coordinates.
    }

    @Override
    boolean canConsume(Organism target) {
        return target.isDead();
    }

    void spawnAlgae() {
        // TODO: publish event EVENT_ENTITY_SPAWN_REQUEST (ALGAE) when energy level reaches a certain threshold.
    }

    @Override
    void consumeCorpse(Organism target) {
        // TODO: implement scavenger action logic (e.g., increase energy level by consuming the target organism).
    }

    @Override
    void searchCorpse() {
        // TODO: implement corpse searching logic (e.g., scan nearby area for dead organisms to consume).
    }
}


final class Algae extends Producer { 
    
    Algae(float x, float y, float energy, float maxE, float dMin, float dMax) {
        super(x, y, energy, maxE, dMin, dMax);
    }

    @Override
    void update() {
        if (isDead()) return;

        // Cập nhật trạng thái sinh học cơ bản 
        updateBiologicalState();

        // TODO: Thực hiện logic quang hợp dựa trên độ sâu y
        photosynthesis();  
        // TODO: Kiểm tra điều kiện nhân bản để cân bằng quần thể 
        checkReproduction();
    }

    @Override
    void render() {
        if (isDead()) return;
        // TODO: Vẽ tảo bằng các hình khối cơ bản để đảm bảo hiệu suất 60 FPS 
    }

    @Override
    boolean isSelected(float mx, float my) {
        return false; 
        // TODO: Xử lý logic chọn thực thể bằng chuột trên UI 
    }

    @Override
    boolean canConsume(Organism target) {
        // Tảo là sinh vật sản xuất, không tham gia săn mồi 
        return false;
    }

    @Override
    void photosynthesis() {
        // TODO: Tăng energyLevel dựa vào ánh sáng tại vùng nông/trung/sâu 
    }

    void checkReproduction() {
        // TODO: Khi đạt MaxEnergy, tạo bản sao và giảm năng lượng 
        // TODO: Sử dụng EventBus để gửi yêu cầu spawn thực thể mới 
    }
}

final class Shark extends Consumer{
    public Shark(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float hungerThreshold, float visionRadius, float speed, float attackRadius, float addEnergy) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax, hungerThreshold, visionRadius, speed, attackRadius, addEnergy);
    }

    @Override
    public float energyDecayRate() {
        return 0.3f;
    }

    @Override
    public boolean canConsume(Organism other) {
        return other instanceof Sardine || other instanceof Producer;
    }

    @Override
    public void render() {
        if (isDead()) return;
        // TODO: Vẽ tảo bằng các hình khối cơ bản để đảm bảo hiệu suất 60 FPS

    }

final class Sardine extends Consumer {
    public Sardine(float x, float y, float energyLevel, float maxEnergy, float optimalDepthMin, float optimalDepthMax, float optimalWidthMin, float optimalWidthMax, float width, float height, float hungerThreshold, float visionRadius, float speed, State state, float attackRadius, float addEnergy, Organism currentTarget) {
        super(x, y, energyLevel, maxEnergy, optimalDepthMin, optimalDepthMax, optimalWidthMin, optimalWidthMax, width, height, hungerThreshold, visionRadius, speed, state, attackRadius, addEnergy, currentTarget);
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
        // Todo: tìm Shark gần và avoid.
        return null;
    }

    public boolean avoidShark (Shark shark) {
        // Todo: phát hiện shark => tăng tốc độ, cập nhật lại velocityX và velocityY
        return true;
    }

    @Override
    public void update() {
        if (isDead()) return;

        // Todo: Update lại logic.
        super.update();
    }
}
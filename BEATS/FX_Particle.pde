class FX_Particle {

  PVector pos;   // vị trí particle
  PVector vel;   // vận tốc
  float life;    // thời gian tồn tại theo frame

  FX_Particle(PVector start) {

    pos = start.copy();

    // hướng bay ngẫu nhiên
    vel = PVector.random2D().mult(random(1,3));

    life = 60;

  }

  void update() {

    pos.add(vel);   

    vel.mult(0.98); 

    life--;    

  }

  void render() {

    noStroke();

    // nháy đỏ 
    class FX_Particle {

  PVector pos;   // vị trí particle
  PVector vel;   // vận tốc
  float life;    // thời gian tồn tại

  FX_Particle(PVector start) {

    pos = start.copy();

    // hướng bay ngẫu nhiên
    vel = PVector.random2D().mult(random(1,3));

    life = 60; // tồn tại 60 frame

  }

  void update() {

    pos.add(vel);   // di chuyển

    vel.mult(0.98); // giảm tốc

    life--;         // giảm life

  }

  void render() {

    noStroke();

    // màu đỏ + fade theo life
    fill(255, 80, 80, life * 4);

    ellipse(pos.x, pos.y, 4, 4);

  }

  boolean isDead() {

    return life <= 0;

  }

}


    ellipse(pos.x, pos.y, 6, 6);

  }

  boolean isDead() {

    return life <= 0;

  }

}

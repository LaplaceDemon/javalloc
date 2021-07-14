package io.github.laplacedemon.javalloc.struct.physics;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.struct.value.JDouble;

public class Point3D extends Point2D {
    public JDouble z;

    public void setValues(JStackAlloc jsa, long p, double x, double y, double z) {
        this.x.set(jsa, p, x);
        this.y.set(jsa, p, y);
        this.z.set(jsa, p, z);
    }

}

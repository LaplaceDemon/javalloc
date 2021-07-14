package io.github.laplacedemon.javalloc.struct;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.struct.physics.Point3D;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JStructDoubleTest {

    @Test
    public void testAlloc() {
        JStackAlloc jsa = new JStackAlloc(1024 * 16);  // 16k

        Point3D point3D = jsa.type(Point3D.class);

        long p = jsa.alloc(point3D);

        point3D.x.set(jsa, p, 10.0);
        point3D.y.set(jsa, p, 20.0);
        point3D.z.set(jsa, p, 30.0);

        System.out.println(Arrays.toString(jsa.getMemory()));

        double x = point3D.x.get(jsa, p);
        double y = point3D.y.get(jsa, p);
        double z = point3D.z.get(jsa, p);

        System.out.println(x+ "," + y+ "," + z);
        Assert.assertEquals(10.0, x, 0.0001);
        Assert.assertEquals(20.0, y, 0.0001);
        Assert.assertEquals(30.0, z, 0.0001);
    }

    @Test
    public void testContinuousAlloc() {
        JStackAlloc jsa = new JStackAlloc(1024 * 16);  // 16k

        Point3D point3D = jsa.type(Point3D.class);

        long p1 = jsa.alloc(point3D);
        long p2 = jsa.alloc(point3D);
        long p3 = jsa.alloc(point3D);

        point3D.setValues(jsa, p1, 10.0, 20.0, 30.0);
        point3D.setValues(jsa, p2, 10.0, 20.0, 30.0);
        point3D.setValues(jsa, p3, 10.0, 20.0, 30.0);

//        System.out.println(Arrays.toString(jsa.getMemory()));

        Assert.assertEquals(10.0, point3D.x.get(jsa, p1), 0.00001);
        Assert.assertEquals(20.0, point3D.y.get(jsa, p1), 0.00001);
        Assert.assertEquals(30.0, point3D.z.get(jsa, p1), 0.00001);

        Assert.assertEquals(10.0, point3D.x.get(jsa, p2), 0.00001);
        Assert.assertEquals(20.0, point3D.y.get(jsa, p2), 0.00001);
        Assert.assertEquals(30.0, point3D.z.get(jsa, p2), 0.00001);

        Assert.assertEquals(10.0, point3D.x.get(jsa, p3), 0.00001);
        Assert.assertEquals(20.0, point3D.y.get(jsa, p3), 0.00001);
        Assert.assertEquals(30.0, point3D.z.get(jsa, p3), 0.00001);
    }
}

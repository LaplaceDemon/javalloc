package io.github.laplacedemon.javalloc.struct;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.JStackOverflowException;
import org.junit.Test;

import java.util.Arrays;

public class JStructTest {

    @Test
    public void testAlloc() {
        JStackAlloc jStackAlloc = new JStackAlloc(1024 * 16);  // 16k

        int size = jStackAlloc.sizeOf(Student.class);
        long point = jStackAlloc.alloc(size);

        System.out.println(Arrays.toString(jStackAlloc.getMemory()));

        Student student = jStackAlloc.type(Student.class);

        int v = student.age.get(jStackAlloc, point);
        student.age.set(jStackAlloc, point, v + 10);
        student.gender.set(jStackAlloc, point, true);

        System.out.println(student.age.get(jStackAlloc, point));
        System.out.println(student.gender.get(jStackAlloc, point));

        System.out.println(Arrays.toString(jStackAlloc.getMemory()));
    }

    @Test
    public void testAlloc2() {
        JStackAlloc jStackAlloc = new JStackAlloc(1024 * 16);  // 16k
        Student student = jStackAlloc.type(Student.class);

        long p0 = jStackAlloc.alloc(student);
        long p1 = jStackAlloc.alloc(student);

        student.age.set(jStackAlloc, p0, 10);
        student.age.set(jStackAlloc, p1, 12);

        System.out.println(Arrays.toString(jStackAlloc.getMemory()));
    }

    @Test(expected = JStackOverflowException.class)
    public void testForAlloc() {
        JStackAlloc jsa = new JStackAlloc(1000);  // 16k

        Student student = jsa.type(Student.class);
        for (int i = 0; i < 1000; i++) {
            long p = jsa.alloc(student);

            student.gender.set(jsa, p, true);
            student.age.set(jsa, p, 123);
        }

    }
}

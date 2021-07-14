package io.github.laplacedemon.javalloc.struct;

import io.github.laplacedemon.javalloc.JStackAlloc;
import org.junit.Test;

import java.util.Arrays;

public class StringTest {

    @Test
    public void test() {
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

}



package io.github.laplacedemon.javalloc.struct.value;

import io.github.laplacedemon.javalloc.JStackAlloc;
import io.github.laplacedemon.javalloc.util.UNSAFE;

public class JDouble extends JValue {
    public static int SIZE = 8;

    public double get(JStackAlloc jStackAlloc, long handle) {
        byte[] memory = jStackAlloc.getMemory();
        return UNSAFE.getDouble(memory, 4 + offset + handle);
    }

    public void set(JStackAlloc jStackAlloc, long handle, double value) {
        byte[] memory = jStackAlloc.getMemory();
        UNSAFE.setDouble(memory, 4 + offset+ handle, value);
    }

}
